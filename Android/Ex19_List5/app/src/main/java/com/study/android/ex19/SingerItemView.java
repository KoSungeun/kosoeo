package com.study.android.ex19;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    ImageView imageView1;
    Button button1;

    public SingerItemView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item_view, this, true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        imageView1 = findViewById(R.id.imageView1);
        button1 = findViewById(R.id.btnRemove);

    }

    public void setName(String name) {
        textView1.setText(name);
    }

    public void setAge(String age) {
        textView2.setText(age);
    }

    public void setImage(int imgNum) {
        imageView1.setImageResource(imgNum);
    }

    public Button getBtn() {
        return button1;
    }
}
