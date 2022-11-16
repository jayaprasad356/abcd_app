package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.app.abcdapp.R;
import com.app.abcdapp.helper.Session;

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        handler = new Handler();
        GotoActivity();
    }


    private void GotoActivity()
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Session session = new Session(SplashActivity.this);
                if (session.getBoolean("is_logged_in")){
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }



            }
        },2000);
    }
}