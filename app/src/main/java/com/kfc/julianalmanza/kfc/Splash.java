package com.kfc.julianalmanza.kfc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    public static final long SPLASH_DELAY=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.supportRequestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        TimerTask task=new TimerTask() {
            @Override
            public void run() {

                SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                String usuario = prefs.getString("nombre","");
                if(usuario.equals("")){
                    Intent i=new Intent().setClass(Splash.this,Loggin.class);
                    startActivity(i);
                    finish();

                }else{
                    Intent i=new Intent().setClass(Splash.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }


            }
        };
        Timer timer=new Timer();
        timer.schedule(task,SPLASH_DELAY);
    }
}
