package com.study.android.ex24_fragment2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ViewerFragment extends Fragment {

    private static final String TAG = "lecture";

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);


        imageView = rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }


}
