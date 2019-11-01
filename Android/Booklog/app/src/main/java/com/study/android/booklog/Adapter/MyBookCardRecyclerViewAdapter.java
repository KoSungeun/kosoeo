package com.study.android.booklog.Adapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.material.snackbar.Snackbar;
import com.study.android.booklog.Fragment.BookDetailFragment;

import com.study.android.booklog.ImageRequester;
import com.study.android.booklog.MySnackbar;
import com.study.android.booklog.NavigationHost;
import com.study.android.booklog.R;
import com.study.android.booklog.VolleyCallback;
import com.study.android.booklog.model.Book;

import java.util.List;

public class MyBookCardRecyclerViewAdapter extends RecyclerView.Adapter<MyBookCardRecyclerViewAdapter.MyBookCardViewHolder> {
    private List<Book> bookList;
    private ImageRequester imageRequester;
    private ViewGroup praent;

    public MyBookCardRecyclerViewAdapter() {
        imageRequester = ImageRequester.getInstance();
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }


//    @Override
//    public int getItemViewType(int position) {
//        return position %3;
//    }

    @NonNull
    @Override
    public MyBookCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        int layoutId = R.layout.my_book_card_first;
//        if (viewType == 1) {
//            layoutId = R.layout.my_book_card_second;
//        } else if (viewType == 2) {
//            layoutId = R.layout.my_book_card_third;
//        }
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_book_card, parent, false);
        this.praent = parent;
        return new MyBookCardViewHolder(layoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyBookCardViewHolder holder, final int position) {

        final Book book = bookList.get(position);
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.fade_in));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                PopupMenu popup = new PopupMenu(v.getContext(), v);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {


                        switch (item.getItemId()) {
                            case R.id.delete:
                                praent.getRootView().findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                                Book.deleteMyBook(book.getMyFirebaseData(), new VolleyCallback() {
                                    @Override
                                    public void onSuccess(Object result) {
                                        bookList.remove(position);
                                        praent.getRootView().findViewById(R.id.progressBar).setVisibility(View.GONE);
                                        notifyDataSetChanged();
                                        ((MySnackbar) v.getContext()).show("삭제했습니다", Snackbar.LENGTH_LONG);


                                    }
                                });
                                break;

                            case R.id.read:
                                final boolean isRead = (boolean) book.getMyFirebaseData().get("isRead");
                                book.getMyFirebaseData().put("isRead", !isRead);
                                praent.getRootView().findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                                Book.updateMyBook(book.getMyFirebaseData(), new VolleyCallback() {
                                    @Override
                                    public void onSuccess(Object result) {
                                        praent.getRootView().findViewById(R.id.progressBar).setVisibility(View.GONE);
                                        notifyDataSetChanged();
                                        ((MySnackbar)v.getContext()).show("업데이트 했습니다", Snackbar.LENGTH_LONG);
                                    }
                                });
                                break;
                            case R.id.detail:
                               Fragment bookDetail = new BookDetailFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("bid", book.bid);
                                bookDetail.setArguments(bundle);

                                ((NavigationHost) v.getContext()).navigateAdd(bookDetail, true);
                                break;

                        }


                        return false;
                    }
                });
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
            }
        });

        holder.title.setText(book.title);
        holder.author.setText(book.authorList.get(0).name);

        if((Boolean) book.getMyFirebaseData().get("isRead")) {
            holder.readCheck.setImageResource(R.drawable.read_ok);
        } else {

            holder.readCheck.setImageResource(R.drawable.not_read);
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

    class MyBookCardViewHolder extends RecyclerView.ViewHolder {


        NetworkImageView coverImage;
        TextView title;
        TextView author;
        ImageView readCheck;


        MyBookCardViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.cover_image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            readCheck = itemView.findViewById(R.id.read_check);
        }


    }

}