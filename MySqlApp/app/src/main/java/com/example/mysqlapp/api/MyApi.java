package com.example.mysqlapp.api;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MyApi {

    @GET("anani.php")
    Call<QuestionList> getQuestions();

}
