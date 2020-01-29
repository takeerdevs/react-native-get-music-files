package com.GetMusicFiles.Utils;

import android.os.Bundle;

import com.facebook.react.bridge.Promise;

public class GeneralUtils {

    static final String LOG = "RNGetMusicFiles";

    public static Runnable toRunnable(Promise promise) {
        return () -> promise.resolve(null);
    }

    static long toMillis(double seconds) {
        return (long) (seconds * 1000);
    }

    static double toSeconds(long millis) {
        return millis / 1000D;
    }

    static int getInt(Bundle data, String key, int defaultValue) {
        Object value = data.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return defaultValue;
    }

}
