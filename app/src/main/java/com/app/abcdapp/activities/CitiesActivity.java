package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import com.app.abcdapp.Adapter.CitiesAdapter;
import com.app.abcdapp.R;
import com.app.abcdapp.helper.DatabaseHelper;
import com.app.abcdapp.model.GenerateCodes;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity {

    RecyclerView rvCityData;
    CitiesAdapter citiesAdapter;
    Activity activity;
    DatabaseHelper databaseHelper;
    Dialog dialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        activity = CitiesActivity.this;
        databaseHelper = new DatabaseHelper(activity);

        rvCityData = findViewById(R.id.rvCityData);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvCityData.setLayoutManager(linearLayoutManager);

        citylist();


    }

    private void citylist() {
        ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
        generateCodes = databaseHelper.getMissingCodes();


       // citiesAdapter = new CitiesAdapter(activity,generateCodes, dialog);
        rvCityData.setAdapter(citiesAdapter);




    }
}