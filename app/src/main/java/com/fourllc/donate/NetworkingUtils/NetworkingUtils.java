package com.fourllc.donate.NetworkingUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

/**
 * Created by aaronbrecher on 3/6/18.
 */

public class NetworkingUtils {
    public static boolean hasNetworkConnection(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isConnected = true;
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) isConnected = false;
        return isConnected;
    }
}
