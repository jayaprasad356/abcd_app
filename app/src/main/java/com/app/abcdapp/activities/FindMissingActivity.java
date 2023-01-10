package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.Adapter.CitiesAdapter;
import com.app.abcdapp.Adapter.PincodeAdapter;
import com.app.abcdapp.R;
import com.app.abcdapp.fragment.GenrateQRFragment;
import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.DatabaseHelper;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.model.GenerateCodes;

import java.util.ArrayList;
import java.util.Random;

public class FindMissingActivity extends AppCompatActivity {

    Button btnCities,btnpincode,btnVerfifyPincode,btnVerfifyCity,btnGenerate;
    LinearLayout llCityLocation,llPincode;
    EditText edCity,edPincode;
    Dialog dialog;
    CitiesAdapter citiesAdapter;
    PincodeAdapter pincodeAdapter;
    Activity activity;
    DatabaseHelper databaseHelper;
    RecyclerView rvCityData,rvPincodeData;
    EditText edName;
    TextView tvSkip;
    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four,otp_textbox_five,otp_textbox_six,otp_textbox_seven,otp_textbox_eight,otp_textbox_nine,otp_textbox_ten;

    Session session;

    ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_missing);
        activity = FindMissingActivity.this;
        session = new Session(activity);
        databaseHelper = new DatabaseHelper(activity);




        btnCities = findViewById(R.id.btnCities);
        btnpincode = findViewById(R.id.btnpincode);
        llPincode = findViewById(R.id.llPincode);
        llCityLocation = findViewById(R.id.llCityLocation);
        btnVerfifyCity = findViewById(R.id.btnVerfifyCity);
        btnVerfifyPincode = findViewById(R.id.btnVerfifyPincode);
        edName = findViewById(R.id.edName);
        edCity = findViewById(R.id.edCity);
        edPincode = findViewById(R.id.edPincode);
        btnGenerate = findViewById(R.id.btnGenerate);
        tvSkip = findViewById(R.id.tvSkip);

        otp_textbox_one = findViewById(R.id.otp_edit_box1);
        otp_textbox_two = findViewById(R.id.otp_edit_box2);
        otp_textbox_three = findViewById(R.id.otp_edit_box3);
        otp_textbox_four = findViewById(R.id.otp_edit_box4);
        otp_textbox_five = findViewById(R.id.otp_edit_box5);
        otp_textbox_six = findViewById(R.id.otp_edit_box6);
        otp_textbox_seven = findViewById(R.id.otp_edit_box7);
        otp_textbox_eight = findViewById(R.id.otp_edit_box8);
        otp_textbox_nine = findViewById(R.id.otp_edit_box9);
        otp_textbox_ten = findViewById(R.id.otp_edit_box10);



        generateCodes = databaseHelper.getMissingCodes();
        final int min = 0;
        final int max = 99;
        //final int random = new Random().nextInt((max - min) + 1) + min;
        final int random = 1;
        edName.setText(generateCodes.get(random).getStudent_name());
        setIdValue(generateCodes.get(random).getId_number());
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,FindMissingActivity.class);
                startActivity(intent);
                finish();
            }
        });




        btnCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(activity);
                dialog.setContentView(R.layout.cities_dialog_layout);
                rvCityData = dialog.findViewById(R.id.rvCityData);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
                rvCityData.setLayoutManager(linearLayoutManager);

                citylist();
                dialog.show();
//                Intent intent = new Intent(FindMissingActivity.this,CitiesActivity.class);
//                startActivity(intent);
            }
        });
        btnpincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(activity);
                dialog.setContentView(R.layout.pincode_dialog_layout);
                rvPincodeData = dialog.findViewById(R.id.rvPincodeData);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
                rvPincodeData.setLayoutManager(linearLayoutManager);

                pincodelist();
                dialog.show();
//                Intent intent = new Intent(FindMissingActivity.this,PincodeActivity.class);
//                startActivity(intent);
            }
        });

        btnVerfifyCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edCity.getText().toString().trim().equals("")){
                    if (generateCodes.get(random).getEcity().equals(edCity.getText().toString().trim())){
                        Toast.makeText(FindMissingActivity.this, "Verify Successfully", Toast.LENGTH_SHORT).show();

                        llPincode.setVisibility(View.VISIBLE);
                        btnpincode.setVisibility(View.VISIBLE);
                        btnCities.setVisibility(View.GONE);
                        btnVerfifyCity.setVisibility(View.GONE);
                        edCity.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_24), null);
                        edCity.setEnabled(false);

                    }
                    else {
                        Toast.makeText(FindMissingActivity.this, "City Wrong", Toast.LENGTH_SHORT).show();


                    }

                }

            }
        });
        btnVerfifyPincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edPincode.getText().toString().trim().equals("")){
                    if (generateCodes.get(random).getPin_code().equals(edPincode.getText().toString().trim())){
                        Toast.makeText(FindMissingActivity.this, "Verify Successfully", Toast.LENGTH_SHORT).show();
                        btnpincode.setVisibility(View.GONE);
                        btnCities.setVisibility(View.GONE);
                        btnVerfifyPincode.setVisibility(View.GONE);
                        edPincode.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_24), null);
                        btnGenerate.setVisibility(View.VISIBLE);
                        edPincode.setEnabled(false);

                    }
                    else {
                        Toast.makeText(FindMissingActivity.this, "Pincode Wrong", Toast.LENGTH_SHORT).show();


                    }

                }

            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ApiConfig.isConnected(activity)){
                    if (session.getData(Constant.CODE_GENERATE).equals("1")){
                        session.setInt(Constant.CODES,session.getInt(Constant.CODES) + 1);
                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.Container, new GenrateQRFragment()).commit();

                    }else {
                        Toast.makeText(activity, "You are Restricted for Generating Code", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }

    private void pincodelist() {
        ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
        generateCodes = databaseHelper.getMissingCodes();

        //pincodeAdapter = new PincodeAdapter(activity,generateCodes,dialog);
        rvPincodeData.setAdapter(pincodeAdapter);



    }

    private void setIdValue(String id_number)
    {
        String id1,id2,id3,id4,id5,id6,id7,id8,id9,id10;
        id1=id_number.substring(0,1);
        id2=id_number.substring(1,2);
        id3=id_number.substring(2,3);
        id4=id_number.substring(3,4);
        id5=id_number.substring(4,5);
        id6=id_number.substring(5,6);
        id7=id_number.substring(6,7);
        id8=id_number.substring(7,8);
        id9=id_number.substring(8,9);
        id10=id_number.substring(9,10);
        otp_textbox_one.setText(id1);
        otp_textbox_two.setText(id2);
        otp_textbox_three.setText(id3);
        otp_textbox_four.setText(id4);
        otp_textbox_five.setText(id5);
        otp_textbox_six.setText(id6);
        otp_textbox_seven.setText(id7);
        otp_textbox_eight.setText(id8);
        otp_textbox_nine.setText(id9);
        otp_textbox_ten.setText(id10);

    }

    public void setCityValue(String ecity) {
        edCity.setText(ecity);
    }
    public void setPincodeValue(String pincode) {
        edPincode.setText(pincode);
    }


    private void citylist() {
        ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
        generateCodes = databaseHelper.getMissingCodes();

        //citiesAdapter = new CitiesAdapter(activity,generateCodes,dialog);
        rvCityData.setAdapter(citiesAdapter);




    }
}