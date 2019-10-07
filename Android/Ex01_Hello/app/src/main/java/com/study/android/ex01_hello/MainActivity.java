package com.study.android.ex01_hello;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lecture", "로그 출력");
                Toast.makeText(getApplicationContext(), "긴 토스트", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onButton2Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }

    public void onButton3Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01074693800"));
        startActivity(intent);
    }

    public void onButton4Clicked(View v) {
        EditText editText = findViewById(R.id.editText1);
        TextView textView = findViewById(R.id.textView1);

        textView.setText(editText.getText());
    }
}
