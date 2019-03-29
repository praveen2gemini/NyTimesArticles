package com.nytimes.apibase.logger;

/**
 * @author Praveen
 */

import android.os.Bundle;
import android.util.Log;

/**
 * Oribter Logger.
 *
 * @author Praveen
 */
public class Logger {

    /**
     * Log tag
     */
    private static final String TAG = "Oribter";
    /**
     * Holds the debug mode flag.
     */
    private static boolean sLogsEnabled = false;

    private Logger() {
    }

    @SuppressWarnings("unused")
    synchronized
    public static boolean isDebugMode() {
        return sLogsEnabled;
    }

    /**
     * Sets the Logs enabled flag.
     *
     * @param isLogsEnabled if true enables the logs
     */
    synchronized
    public static void setDebugMode(final boolean isLogsEnabled) {
        Log.d(TAG, "[OribterLogger] Logs Enabled: " + isLogsEnabled);
        sLogsEnabled = isLogsEnabled;
    }

    public static void v(final String tag,
                         final String message) {
        if (sLogsEnabled) {
            Log.v(TAG, String.format("[%s] %s", tag, message));
        }
    }

    public static void d(final String tag,
                         final String message) {
        if (sLogsEnabled) {
            Log.d(TAG, String.format("[%s] %s", tag, message));
        }
    }

    public static void d(final String tag,
                         final String message,
                         final Bundle bundle) {
        if (sLogsEnabled) {
            Log.d(TAG, String.format("[%s] %s", tag, message));
            if (null != bundle) {
                for (String key : bundle.keySet()) {
                    Log.d(TAG, String.format("[%s] - %s : %s", tag, key, String.valueOf(bundle.get(key))));
                }
            }
        }
    }

    public static void i(final String tag, final String message) {
        if (sLogsEnabled) {
            Log.i(TAG, String.format("[%s] %s", tag, message));
        }
    }

    public static void i(final String tag, final String message, final Throwable t) {
        if (sLogsEnabled) {
            Log.i(TAG, String.format("[%s] ERROR: %s", tag, message), t);
        }
    }

    public static void e(final String tag, final String message) {
        e(tag, message, null);
    }

    public static void e(final String tag, final String message, final Throwable t) {
        Log.e(TAG, String.format("[%s] ERROR: %s", tag, message), t);
    }

}

