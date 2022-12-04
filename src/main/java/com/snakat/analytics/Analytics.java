package com.snakat.analytics;

import android.content.Context;

import androidx.annotation.NonNull;

public final class Analytics extends AnalyticsInternal {

    private static Analytics mInstance;

    public static Analytics getInstance() {
        return mInstance;
    }

    public static void createInstance(@NonNull Context context) {
        createInstance(context, false);
    }

    public static void createInstance(@NonNull Context context, boolean logEnabled) {
        if (mInstance == null) {
            synchronized (Analytics.class) {
                mInstance = new Analytics(context, logEnabled);
            }
        }
    }

    public static void destroyInstance() {
        mInstance.dispose();
        mInstance = null;
    }

    private Analytics(@NonNull Context context, boolean logEnabled) {
        super(context, logEnabled);
    }

    public void logEvent(@NonNull String event, Object... params) {
        for (AnalyticsHandler handler : mHandlers) {
            handler.handle(event, params);
        }
    }

    public void addHandler(AnalyticsHandler analyticsHandler) {
        mHandlers.add(analyticsHandler);
    }
}
