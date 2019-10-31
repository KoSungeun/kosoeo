package com.study.android.booklog.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.study.android.booklog.Adapter.BestsellerCardRecyclerViewAdapter;
import com.study.android.booklog.BestsellerGridItemDecoration;
import com.study.android.booklog.R;
import com.study.android.booklog.VolleyCallback;
import com.study.android.booklog.model.Book;

import java.util.List;


public class BestsellerFragment extends Fragment {

    RecyclerView recyclerView;
    TabLayout tabLayout;
    ProgressBar progressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bestseller, container, false);
        setUpToolbar(view);
        recyclerView = view.findViewById(R.id.recycler_view);
        tabLayout = view.findViewById(R.id.tabs);
        progressBar = view.findViewById(R.id.progressBar);


        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);




        final BestsellerCardRecyclerViewAdapter adapter = new BestsellerCardRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
        Book.getBestsellerBookList("yes24", new VolleyCallback(){
            @Override
            public void onSuccess(Object result) {
                adapter.setBookList((List<Book>) result);
                progressBar.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String cpName = "";
                switch (tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        cpName = "kyobo";
                        break;
                    case 2:
                        cpName = "aladdin";
                        break;
                    case 3:
                        cpName = "bookpark";
                        break;
                    case 4:
                        cpName = "bandi";
                        break;
                    case 5:
                        cpName = "ypbooks";
                        break;
                    case 6:
                        cpName = "morning365";
                        break;
                    case 7:
                        cpName = "conects";
                        break;
                }
                progressBar.setVisibility(View.VISIBLE);
                Book.getBestsellerBookList(cpName, new VolleyCallback() {
                    @Override
                    public void onSuccess(Object result) {
                        adapter.setBookList((List<Book>) result);
                        progressBar.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new BestsellerGridItemDecoration(largePadding, smallPadding));

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

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
