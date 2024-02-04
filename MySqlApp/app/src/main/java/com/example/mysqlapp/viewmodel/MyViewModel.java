package com.example.mysqlapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysqlapp.api.QuestionList;
import com.example.mysqlapp.source.MyRepository;

public class MyViewModel extends ViewModel {

    private MyRepository myRepository = new MyRepository();
    private LiveData<QuestionList> liveData;




    public MyViewModel() {
        liveData = myRepository.getQuestionfromapi();
    }

    public LiveData<QuestionList> livedata(){
        return liveData;
    }


}
