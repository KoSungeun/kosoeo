package com.study.android.phonebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class SingerAdapter extends BaseAdapter {

    private  static final String TAG = "lecture";

    Context context;
    ArrayList<SingerItem> items = new ArrayList<>();

    public SingerAdapter(Context context) {
        this.context = context;
    }

    public void addItem(SingerItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        final SingerItem item = items.get(position);
        SingerItemView view = new SingerItemView(context, item.getGender());



        view.setName(item.getName());
        view.setTel(item.getTelNum());
        view.setImage(item.getResId());
        Button button1 = view.findViewById(R.id.telBtn);
        button1.setFocusable(false);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "tel:" + item.getTelNum();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str));
                context.startActivity(intent);
            }
        });


        Button button2 = view.findViewById(R.id.smsBtn);
        button2.setFocusable(false);


        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "sms:" + item.getTelNum();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str));
                context.startActivity(intent);
            }
        });

        return view;
    }
}
