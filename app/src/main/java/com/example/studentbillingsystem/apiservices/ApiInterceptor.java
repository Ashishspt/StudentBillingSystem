package com.example.studentbillingsystem.apiservices;

import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.studentbillingsystem.utils.Utilities;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    final String token="bearer " + Utilities.getLoginResponse().getAccessToken();
//    final String basicAuth = "Basic " + Base64.encodeToString("c1i3ntID:S3cre7".getBytes(), Base64.NO_WRAP);


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        if (Utilities.getLoginResponse() == null ) {
//            return chain.proceed(originalRequest);
//        }

        Request request = originalRequest.newBuilder()
//                .addHeader("Authorization",basicAuth)
//                .addHeader("Authorization","bearer " + Utilities.getLoginResponse().getAccessToken())

                .addHeader("Authorization", token)

//                .addHeader("Accept", "Accept: application/x.school.v1+json")
                .header("Cache-Control", String.format("max-age=%d", 50000))
                .build();
        Log.e( "intercept: ",token );
        return chain.proceed(request);
    }
}