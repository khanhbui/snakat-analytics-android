package com.snakat.analytics;

import android.content.Context;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

abstract class AnalyticsInternal {

    static boolean LOG_ENABLED = true;

    protected static final String TAG = Analytics.class.getName();

    protected final WeakReference<Context> mContext;

    protected final List<AnalyticsHandler> mHandlers = Collections.synchronizedList(new ArrayList<>());

    protected AnalyticsInternal(@NonNull Context context, boolean logEnabled) {
        LOG_ENABLED = logEnabled;

        mContext = new WeakReference<>(context);
    }

    protected void dispose() {
        for (AnalyticsHandler handler: mHandlers) {
            handler.dispose();
        }
        mHandlers.clear();
        mContext.clear();
    }
}
