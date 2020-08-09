package com.example.studentbillingsystem.apiservices;

import com.example.studentbillingsystem.models.Profile;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ProfilePicApi {
    @GET("api/image/upload")
    Observable<Profile> getProfile();
}
