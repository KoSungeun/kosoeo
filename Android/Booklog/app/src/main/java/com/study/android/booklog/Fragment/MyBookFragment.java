package com.study.android.booklog.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.study.android.booklog.Adapter.BestsellerCardRecyclerViewAdapter;
import com.study.android.booklog.Adapter.MyBookCardRecyclerViewAdapter;
import com.study.android.booklog.BestsellerGridItemDecoration;
import com.study.android.booklog.BooklogApplication;
import com.study.android.booklog.MyRequestQueue;
import com.study.android.booklog.R;
import com.study.android.booklog.VolleyCallback;
import com.study.android.booklog.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookFragment extends Fragment {

    private FirebaseAuth mAuth;
    private static final String TAG = "lecture";
    private RecyclerView recyclerView;
    private int RC_SIGN_IN = 9001;
    private SignInButton mBtnGoogleSignIn; // 로그인 버튼
    private GoogleSignInClient mGoogleSignInClient;
    private LinearLayout loginLayout;
    private MyBookCardRecyclerViewAdapter adapter;
    private ProgressBar progressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_book, container, false);
        setUpToolbar(view);


        progressBar = view.findViewById(R.id.progressBar);
        mBtnGoogleSignIn = view.findViewById(R.id.sign_in_button);
        mBtnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        recyclerView = view.findViewById(R.id.recycler_view);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));


        initFirebaseAuth();

        loginLayout = view.findViewById(R.id.login_layout);


        adapter = new MyBookCardRecyclerViewAdapter();

        recyclerView.setAdapter(adapter);
        if(mAuth.getCurrentUser() != null) {
            adapterSetMyBookData();
        }


        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new BestsellerGridItemDecoration(largePadding, smallPadding));

        return view;

    }

    private void adapterSetMyBookData() {

        loginLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Book.getMyBook(new VolleyCallback() {
            @Override
            public void onSuccess(final Object result) {

                final List<Map<String, Object>> myBookList = (List) result;

                final List<Book> bookList = new ArrayList<>();
                if(myBookList != null) {

                    for (final Map m : myBookList) {
                        String bid = (String) m.get("bid");
                        Book.initBookDetail(bid,new VolleyCallback() {
                            @Override
                            public void onSuccess(Object result) {
                                Book book = (Book) result;
                                book.setMyFirebaseData(m);
                                bookList.add((Book) result);

                            }
                        });


                    }
                    MyRequestQueue.getInstance().addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                        @Override
                        public void onRequestFinished(Request<Object> request) {
                            if(myBookList.size() == bookList.size()){
                                adapter.setBookList(bookList);
                                progressBar.setVisibility(View.GONE);
                                adapter.notifyDataSetChanged();
                                MyRequestQueue.getInstance().removeRequestFinishedListener(this);
                            }
                        }
                    });
                }


            }
        });


    }


    private void initFirebaseAuth() {
        mAuth = BooklogApplication.getmAuth();
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void singout() {
        mAuth.signOut();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }


    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Snackbar.make(getView(),"로그인 성공", 1000);
                            Log.d(TAG, "signInWithCredential:success");
                            adapterSetMyBookData();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
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
