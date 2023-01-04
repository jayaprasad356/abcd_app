package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.abcdapp.R;

public class FindMissingActivity extends AppCompatActivity {

    Button btnCities;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_missing);


        btnCities = findViewById(R.id.btnCities);


        btnCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindMissingActivity.this,CitiesActivity.class);
                startActivity(intent);
            }
        });
    }
}