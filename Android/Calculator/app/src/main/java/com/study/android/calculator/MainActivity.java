package com.study.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    TextView progressText;
    TextView resultText;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressText = findViewById(R.id.progress);
        resultText = findViewById(R.id.result);
    }

    public void onBtnClick(View v){
        String sProgressText = progressText.getText().toString();
        String Tag = v.getTag().toString();
        switch (Tag) {
            case "del":
                if(sProgressText.length() > 0) {
                    sProgressText = sProgressText.substring(0, sProgressText.length() - 1);
                }
                break;
            case "*":
            case "/":
            case "+":
            case "-":
                if(sProgressText.lastIndexOf("*") == sProgressText.length()-1 || sProgressText.lastIndexOf("+") == sProgressText.length()-1 || sProgressText.lastIndexOf("/") == sProgressText.length()-1 || sProgressText.lastIndexOf("-") == sProgressText.length()-1) {
                    if(sProgressText.length() > 0) {
                        sProgressText = sProgressText.substring(0, sProgressText.length() - 1);
                    }
                }
                sProgressText += Tag;
                break;
            case "=":
                StringTokenizer st = new StringTokenizer(progressText.getText().toString(), "+-*/", true);
                String calc;
                while(st.hasMoreTokens()){
                    String token = st.nextToken();
                    double result = 0;
                    try {
                        result = Double.parseDouble(token);
                    } catch (NumberFormatException e) {
                        calc = token;
                    }
                }
                break;
            default:
                sProgressText += Tag;
        }
        progressText.setText(sProgressText);

    }



}
