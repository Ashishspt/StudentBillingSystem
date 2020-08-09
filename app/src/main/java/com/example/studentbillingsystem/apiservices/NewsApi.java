package com.example.studentbillingsystem.apiservices;


import com.example.studentbillingsystem.models.News;
import com.example.studentbillingsystem.models.Subject;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface NewsApi {
    @GET("api/news/list")
    Observable<News> getNews();
}
