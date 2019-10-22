package com.study.android.booklog;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BestsellerCardRecyclerViewAdapter extends RecyclerView.Adapter<BestsellerCardViewHolder> {
    private List<Book> bookList;

    @NonNull
    @Override
    public BestsellerCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BestsellerCardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
