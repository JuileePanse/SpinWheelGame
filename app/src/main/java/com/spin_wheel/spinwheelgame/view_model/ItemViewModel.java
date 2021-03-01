package com.spin_wheel.spinwheelgame.view_model;

import android.util.Log;

import com.spin_wheel.spinwheelgame.WebAPIService;
import com.spin_wheel.spinwheelgame.models.Item;
import com.spin_wheel.spinwheelgame.utils.RetrofitService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemViewModel extends ViewModel {
    private MutableLiveData<List<Item>> itemList;

    public LiveData<List<Item>> getItems() {
        //if the list is null
        if (itemList == null) {
            itemList = new MutableLiveData<>();
            getItems1();
        }

        //finally we will return the list
        return itemList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void getItems1() {

        WebAPIService service1 = RetrofitService.getInterface();
        Call<List<Item>> jsonCall = service1.getItems();

        jsonCall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                List<Item> list = response.body();
                itemList.setValue(list);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("JSON", t.toString());
            }
        });
    }
}
