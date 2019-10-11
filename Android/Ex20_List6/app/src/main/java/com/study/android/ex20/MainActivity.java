package com.study.android.ex20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = "lecture";


    SingerAdapter adapter;

    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new SingerAdapter(this);

        SingerItem item1 = new SingerItem("홍길동", "010-1234-5678", R.drawable.face1);
        adapter.addItem(item1);
        SingerItem item2 = new SingerItem("이순신", "010-4321-9876", R.drawable.face2);
        adapter.addItem(item2);
        SingerItem item3 = new SingerItem("김유신", "010-5678-4321", R.drawable.face3);
        adapter.addItem(item3);

        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "selected : " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

    }

    public void onBtn1Clicked(View v) {
        String inputName = editText1.getText().toString();
        String inputTelNum = editText2.getText().toString();

        int inputImage = R.drawable.face1;
        Random r = new Random();
        switch (r.nextInt(3)) {
            case 0:
                inputImage = R.drawable.face1;
                break;
            case 1:
                inputImage = R.drawable.face2;
                break;
            case 2:
                inputImage = R.drawable.face3;
                break;
        }


        SingerItem item = new SingerItem(inputName, inputTelNum, inputImage);
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }

}
