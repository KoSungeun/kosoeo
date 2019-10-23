package com.study.android.booklog;


import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BestsellerCardRecyclerViewAdapter extends RecyclerView.Adapter<BestsellerCardViewHolder> {
    private List<Book> bookList;
    private ImageRequester imageRequester;

    private ViewGroup parent;

    public BestsellerCardRecyclerViewAdapter(List<Book> bookList) {
        this.bookList = bookList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public BestsellerCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bestseller_card, parent, false);
        this.parent = parent;


        return new BestsellerCardViewHolder(layoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull final BestsellerCardViewHolder holder, int position) {
        final Book book = bookList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(parent);
                 holder.itemView.getLayoutParams().width = 500;
                 holder.itemView.requestLayout();
         //       ((NavigationHost) BooklogApplication.getActivitContext()).navigateTo(new BookDetailFragment(), true);
            }
        });
        holder.title.setText(book.title);
        holder.author.setText(book.authorList.get(0).name);
        imageRequester.setImageFromUrl(holder.coverImage, book.coverUrl);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

}
