package com.example.paginglibraryapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paginglibraryapp.R;
import com.example.paginglibraryapp.databinding.LayoutStateItemBinding;

public class MoviesLoadStateAdapter extends LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder> {

    private View.OnClickListener mRetryCallback;

    public MoviesLoadStateAdapter() {
    }

    public MoviesLoadStateAdapter(View.OnClickListener mRetryCallback){
        this.mRetryCallback = mRetryCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder loadStateViewHolder, @NonNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LoadState loadState) {
        return new LoadStateViewHolder(parent, mRetryCallback);
    }


    public static class LoadStateViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar mProgressBar;
        private TextView mErrorMsg;
        private Button mRetry;

        public LoadStateViewHolder(@NonNull ViewGroup parent,
                                   @NonNull View.OnClickListener retryCallback) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.layout_state_item, parent, false));


            LayoutStateItemBinding binding = LayoutStateItemBinding.bind(itemView);
            mProgressBar = binding.progressBar;
            mErrorMsg = binding.textview;
            mRetry = binding.button;
            mRetry.setOnClickListener(retryCallback);
        }
        public void bind(LoadState loadState){
            if (loadState instanceof LoadState.Error){
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                mErrorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }

            mProgressBar.setVisibility(
                    loadState instanceof LoadState.Loading ?View.VISIBLE :View.GONE);

            mRetry.setVisibility(
                    loadState instanceof LoadState.Error ?View.VISIBLE :View.GONE);

            mErrorMsg.setVisibility(
                    loadState instanceof LoadState.Error ?View.VISIBLE :View.GONE);

        }

    }

}

