package com.study.android.ex_38;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    MediaPlayer mp = null;
    int playbackPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBtn1Clicked(View v) {
        mp = MediaPlayer.create(this, R.raw.old_pop);
        mp.seekTo(0);
        mp.start();
    }

    public void onBtn2Clicked(View v) {
        if(mp != null) {
            mp.pause();
            playbackPosition = mp.getCurrentPosition();
        }

    }

    public void onBtn3Clicked(View v) {
        if(mp != null) {
            mp.seekTo(playbackPosition);
            mp.start();
        }
    }

    public void onBtn4Clicked(View v) {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        mp = null;
    }

    public void onBtn5Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
        startActivity(intent);
    }
}
