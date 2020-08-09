package com.example.studentbillingsystem.apiservices;


import com.example.studentbillingsystem.BuildConfig;
import com.example.studentbillingsystem.helpers.MyApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ACIS SAPKOTA on 1/26/2020.
 */
public class ApiClient {
//    private static final String BASE_URL = "https://elibrary.smartgov.app/api/";
    private static final String BASE_URL = "http://192.168.0.103:8085/";
    private static File httpCacheDirectory = new File(MyApplication.getAppContext().getCacheDir(), "responses");
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        builder.interceptors().add(interceptor);
//        builder.authenticator(new ApiTokenAuthenticator());
        OkHttpClient httpClient = builder
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new ApiInterceptorToken())
                .cache(cache)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        RxJava2CallAdapterFactory rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.computation());


        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }

}
