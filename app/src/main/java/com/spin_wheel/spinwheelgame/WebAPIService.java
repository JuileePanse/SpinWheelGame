package com.spin_wheel.spinwheelgame;

import com.spin_wheel.spinwheelgame.models.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebAPIService {

        @GET("/bin/539dc092-8367-414a-8892-ed3b2d666dbe")
        Call<List<Item>> getItems();
}
