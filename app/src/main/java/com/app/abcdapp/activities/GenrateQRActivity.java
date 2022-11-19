package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.app.abcdapp.R;
import com.app.abcdapp.helper.Session;

public class GenrateQRActivity extends AppCompatActivity {


    Handler handler;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genrate_qractivity);


        handler = new Handler();
        GotoActivity();
    }



    private void GotoActivity()
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {



                Intent intent=new Intent(GenrateQRActivity.this,MainActivity.class);
                startActivity(intent);
                finish();



            }
        },1000);
    }
}