package com.study.android.booklog.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.study.android.booklog.ImageRequester;
import com.study.android.booklog.R;
import com.study.android.booklog.VolleyCallback;
import com.study.android.booklog.model.Book;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailFragment extends Fragment {

    private TextView tvTitle;
    private ImageRequester imageRequester;
    private NetworkImageView coverImg;

    public BookDetailFragment() {
        imageRequester = ImageRequester.getInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        tvTitle = view.findViewById(R.id.tvTitleData);
        coverImg = view.findViewById(R.id.cover_image);
        setUpToolbar(view);
        Book.initBookDetail(getArguments().getString("bid"), new VolleyCallback() {
            @Override
            public void onSuccess(Object result) {
                Book book = (Book) result;
                tvTitle.setText(book.getTitle());
                imageRequester.setImageFromUrl(coverImg, book.getCoverUrl());
            }
        });
        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                // TransitionManager.beginDelayedTransition(container);
                //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
