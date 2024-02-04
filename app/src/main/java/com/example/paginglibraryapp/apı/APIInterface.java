package com.example.paginglibraryapp.apı;

import com.example.paginglibraryapp.model.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    //RXJava 3.0 and Retrofit 2.0 wrap provided by gocmen :)
    @GET("movie/popular")
    Single<MovieResponse> movies(@Query("page")int page);
}