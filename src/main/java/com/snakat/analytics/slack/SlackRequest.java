package com.snakat.analytics.slack;

import com.google.gson.annotations.SerializedName;

class SlackRequest {
    @SerializedName("text")
    private String mText;

    public SlackRequest(String text) {
        mText = text;
    }
}
