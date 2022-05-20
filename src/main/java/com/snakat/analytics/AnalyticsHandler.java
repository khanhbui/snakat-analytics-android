package com.snakat.analytics;

import androidx.annotation.NonNull;

public interface AnalyticsHandler {
    void handle(@NonNull String event, Object... params);
}
