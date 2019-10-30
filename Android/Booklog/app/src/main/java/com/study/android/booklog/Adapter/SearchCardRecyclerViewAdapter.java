package com.study.android.booklog.Adapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.study.android.booklog.Fragment.BookDetailFragment;
import com.study.android.booklog.ImageRequester;
import com.study.android.booklog.NavigationHost;
import com.study.android.booklog.R;
import com.study.android.booklog.model.Book;

import java.util.List;

public class SearchCardRecyclerViewAdapter extends RecyclerView.Adapter<SearchCardRecyclerViewAdapter.SearchCardViewHolder> {
    private List<Book> bookList;
    private ImageRequester imageRequester;


    public SearchCardRecyclerViewAdapter() {
        imageRequester = ImageRequester.getInstance();
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public SearchCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card, parent, false);
        return new SearchCardViewHolder(layoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull final SearchCardViewHolder holder, final int position) {
        final Book book = bookList.get(position);
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.fade_in));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment bookDetail = new BookDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("bid", book.bid);
                bookDetail.setArguments(bundle);

                ((NavigationHost) v.getContext()).navigateAdd(bookDetail, true);
            }
        });
        holder.title.setText(book.title);
        holder.author.setText(book.authorList.get(0).name);
        imageRequester.setImageFromUrl(holder.coverImage, book.coverUrl);
    }

    @Override
    public int getItemCount() {
        if(bookList == null) {
            return 0;
        }
        return bookList.size();
    }

    class SearchCardViewHolder extends RecyclerView.ViewHolder {


        NetworkImageView coverImage;
        TextView title;
        TextView author;


        SearchCardViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.cover_image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);

        }


    }

}