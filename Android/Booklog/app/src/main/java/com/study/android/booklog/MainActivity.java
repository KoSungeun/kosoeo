package com.study.android.booklog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        String url = "https://book.naver.com/bestsell/bestseller_list.nhn";

        GetAction myGetAct = new GetAction();
        myGetAct.execute(url);

    }

    class GetAction extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... strings) {
            String sOutput = "";

            try{

                Connection con = Jsoup.connect(strings[0]).userAgent("Mozilla")
                        .cookie("auth", "token").timeout(3000);
                Connection.Response resp = con.execute();

                Document doc = null;
                if(resp.statusCode() == 200) {
                    sOutput = con.get().toString();
                }
            }catch (Exception ex) {
                Log.d(TAG, "Exception in processing response.", ex);
            }

            return sOutput;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
//            webView.loadData(s, "text/html; charset=UTF-8", null);
        }
    }




}
