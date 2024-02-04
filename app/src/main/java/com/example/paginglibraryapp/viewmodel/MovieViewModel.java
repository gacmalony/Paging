package com.example.paginglibraryapp.viewmodel;

import static com.bumptech.glide.Glide.init;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.paginglibraryapp.model.Movie;
import com.example.paginglibraryapp.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {

    public Flowable<PagingData<Movie>> flow;

    public MovieViewModel() {
        init();
    }

    private void init() {
        MoviePagingSource moviePagingSource = new MoviePagingSource();


        Pager<Integer, Movie> pager = new Pager<>(
                new PagingConfig(
                        20,
                        20,
                        false,
                        20,
                        20*499
        ),
        () -> moviePagingSource);

        flow = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(flow, coroutineScope);
    }
}
