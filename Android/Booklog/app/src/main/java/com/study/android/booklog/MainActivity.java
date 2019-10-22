package com.study.android.booklog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements NavigationHost {

    private static final String TAG = "lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new BestsellerFragment())
                    .commit();
        }



//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "https://book.naver.com/bestsell/home_bestseller_json.nhn?cp_cate=&cp_name=kyobo";
//
//        JSONObject jsonRequest = new JSONObject();
//        try {
//            jsonRequest.put("cp_cate", "");
//            jsonRequest.put("cp_name", "kyobo");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(url, jsonRequest, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                String text = "";
//                try {
//                    JSONArray result = response.getJSONArray("result");
//                    for(int i = 0; i < result.length(); i++) {
//                        JSONObject item = result.getJSONObject(i);
//                        String title = item.getString("title");
//                        String name = "";
//                        JSONArray authorList = item.getJSONArray("authorList");
//                        for(int j = 0; j < authorList.length(); j++) {
//                            JSONObject Author = authorList.getJSONObject(j);
//                            name = Author.getString("name");
//                        }
//                        text += title + " " + name + "\n";
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        });
//
//        queue.add(stringRequest);
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}

