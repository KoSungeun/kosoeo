package com.study.android.ex06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    EditText inputMessage;
    String strAmount = ""; // 임시저장값 (콤마)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = findViewById(R.id.etMessage);

        inputMessage.addTextChangedListener(watcher);
    }

    TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            strAmount = s.toString();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d(TAG, s.toString() + ":" + strAmount);

            if(!s.toString().equals(strAmount)) {
                strAmount = makeStringComma(s.toString().replace(",",""));
                inputMessage.setText(strAmount);
                inputMessage.setSelection(inputMessage.getText().length(), inputMessage.getText().length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    };

    protected String makeStringComma(String str) {
        if (str.length() == 0)
            return  "";
        long value = Long.parseLong(str);
        DecimalFormat format = new DecimalFormat("###,##0");
        return format.format(value);
    }
}
