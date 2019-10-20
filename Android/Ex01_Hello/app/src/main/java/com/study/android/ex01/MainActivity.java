package com.study.android.ex01;

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

    public void onBtn2Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }

    public void onBtn3Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01074693800"));
        startActivity(intent);
    }

    public void onBtn4Clicked(View v) {
        EditText editText = findViewById(R.id.editText1);
        TextView textView = findViewById(R.id.textView1);

        textView.setText(editText.getText());
    }

    public void onBtn5Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), NewActivity.class);
        intent.putExtra("CustomerName", "홍길동");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("lecture", "콜백 함수 호출됨");

        if(requestCode == 1 && resultCode == 10) {
            String sData = "";
            String str = "OnActivityResult() called : " +
                    requestCode + " : " +
                    resultCode;

            sData = data.getStringExtra("BackData");
            str = str + " : " + sData;

            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();



        }

    }
}
