package com.study.android.ex19;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SingerAdapter extends BaseAdapter {

    Context context;
    ArrayList<SingerItem> items = new ArrayList<>();

    public SingerAdapter(Context context) {
        this.context = context;
    }

    public  void addItem(SingerItem item) {
        items.add(item);
    }
    public void removeItem(int position) {
        items.remove(position);
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

        SingerItemView view;
        if(convertView == null) {
            view = new SingerItemView(context);
        } else {
            view = (SingerItemView) convertView;
        }

        SingerItem item = items.get(position);
        view.setName(item.getName());
        view.setAge(item.getAge());
        view.setImage(item.getResId());
        view.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
