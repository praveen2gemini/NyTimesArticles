package com.nytimes.apibase.common_utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;


/**
 * A class that provides the common device related utility methods.
 *
 * @author Praveen
 */
public class DeviceUtil {

    /**
     * A method to check if the device is online.
     *
     * @param context the application context
     * @return true if online; otherwise returns false.
     */
    public static boolean isOffline(Context context) {
        if (null == context) return true;
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;
        return null == cm || null == (netInfo = cm.getActiveNetworkInfo()) || !netInfo.isConnectedOrConnecting();
    }

    /**
     * It will return true if device runs on LOLLIPOP and above devices.
     *
     * @return - true if device version is LOLLIPOP and above.
     */
    public static boolean isLollipopDevice() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

}
