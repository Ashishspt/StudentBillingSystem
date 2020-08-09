package com.example.studentbillingsystem.apiservices;

import android.util.Base64;
import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptorToken implements Interceptor {
//    final String token="bearer " + Utilities.getLoginResponse().getAccessToken();
    final String basicAuth = "Basic " + Base64.encodeToString("c1i3ntID:S3cre7".getBytes(), Base64.NO_WRAP);


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        if (Utilities.getLoginResponse() == null ) {
//            return chain.proceed(originalRequest);
//        }

        Request request = originalRequest.newBuilder()
                .addHeader("Authorization",basicAuth)
//                .addHeader("Authorization","bearer " + Utilities.getLoginResponse().getAccessToken())
                //.addHeader("Authorization", Utilities.getLoginResponse().getUserDetails().getToken())
//                .addHeader("Accept", "Accept: application/x.school.v1+json")
                .header("Cache-Control", String.format("max-age=%d", 50000))
                .build();
        return chain.proceed(request);
    }
}