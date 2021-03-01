package com.spin_wheel.spinwheelgame.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityReceiver extends BroadcastReceiver {
 
    public static ConnectivityReceiverListener connectivityReceiverListener;
 
    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            connectivityReceiverListener.onNetworkConnectionChanged(isOnline(context));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}