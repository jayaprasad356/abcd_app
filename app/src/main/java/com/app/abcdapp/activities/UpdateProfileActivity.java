package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.abcdapp.R;
import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateProfileActivity extends AppCompatActivity {


    EditText EtName,EtPhoneNo,edDOB,EtEmail,EtCity,EtCode,EtPassword;
    LinearLayout llDob;
    ImageView backbtn;
    Button btnUpdate;
    Session session;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        activity = UpdateProfileActivity.this;
        session = new Session(activity);


        edDOB = findViewById(R.id.edDOB);
        llDob = findViewById(R.id.llDob);
        backbtn = findViewById(R.id.backbtn);
        EtName = findViewById(R.id.EtName);
        EtPhoneNo = findViewById(R.id.EtPhoneNo);
        EtEmail = findViewById(R.id.EtEmail);
        EtCity = findViewById(R.id.EtCity);
        EtPassword = findViewById(R.id.EtPassword);
        userDetails();




        edDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our text view.
                        edDOB.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();

            }
        });

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EtName.getText().toString().trim().equals("") ){
                    Toast.makeText(activity, "Name is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtPhoneNo.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "Phone Number is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtPhoneNo.getText().length() != 10){
                    Toast.makeText(activity, "Phone Number is invalid", Toast.LENGTH_SHORT).show();
                }
                else if (edDOB.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "DOB is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtEmail.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "Email is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtCity.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "City is empty", Toast.LENGTH_SHORT).show();
                }

                else if (EtPassword.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "Password is empty", Toast.LENGTH_SHORT).show();
                }
                else{


                    updateUser();


//                    Intent intent = new Intent(activity,LoginActivity.class);
//                    startActivity(intent);

                }
            }
        });



        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
        
        


    private void userDetails()


    {


        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        EtName.setText(jsonArray.getJSONObject(0).getString(Constant.NAME));
                        EtEmail.setText(jsonArray.getJSONObject(0).getString(Constant.EMAIL));
                        EtPhoneNo.setText(jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        edDOB.setText(jsonArray.getJSONObject(0).getString(Constant.DOB));
                        EtCity.setText(jsonArray.getJSONObject(0).getString(Constant.CITY));
                        EtPassword.setText(jsonArray.getJSONObject(0).getString(Constant.PASSWORD));
                 }

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, activity, Constant.USER_DETAILS_URL, params,true);



    }


    private void updateUser() {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID, session.getData(Constant.ID));
        params.put(Constant.NAME, EtName.getText().toString().trim());
        params.put(Constant.EMAIL, EtEmail.getText().toString().trim());
        params.put(Constant.CITY, EtCity.getText().toString().trim());
        params.put(Constant.MOBILE,EtPhoneNo .getText().toString().trim());
        params.put(Constant.PASSWORD, EtPassword.getText().toString().trim());
        params.put(Constant.DOB, edDOB.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.EARN,jsonArray.getJSONObject(0).getString(Constant.EARN));
                        session.setData(Constant.WITHDRAWAL,jsonArray.getJSONObject(0).getString(Constant.WITHDRAWAL));
                        session.setInt(Constant.TOTAL_CODES,Integer.parseInt(jsonArray.getJSONObject(0).getString(Constant.TOTAL_CODES)));
                        session.setInt(Constant.TODAY_CODES,Integer.parseInt(jsonArray.getJSONObject(0).getString(Constant.TODAY_CODES)));
                        session.setData(Constant.BALANCE,jsonArray.getJSONObject(0).getString(Constant.BALANCE));
                        session.setData(Constant.REFER_CODE,jsonArray.getJSONObject(0).getString(Constant.REFER_CODE));

                        startActivity(new Intent(activity, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, activity, Constant.UPDATE_USER_URL, params, true);


    }

}