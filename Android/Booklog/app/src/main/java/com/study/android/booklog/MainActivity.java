package com.study.android.booklog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.study.android.booklog.Fragment.BestsellerFragment;
import com.study.android.booklog.Fragment.SearchFragment;
import com.study.android.booklog.Fragment.MyBookFragment;


public class MainActivity extends AppCompatActivity implements NavigationHost {

    private static final String TAG = "lecture";

    private BestsellerFragment bestsellerFragment;
    private MyBookFragment myBookFragment;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        bestsellerFragment = new BestsellerFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_bestseller:
                        selectedFragment = bestsellerFragment;
                        break;
                    case R.id.action_mybook:
                        selectedFragment = new MyBookFragment();
                        break;
                    case R.id.action_search:
                        selectedFragment = new SearchFragment();
                        break;
                }
                navigateTo(selectedFragment, false);

                return true;
            }
        });



        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }



        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, bestsellerFragment,"cur")
                    .commit();
        }



    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {



        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .show(fragment)
                        .replace(R.id.container, fragment, "cur");

        Fragment detail = getSupportFragmentManager().findFragmentByTag("detail");
        if(detail != null) {
            transaction.remove(detail);

        }
        if (addToBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void navigateAdd(Fragment fragment, boolean addToBackstack) {

        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .hide(getSupportFragmentManager().findFragmentByTag("cur"))
                        .add(R.id.container, fragment, "detail");

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}

