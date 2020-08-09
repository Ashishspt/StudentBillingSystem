package com.example.studentbillingsystem.apiservices;

import com.example.studentbillingsystem.models.Login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("oauth/token")
    @FormUrlEncoded
    Observable<Login> login(
            @Field("grant_type") String grant_type,
            @Field("username") String email,
            @Field("password") String password);

}
