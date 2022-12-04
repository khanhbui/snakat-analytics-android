package com.snakat.analytics.slack;

import android.content.Context;

import androidx.annotation.NonNull;

import com.snakat.analytics.AnalyticsHandler;
import com.snakat.analytics.R;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class SlackAnalyticsHandler implements AnalyticsHandler {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private final SlackApi mSlackApi;
    private final String mHookUrl;
    private final Formatter mFormatter;

    private SlackAnalyticsHandler(@NonNull Builder builder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(builder.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mSlackApi = retrofit.create(SlackApi.class);
        mHookUrl = builder.getHookUrl();
        mFormatter = builder.getFormatter();
    }

    @Override
    public void dispose() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void handle(@NonNull String event, Object... params) {
        mSlackApi.logEvent(mHookUrl, new SlackRequest(mFormatter.format(event, params)))
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        // do nothing
                    }

                    @Override
                    public void onError(Throwable e) {
                        // do nothing
                    }
                });
    }

    public interface Formatter {
        String format(@NonNull String event, Object... params);
    }

    public static final class Builder {
        private final String mBaseUrl;
        private String mHookUrl;
        private Formatter mFormatter;

        public Builder(@NonNull Context context) {
            mBaseUrl = context.getString(R.string.slack_base_url);
        }

        public String getBaseUrl() {
            return mBaseUrl;
        }

        public String getHookUrl() {
            return mHookUrl;
        }

        public Builder setHookUrl(String hookUrl) {
            mHookUrl = hookUrl;
            return this;
        }

        public Formatter getFormatter() {
            return mFormatter;
        }

        public Builder setFormatter(Formatter formatter) {
            mFormatter = formatter;
            return this;
        }

        public SlackAnalyticsHandler build() {
            return new SlackAnalyticsHandler(this);
        }
    }
}
