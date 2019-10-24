package com.study.android.booklog.model;

import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.android.booklog.BestsellerCardRecyclerViewAdapter;
import com.study.android.booklog.MyRequestQueue;
import com.study.android.booklog.Translator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book {
    public String bid;
    public String gdid;
    public int rank;
    public String rankChange;
    public String subTitle;
    public String title;
    public String volume;
    public String authorEtcInfo;
    public List<Author> authorList;
    public List<Translator> translatorList;
    public boolean isAdult;
    public String coverUrl;


    public static void initBookList(final RecyclerView recyclerView) {
        RequestQueue queue = MyRequestQueue.getInstance();
        String url = "https://book.naver.com/bestsell/home_bestseller_json.nhn";

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("cp_cate", "");
            jsonRequest.put("cp_name", "kyobo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(url, jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray result = response.getJSONArray("result");
                    Gson gson = new Gson();
                    Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType();
                    List<Book> list = gson.fromJson(result.toString(), bookListType);

                    for(Book book : list) {
                        String covertedBid = "00000000".substring(0,8-book.bid.length()) + book.bid;
                        String coverUrl = "https://bookthumb-phinf.pstatic.net/cover/" + covertedBid.substring(0,3) + "/" + covertedBid.substring(3,6) + "/" + covertedBid + ".jpg";
                        book.setCoverUrl(coverUrl);
                    }
                    BestsellerCardRecyclerViewAdapter adapter = new BestsellerCardRecyclerViewAdapter(list);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);
    }



    public static void initBookDetail(String bid, final TextView textView) {
        RequestQueue queue = MyRequestQueue.getInstance();
        String url = "https://book.naver.com/bookdb/book_detail.nhn?bid=" + bid;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                textView.setText(response);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
                return headers;
            }
        };

        queue.add(stringRequest);
    }


    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
