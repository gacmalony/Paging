package com.example.mysqlapp.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mysqlapp.api.MyApi;
import com.example.mysqlapp.api.QuestionList;
import com.example.mysqlapp.api.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository {

    private MyApi myApi;


    public MyRepository() {
        this.myApi = new RetrofitInstance()
                .getQuestions()
                .create(MyApi.class);

    }

    public LiveData<QuestionList> getQuestionfromapi(){
        MutableLiveData<QuestionList> mutableLiveData = new MutableLiveData<>();
        Call<QuestionList> response = myApi.getQuestions();
        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                QuestionList list = response.body();
                mutableLiveData.setValue(list);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }




}
