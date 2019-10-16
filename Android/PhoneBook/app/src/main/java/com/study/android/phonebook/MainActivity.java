package com.study.android.phonebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";


    SingerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new SingerAdapter(this);

        String data = "img1,이병윤,010-2946-6716,m\n" +
                "img2,정민승,010-5621-8245,m\n" +
                "img3,김관호,010-4422-1669,m\n" +
                "img4,고성은,010-7340-7171,m\n" +
                "img5,노동현,010-8254-8754,m\n" +
                "img6,홍석우,010-7381-9117,m\n" +
                "img7,윤일노,010-7594-0214,m\n" +
                "img8,한진희,010-5344-9980,m\n" +
                "img9,김승환,010-4517-9330,m\n" +
                "img10,정승우,010-7172-5170,m\n" +
                "img11,양호열,010-4949-5102,m\n" +
                "img12,박지훈,010-4029-9306,m\n" +
                "img13,한태준,010-6361-4874,m\n" +
                "img14,최영수,010-2527-1657,m\n" +
                "img15,방동현,010-2357-6148,m\n" +
                "img16,김승현,010-5520-5215,m\n" +
                "img17,박다은,010-6739-1559,f\n" +
                "img18,송치민,010-3386-0316,m\n" +
                "img19,서기남,010-9249-4217,m\n" +
                "img20,박창환,010-3529-5523,m\n" +
                "img21,윤재필,010-7182-6895,m\n" +
                "img22,신혜성,010-5714-0314,m\n" +
                "img23,이시은,010-7743-1274,f\n" +
                "img24,윤송,010-3627-7217,f\n" +
                "img25,이유진,010-8798-2081,f\n" +
                "img26,선우은미,010-4179-4191,f\n" +
                "img27,허예진,010-6307-6025,f\n" +
                "img28,장소현,010-3666-1087,f\n" +
                "img29,조혜원,010-3948-0207,f";

        StringTokenizer st = new StringTokenizer(data, "\n");

        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            StringTokenizer st2 = new StringTokenizer(temp, ",");
            SingerItem item = new SingerItem();

            item.setResId(getResources().getIdentifier(st2.nextToken(),"drawable",this.getPackageName()));
            item.setName(st2.nextToken());
            item.setTelNum(st2.nextToken());
            item.setGender(st2.nextToken());
            adapter.addItem(item);
        }

        ListView listView1 = findViewById(R.id.listView);
        listView1.setAdapter(adapter);

    }


}
