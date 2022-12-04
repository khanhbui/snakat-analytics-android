package com.snakat.analytics.slack;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface SlackApi {
    @POST("{url}")
    @Headers("Content-type: application/json")
    Single<ResponseBody> logEvent(
            @Path(value = "url", encoded = true) String url,
            @Body SlackRequest slackRequest
    );
}
