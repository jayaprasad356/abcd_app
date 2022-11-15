package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
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
        EtCode = findViewById(R.id.EtCode);
        EtPassword = findViewById(R.id.EtPassword);
       // userDetails();




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
                else if (edDOB.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "DOB is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtEmail.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "Email is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtCity.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "City is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtCode.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "Code is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtPassword.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "Password is empty", Toast.LENGTH_SHORT).show();
                }
                else{


                  //  updateUser();


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
        
        

//
//    private void userDetails()
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constant.USER_ID,session.getData(Constant.ID));
//        ApiConfig.RequestToVolley((result, response) -> {
//            if (result) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
//                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
//                        spinOccupation.setSelection(getIndex(spinOccupation, jsonArray.getJSONObject(0).getString(Constant.OCCUPATION)));
//                        spinGender.setSelection(getIndex(spinGender, jsonArray.getJSONObject(0).getString(Constant.GENDER)));
//                        etName.setText(jsonArray.getJSONObject(0).getString(Constant.NAME));
//                        etEmail.setText(jsonArray.getJSONObject(0).getString(Constant.EMAIL));
//                        etMobile.setText(jsonArray.getJSONObject(0).getString(Constant.MOBILE));
//                        etAddress.setText(jsonArray.getJSONObject(0).getString(Constant.ADDRESS));
//                        etVillageName.setText(jsonArray.getJSONObject(0).getString(Constant.VILLAGE));
//                        etPincode.setText(jsonArray.getJSONObject(0).getString(Constant.PINCODE));
//                        etDistrict.setText(jsonArray.getJSONObject(0).getString(Constant.DISTRICT));
//                    }
//
//                } catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        }, activity, Constant.USER_DETAILS_URL, params,true);
//
//
//
//    }
}