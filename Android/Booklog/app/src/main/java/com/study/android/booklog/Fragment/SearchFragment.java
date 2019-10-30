package com.study.android.booklog.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import com.google.android.gms.common.SignInButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.study.android.booklog.Adapter.BestsellerCardRecyclerViewAdapter;
import com.study.android.booklog.Adapter.SearchCardRecyclerViewAdapter;
import com.study.android.booklog.BestsellerGridItemDecoration;
import com.study.android.booklog.R;
import com.study.android.booklog.VolleyCallback;
import com.study.android.booklog.model.Book;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "lecture";

    private TextInputEditText searchEditText;
    private Button searchButton;
    private RecyclerView recyclerView;
    public SearchFragment() {
        // Required empty public constructor
    }
    


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);



        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));

        final SearchCardRecyclerViewAdapter adapter = new SearchCardRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        searchEditText = view.findViewById(R.id.search_edit_text);
        searchButton = view.findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book.searchBook(searchEditText.getText().toString(), new VolleyCallback() {
                    @Override
                    public void onSuccess(Object result) {
                        adapter.setBookList((List<Book>) result);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });


        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new BestsellerGridItemDecoration(largePadding, smallPadding));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }




}
