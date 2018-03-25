package com.fourllc.donate.NetworkingUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

/**
 * Utilities for Networking Functions
 */

public class NetworkingUtils {
    /**
     * Function to check fo Network Connection
     * @param context the context to be used to get the connection info
     * @return boolean true if is connected false if not
     */
    public static boolean hasNetworkConnection(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return !(networkInfo == null || !networkInfo.isConnectedOrConnecting());
    }
}
