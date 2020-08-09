package com.example.studentbillingsystem.apiservices;


import com.example.studentbillingsystem.models.Subject;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface SubjectApi {
    @GET("api/subjects")
    Observable<Subject> getSubject();
}
