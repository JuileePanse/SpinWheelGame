package com.spin_wheel.spinwheelgame.utils;


import com.spin_wheel.spinwheelgame.WebAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "http://mockbin.org";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static WebAPIService getInterface() {
        return retrofit.create(WebAPIService.class);
    }
}

