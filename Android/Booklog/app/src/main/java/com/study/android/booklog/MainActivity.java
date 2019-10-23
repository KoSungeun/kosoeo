package com.study.android.booklog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements NavigationHost {

    private static final String TAG = "lecture";

    private BestsellerFragment bestsellerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bestsellerFragment = new BestsellerFragment();
        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, bestsellerFragment)
                    .commit();
        }
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {



        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(bestsellerFragment)
                        .add(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }



        transaction.commit();
    }
}

