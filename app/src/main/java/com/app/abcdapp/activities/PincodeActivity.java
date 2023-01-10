package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.app.abcdapp.Adapter.CitiesAdapter;
import com.app.abcdapp.Adapter.PincodeAdapter;
import com.app.abcdapp.R;
import com.app.abcdapp.helper.DatabaseHelper;
import com.app.abcdapp.model.GenerateCodes;
import com.app.abcdapp.model.Pincode;

import java.util.ArrayList;

public class PincodeActivity extends AppCompatActivity {


    RecyclerView rvPincode;
    PincodeAdapter pincodeAdapter;
    Activity activity;
    DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode);
        activity = PincodeActivity.this;
        databaseHelper = new DatabaseHelper(activity);


        rvPincode = findViewById(R.id.rvPincode);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvPincode.setLayoutManager(linearLayoutManager);

      pincode();

    }

    private void pincode() {

        ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
        generateCodes = databaseHelper.getMissingCodes();

        //pincodeAdapter = new PincodeAdapter(activity,generateCodes);
        rvPincode.setAdapter(pincodeAdapter);

    }
}