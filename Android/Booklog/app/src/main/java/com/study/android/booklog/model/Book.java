package com.study.android.booklog.model;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.study.android.booklog.MyRequestQueue;
import com.study.android.booklog.Translator;
import com.study.android.booklog.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
    private String rating;


    public Book() {
        this.authorList = new ArrayList<>();
        this.translatorList = new ArrayList<>();
    }

    public static void getBestsellerBookList(final VolleyCallback callback) {
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

                    callback.onSuccess(list);

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



    public static void initBookDetail(final String bid, final VolleyCallback callback) {
        RequestQueue queue = MyRequestQueue.getInstance();
        String url = "https://book.naver.com/bookdb/book_detail.nhn?bid=" + bid;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Document document = Jsoup.parse(response);
                Elements bookInfo = document.select("div.book_info");
                Book book = new Book();
                book.setBid(bid);
                book.setTitle(bookInfo.select("h2>a").text());
                String rating = bookInfo.select("div.review_point2").next().text();
                rating = rating.substring(0, rating.length()-1);
                book.setRating(rating);
                Author author = new Author();
                author.setName(bookInfo.select("div>em").eq(0).next().text());
                book.getAuthorList().add(author);


                callback.onSuccess(book.getRating());
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                initBookDetail(bid, callback);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
                headers.put("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
                return headers;
            }
        };

        queue.add(stringRequest);
    }


    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getGdid() {
        return gdid;
    }

    public void setGdid(String gdid) {
        this.gdid = gdid;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getRankChange() {
        return rankChange;
    }

    public void setRankChange(String rankChange) {
        this.rankChange = rankChange;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getAuthorEtcInfo() {
        return authorEtcInfo;
    }

    public void setAuthorEtcInfo(String authorEtcInfo) {
        this.authorEtcInfo = authorEtcInfo;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<Translator> getTranslatorList() {
        return translatorList;
    }

    public void setTranslatorList(List<Translator> translatorList) {
        this.translatorList = translatorList;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
