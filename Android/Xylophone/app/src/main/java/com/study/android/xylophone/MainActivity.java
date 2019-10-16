package com.study.android.xylophone;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
    }


    public void play(int raw) {
        MediaPlayer mp = MediaPlayer.create(this, raw);
        mp.seekTo(0);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    public void onDoBtn1Clicked(View v){
        play(R.raw.do1);
    }
    public void onReBtnClicked(View v){
        play(R.raw.re);

    }
    public void onMiBtnClicked(View v){
        play(R.raw.mi);
    }
    public void onFaBtnClicked(View v){
        play(R.raw.fa);

    }
    public void onSolBtnClicked(View v){
        play(R.raw.sol);

    }

    public void onRaBtnClicked(View v){
        play(R.raw.ra);

    }
    public void onSiBtnClicked(View v){
        play(R.raw.si);

    }

    public void onDoBtn2Clicked(View v){
        play(R.raw.do2);
    }

}
