package com.example.mysqlapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public RetrofitInstance() {
    }

    private static Retrofit retrofit;
    private static String BASE_URL = "http://10.0.2.2/wtf2/";

    public Retrofit getQuestions()
    {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }





}
