package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.abcdapp.R;

public class FindMissingActivity extends AppCompatActivity {

    Button btnCities,btnpincode,btnVerfifyPincode,btnVerfifyCity,btnGenerate;
    LinearLayout llCityLocation,llPincode;
    EditText edCity,edPincode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_missing);


        btnCities = findViewById(R.id.btnCities);
        btnpincode = findViewById(R.id.btnpincode);
        llPincode = findViewById(R.id.llPincode);
        llCityLocation = findViewById(R.id.llCityLocation);
        btnVerfifyCity = findViewById(R.id.btnVerfifyCity);
        btnVerfifyPincode = findViewById(R.id.btnVerfifyPincode);
        edCity = findViewById(R.id.edCity);
        edPincode = findViewById(R.id.edPincode);
        btnGenerate = findViewById(R.id.btnGenerate);


        btnCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindMissingActivity.this,CitiesActivity.class);
                startActivity(intent);
            }
        });
        btnpincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindMissingActivity.this,PincodeActivity.class);
                startActivity(intent);
            }
        });

        btnVerfifyCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FindMissingActivity.this, "Verify Successfully", Toast.LENGTH_SHORT).show();
                llPincode.setVisibility(View.VISIBLE);
                btnpincode.setVisibility(View.VISIBLE);
                btnCities.setVisibility(View.GONE);
                btnVerfifyCity.setVisibility(View.GONE);
                edCity.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_24), null);
                edCity.setEnabled(false);
            }
        });
        btnVerfifyPincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FindMissingActivity.this, "Verify Successfully", Toast.LENGTH_SHORT).show();

                btnpincode.setVisibility(View.GONE);
                btnCities.setVisibility(View.GONE);
                btnVerfifyPincode.setVisibility(View.GONE);
                edPincode.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_24), null);
                btnGenerate.setVisibility(View.VISIBLE);
                edPincode.setEnabled(false);


            }
        });



    }
}