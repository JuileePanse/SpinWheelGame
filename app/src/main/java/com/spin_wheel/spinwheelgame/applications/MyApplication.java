package com.spin_wheel.spinwheelgame.applications;


import android.app.Application;

import com.spin_wheel.spinwheelgame.utils.ConnectivityReceiver;

public class MyApplication extends Application {
    private static MyApplication mInstance;
 
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
 
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
 
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}