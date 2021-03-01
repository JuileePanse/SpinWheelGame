package com.spin_wheel.spinwheelgame.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Util {

    private static Util util;

    public static Util getInstance()
    {
        if (util == null) util = new Util();
        return util;
    }

    public boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void showMessage(Context context, String messageString){
        Toast.makeText(context, messageString,Toast.LENGTH_SHORT).show();
    }
}
