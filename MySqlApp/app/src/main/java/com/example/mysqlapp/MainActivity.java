package com.example.mysqlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mysqlapp.api.QuestionList;
import com.example.mysqlapp.databinding.ActivityMainBinding;
import com.example.mysqlapp.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity {



    ActivityMainBinding binding;
    MyViewModel myViewModel;



    QuestionList questionList;

    static int result;
    static int total_questions;

    int i=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        result = 0;
        total_questions = 0;

        myViewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.livedata().observe(MainActivity.this, new Observer<QuestionList>() {
                    @Override
                    public void onChanged(QuestionList myModels) {
                        questionList = myModels;
                        int size = questionList.size();
                        binding.button.setText("Next question");
                        binding.textView.setText("");
                        float radius = binding.textView.getTextSize() / 3;
                        BlurMaskFilter filter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL);
                        binding.textView.getPaint().setMaskFilter(filter);
                        if (i < size) {
                            if (binding.radioGroup.getCheckedRadioButtonId() == -1 && i != 0) {
                            Toast.makeText(getApplicationContext(), "You didnt mark the last question", Toast.LENGTH_SHORT).show();
                            }
                            else if(binding.radioGroup.getCheckedRadioButtonId() != -1) {
                                int noww = binding.radioGroup.getCheckedRadioButtonId();
                                RadioButton radioButton = findViewById(noww);
                                if (radioButton.getText().toString().equals(questionList.get(i - 1).getCorrectOption())) {
                                    result++;
                                }
                            }
                            binding.textView2.setText(questionList.get(i).getQuestion1());
                            binding.textView2.setText(questionList.get(i).getQuestion1());
                            binding.radioButton1.setText(questionList.get(i).getOption1());
                            binding.radioButton2.setText(questionList.get(i).getOption2());
                            binding.radioButton3.setText(questionList.get(i).getOption3());
                            binding.radioButton4.setText(questionList.get(i).getOption4());
                            binding.answer.setText(questionList.get(i).getCorrectOption());
                            i += 1;
                        } else {
                            Toast.makeText(getApplicationContext(), "QUESTIONS ARE FINISHED, TOTAL SCORE: "+result+"/"+size, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
            ;}
        ;})
    ;}
}


