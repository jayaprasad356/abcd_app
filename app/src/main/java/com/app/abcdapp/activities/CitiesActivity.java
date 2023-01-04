package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.app.abcdapp.Adapter.CitiesAdapter;
import com.app.abcdapp.R;
import com.app.abcdapp.model.Cities;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity {

    RecyclerView rvCityData;
    CitiesAdapter citiesAdapter;
    Activity activity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        activity = CitiesActivity.this;

        rvCityData = findViewById(R.id.rvCityData);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvCityData.setLayoutManager(linearLayoutManager);

        citylist();


    }

    private void citylist() {
        ArrayList<Cities> cities = new ArrayList<>();

        Cities rings1 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings2 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings3 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings4 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings5 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings6 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings7 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings8 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings9 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings10 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings11 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings12= new Cities("","Maharatra","12345687","Kumar");
        Cities rings13 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings14 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings15 = new Cities("","Maharatra","12345687","Kumar");
        Cities rings16= new Cities("","Maharatra","12345687","Kumar");
        Cities rings17= new Cities("","Maharatra","12345687","Kumar");
        Cities rings18= new Cities("","Maharatra","12345687","Kumar");
        Cities rings19= new Cities("","Maharatra","12345687","Kumar");
        Cities rings20= new Cities("","Maharatra","12345687","Kumar");



        cities.add(rings1);
        cities.add(rings2);
        cities.add(rings3);
        cities.add(rings4);
        cities.add(rings5);
        cities.add(rings6);
        cities.add(rings7);
        cities.add(rings8);
        cities.add(rings9);
        cities.add(rings10);
        cities.add(rings11);
        cities.add(rings12);
        cities.add(rings13);
        cities.add(rings14);
        cities.add(rings15);
        cities.add(rings16);
        cities.add(rings17);
        cities.add(rings18);
        cities.add(rings19);
        cities.add(rings20);



        citiesAdapter = new CitiesAdapter(activity,cities);
        rvCityData.setAdapter(citiesAdapter);




    }
}