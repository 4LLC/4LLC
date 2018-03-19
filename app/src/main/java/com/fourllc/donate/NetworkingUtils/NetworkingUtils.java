package com.fourllc.donate.NetworkingUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;


// TODO: 3/15/2018 remove these comments... GIT keeps track of these things.
///**
// * Created by aaronbrecher on 3/6/18.
// */

public class NetworkingUtils {
    public static boolean hasNetworkConnection(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//        boolean isConnected = true;
//        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) isConnected = false;
//        return isConnected;

        // TODO: 3/15/2018 this is a more concise expression.
        return !(networkInfo == null || !networkInfo.isConnectedOrConnecting());
    }
}
