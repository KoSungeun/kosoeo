package com.study.android.ex42;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("lecture","뜨냐");
        Toast.makeText(context, "지정한 시간입니다.", Toast.LENGTH_LONG).show();
    }
}
