package com.study.android.booklog;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.android.volley.toolbox.NetworkImageView;


public class BestsellerCardViewHolder extends RecyclerView.ViewHolder {


    public NetworkImageView coverImage;
    public TextView title;
    public TextView author;


    public BestsellerCardViewHolder(@NonNull View itemView) {
        super(itemView);
        coverImage = itemView.findViewById(R.id.cover_image);
        title = itemView.findViewById(R.id.book_title);
        author = itemView.findViewById(R.id.book_author);



    }

}