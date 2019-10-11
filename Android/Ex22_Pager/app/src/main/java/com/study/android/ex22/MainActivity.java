package com.study.android.ex22;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = "lecture";


    ViewPager pager1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager1 = findViewById(R.id.pager1);
        // 기본 3개 : 다음으로 숫자 조정
        pager1.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter();
        pager1.setAdapter(adapter);

    }

    public  void onBtn1Clicked(View v){
        pager1.setCurrentItem(0);

    }
    public  void onBtn2Clicked(View v){
        pager1.setCurrentItem(1);

    }
    public  void onBtn3Clicked(View v){
        pager1.setCurrentItem(2);

    }
    class MyPagerAdapter extends PagerAdapter {

        String [] names = { "홍길동", "강감찬", "을지문덕"};

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view.equals(o);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);

            pager1.removeView((View)object);

        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView view1 = new TextView(getApplicationContext());
            view1.setText(names[position]);
            view1.setTextSize(40.0f);
            view1.setTextColor(Color.BLUE);

            layout.addView(view1);

            pager1.addView(layout, position);

            return layout;
        }
    }
}
