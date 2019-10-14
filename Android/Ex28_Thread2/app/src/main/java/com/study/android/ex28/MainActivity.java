package com.study.android.ex28;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    TextView textView1;
    Button button1;
    Handler handler;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3: 추가한 클래스를 이요한 핸들러 변수 만들기
        handler = new Handler();

        textView1 = findViewById(R.id.textView1);
        progressBar1 = findViewById(R.id.progressBar1);

        button1 = findViewById(R.id.button1);
    }

    public void onBtn1Clicked(View v) {
        progressBar1.setProgress(0);
        RequestThread thread = new RequestThread();
        thread.start();
    }

    class RequestThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                Log.d(TAG, "Request Thread .. " + i);

                // 1: 쓰레드에서 메인쓰레드의 객체로의 접근은 불가능
                //textView1.setText("Request Thread .. " + i);
                final int index = i;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setText("Reuqest Thread .. " + index);
                        progressBar1.incrementProgressBy(1);
                    }
                });


                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
