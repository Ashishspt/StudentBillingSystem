package com.example.studentbillingsystem.apiservices;

import com.example.studentbillingsystem.models.Profile;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ProfileApi {
    @GET("api/profile")
    Observable<Profile> getProfile();
}
