package com.study.android.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    TextView tvName;
    TextView tvPhoneNum;
    ImageView imageView;
    Button telBtn;
    Button smsBtn;

    public SingerItemView(Context context, String Gender) {
        super(context);
        int layout;
        if(Gender.equals("m")) {
            layout = R.layout.singer_item_view;
        } else {
            layout = R.layout.singer_item_right_view;
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layout, this, true);

        tvName = findViewById(R.id.tvName);
        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        imageView = findViewById(R.id.imageView);
        telBtn = findViewById(R.id.telBtn);
        smsBtn = findViewById(R.id.smsBtn);

    }

    public void setName(String name) {
        tvName.setText(name);
    }

    public void setTel(String telNum) {
        tvPhoneNum.setText(telNum);
    }

    public void setImage(int imgNum) {
        imageView.setImageResource(imgNum);
    }

}
