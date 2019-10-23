package com.study.android.booklog;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyRequestQueue {

    private static RequestQueue requestQueue;
    public static RequestQueue getInstance() {
        if(requestQueue == null) {
           requestQueue = Volley.newRequestQueue(BooklogApplication.getAppContext());
        }
        return requestQueue;

    }
}
