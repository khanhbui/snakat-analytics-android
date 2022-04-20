package com.snakat.analytics;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class Analytics {

    private final WeakReference<Context> mContext;

    protected Analytics(@NonNull Context context) {
        mContext = new WeakReference<>(context);
    }

    protected void destroy() {
        mContext.clear();
    }

    @Nullable
    protected Context getContext() {
        return mContext.get();
    }
}
