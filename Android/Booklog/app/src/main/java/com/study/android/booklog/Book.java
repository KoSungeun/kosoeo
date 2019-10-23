package com.study.android.booklog;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Book {
    String bid;
    String gdid;
    int rank;
    String rankChange;
    String subTitle;
    String title;
    String volume;
    String authorEtcInfo;
    List<Author> authorList;
    List<Translator> translatorList;
    boolean isAdult;
    String coverUrl;


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
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
