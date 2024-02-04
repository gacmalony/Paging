package com.example.paginglibraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.paginglibraryapp.adapter.MoviesAdapter;
import com.example.paginglibraryapp.adapter.MoviesLoadStateAdapter;
import com.example.paginglibraryapp.databinding.ActivityMainBinding;
import com.example.paginglibraryapp.utils.GridSpace;
import com.example.paginglibraryapp.utils.MovieComparator;
import com.example.paginglibraryapp.utils.Utils;
import com.example.paginglibraryapp.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    MovieViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        if(Utils.API_KEY == null || Utils.API_KEY.isEmpty()){
            Toast.makeText(this, "Error in API KEY", Toast.LENGTH_SHORT).show();
        }

        moviesAdapter = new MoviesAdapter(new MovieComparator(),requestManager);

        mainActivityViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        initializer();

        mainActivityViewModel.flow.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initializer() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        binding.recyclerview.setLayoutManager(gridLayoutManager);

        binding.recyclerview.addItemDecoration(new GridSpace(2, 20, true));

        binding.recyclerview.setAdapter(
                moviesAdapter.withLoadStateFooter(
                        new MoviesLoadStateAdapter(view ->{
                            moviesAdapter.retry();
                        })
                )
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1:2;
            }
        });

    }
}