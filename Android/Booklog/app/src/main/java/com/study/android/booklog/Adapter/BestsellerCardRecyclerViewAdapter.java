package com.study.android.booklog.Adapter;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class BestsellerCardRecyclerViewAdapter extends RecyclerView.Adapter<BestsellerCardRecyclerViewAdapter.BestsellerCardViewHolder> {
    public List<Book> bookList;
    private ImageRequester imageRequester;

    public BestsellerCardRecyclerViewAdapter() {
        imageRequester = ImageRequester.getInstance();
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BestsellerCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bestseller_card, parent, false);
        return new BestsellerCardViewHolder(layoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull final BestsellerCardViewHolder holder, final int position) {
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


        holder.rankNum.setText(String.valueOf(book.getRank()));

        String rankChange = book.getRankChange();

        int rankChangeNum = 0;
        if(!rankChange.equals("NEW") && !rankChange.equals("")) {
            rankChangeNum = Integer.parseInt(rankChange);
        }
        if(rankChangeNum == 0 && !rankChange.equals("NEW")) {
            holder.rankChangeLayout.setVisibility(View.GONE);
        } else {
            if(rankChangeNum < 0) {
                holder.rankChangeImg.setImageResource(R.drawable.down_black_10dp);
                holder.rankChangeImg.setColorFilter(R.color.quantum_lightblue);
                rankChangeNum = Math.abs(rankChangeNum);
            } else if (rankChange.equals("NEW")) {
                holder.rankChangeImg.setVisibility(View.GONE);
            }
            if(!rankChange.equals("NEW")) {
                holder.rankChangeNum.setText(String.valueOf(rankChangeNum));
            } else {
                holder.rankChangeNum.setText(rankChange);
            }

        }



        imageRequester.setImageFromUrl(holder.coverImage, book.coverUrl);
    }

    @Override
    public int getItemCount() {
        if(bookList == null) {
            return 0;
        }
        return bookList.size();
    }

    class BestsellerCardViewHolder extends RecyclerView.ViewHolder {


        NetworkImageView coverImage;
        TextView title;
        TextView author;
        TextView rankNum;
        TextView rankChangeNum;
        ImageView rankChangeImg;
        LinearLayout rankChangeLayout;


        BestsellerCardViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.cover_image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            rankNum = itemView.findViewById(R.id.rank_num);
            rankChangeNum = itemView.findViewById(R.id.rank_change_num);
            rankChangeImg = itemView.findViewById(R.id.rank_change_img);
            rankChangeLayout = itemView.findViewById(R.id.rank_change_layout);

        }


    }

}