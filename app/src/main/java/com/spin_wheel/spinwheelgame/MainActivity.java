package com.spin_wheel.spinwheelgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.WheelItem;
import com.spin_wheel.spinwheelgame.applications.MyApplication;
import com.spin_wheel.spinwheelgame.models.Item;
import com.spin_wheel.spinwheelgame.utils.ConnectivityReceiver;
import com.spin_wheel.spinwheelgame.view_model.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {


    private LuckyWheel wheelView;
    private ConnectivityReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wheelView = findViewById(R.id.lwv);
        ArrayList<WheelItem> wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.DKGRAY, BitmapFactory.decodeResource(getResources(), R.drawable.image), "Click button rotate"));
        wheelView.addWheelItems(wheelItems);

        mConnectivityReceiver = new ConnectivityReceiver();
        registerNetworkBroadcastForNougat();
        //checkConnection();

        findViewById(R.id.rotate).setOnClickListener(view -> wheelView.rotateWheelTo(2));
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showList(isConnected);
    }

    private void checkConnection() {
        boolean isConnected = mConnectivityReceiver.isOnline(MainActivity.this);
        showList(isConnected);
    }

    private void showList(boolean isConnected) {

        if (isConnected) {
            //Initialize View Model and get data
            ItemViewModel model = ViewModelProviders.of(this).get(ItemViewModel.class);
            model.getItems().observe(this, new Observer<List<Item>>() {
                @Override
                public void onChanged(List<Item> items) {
                    setUpListToWheel(items);
                }
            });


        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.check_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpListToWheel(List<Item> itemList) {

        ArrayList<WheelItem> wheelItems = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++){
            if (i%2==0)
                wheelItems.add(new WheelItem(Color.DKGRAY, BitmapFactory.decodeResource(getResources(), R.drawable.image), itemList.get(i).getDisplayText()));
            else
                wheelItems.add(new WheelItem(Color.BLACK, BitmapFactory.decodeResource(getResources(), R.drawable.image), itemList.get(i).getDisplayText()));
        }

        wheelView.addWheelItems(wheelItems);
        System.out.println("wheel items "+wheelItems.size());

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConnectivityReceiver != null)
            unregisterReceiver(mConnectivityReceiver);
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
}