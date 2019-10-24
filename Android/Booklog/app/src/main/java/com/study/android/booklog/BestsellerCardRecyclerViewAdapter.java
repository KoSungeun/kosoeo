package com.study.android.booklog;


import android.os.Bundle;
import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.study.android.booklog.Fragment.BookDetailFragment;
import com.study.android.booklog.model.Book;

import java.util.List;

public class BestsellerCardRecyclerViewAdapter extends RecyclerView.Adapter<BestsellerCardViewHolder> {
    private List<Book> bookList;
    private ImageRequester imageRequester;

    private ViewGroup parent;
    Scene scene;

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
    public void onBindViewHolder(@NonNull final BestsellerCardViewHolder holder, final int position) {
        final Book book = bookList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment bookDetail = new BookDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("bid", book.bid);
                bookDetail.setArguments(bundle);
                ((NavigationHost) BooklogApplication.getActivitContext()).navigateTo(bookDetail, true);
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
