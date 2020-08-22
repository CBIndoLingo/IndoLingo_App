package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        logolauncher logolauncher = new logolauncher();
        logolauncher.start();
    }

    private class logolauncher extends Thread{
        @Override
        public void run() {
            try{
                sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart",true);
            if (firstStart){
                Intent intent = new Intent(splash_screen.this,welcome.class);
                startActivity(intent);
                splash_screen.this.finish();
                SharedPreferences prefs2 = getSharedPreferences("prefs",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs2.edit();
                editor.putBoolean("firstStart",false);
                editor.apply();
            }
            else{
            Intent intent = new Intent(splash_screen.this,sign_in.class);
            startActivity(intent);
            splash_screen.this.finish();
            }
        }
    }
}
