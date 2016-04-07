package com.gennaroallocca.myproject;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class Intro extends Activity {

    MediaPlayer mp = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

         mp = MediaPlayer.create(this,R.raw.sing);
        mp.start();

        Thread splash = new Thread() {

            public void run() {
                int timer = 0;

                while (timer <= 5000) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timer = timer + 100;
                }
                startActivity(new Intent("com.gennaroallocca.myproject.LOGINACTIVITY"));
                finish();
            }
        };
        splash.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }
}