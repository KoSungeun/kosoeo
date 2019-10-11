package com.study.android.ex18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = "lecture";

    MyAdapter adapter;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyAdapter();


        SingerItem item1 = new SingerItem("홍길동", "20", R.drawable.face1);
        adapter.addItem(item1);
        SingerItem item2 = new SingerItem("이순신", "25", R.drawable.face2);
        adapter.addItem(item2);
        SingerItem item3 = new SingerItem("김유신", "30", R.drawable.face3);
        adapter.addItem(item3);

        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        // 4단계
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
        String inputAge = editText2.getText().toString();

        SingerItem item = new SingerItem(inputName, inputAge, R.drawable.face1);
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }

    class  MyAdapter extends BaseAdapter {

        ArrayList<SingerItem> items = new ArrayList<>();

        public  void addItem(SingerItem item) {
            items.add(item);
        }
        public void removeItem(int position) {
            items.remove(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setAge(item.getAge());
            view.setImage(item.getResId());
            view.getBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(position);
                    adapter.notifyDataSetChanged();
                }
            });

            return view;
        }
    }
}
