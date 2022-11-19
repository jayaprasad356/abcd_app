package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.R;
import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView tvSignup;
    EditText EtPhoneNumber,EtPassword;
    Button btnLogin;
    Session session;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         activity = LoginActivity.this;
        session = new Session(activity);

        btnLogin = findViewById(R.id.btnLogin);
        EtPhoneNumber = findViewById(R.id.EtPhoneNumber);
        EtPassword = findViewById(R.id.EtPassword);
        tvSignup = findViewById(R.id.tvSignup);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApprovalAlertdialog();

//                if(EtPhoneNumber.getText().toString().trim().equals("") ){
//                    Toast.makeText(LoginActivity.this, "Phone Number is empty", Toast.LENGTH_SHORT).show();
//                }
//                else if (EtPassword.getText().toString().trim().equals("")){
//                    Toast.makeText(LoginActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
//                }
//                else{
//
//
//                    Login();
////                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                    startActivity(intent);
//                }
            }
        });
    }

    private void Login() {

        Map<String, String> params = new HashMap<>();
        params.put(Constant.MOBILE,EtPhoneNumber.getText().toString().trim());
        params.put(Constant.PASSWORD,EtPassword.getText().toString().trim());
        params.put(Constant.DEVICE_ID,Constant.getDeviceId(activity));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        if (jsonObject.getBoolean(Constant.DEVICE_VERIFY)){
                            JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                            Toast.makeText(this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                            session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                            session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                            session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                            session.setData(Constant.EARN,jsonArray.getJSONObject(0).getString(Constant.EARN));
                            session.setData(Constant.WITHDRAWAL,jsonArray.getJSONObject(0).getString(Constant.WITHDRAWAL));
                            session.setInt(Constant.TOTAL_CODES,Integer.parseInt(jsonArray.getJSONObject(0).getString(Constant.TOTAL_CODES)));
                            session.setInt(Constant.TODAY_CODES,Integer.parseInt(jsonArray.getJSONObject(0).getString(Constant.TODAY_CODES)));
                            session.setData(Constant.BALANCE,jsonArray.getJSONObject(0).getString(Constant.BALANCE));
                            session.setData(Constant.REFER_CODE,jsonArray.getJSONObject(0).getString(Constant.REFER_CODE));

                            if (session.getBoolean(Constant.IMPORT_DATA)){
                                session.setBoolean("is_logged_in", true);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();

                            }else {
                                startActivity(new Intent(LoginActivity.this, ImportDataActivity.class));
                                finish();
                            }

                        }
                       else {

                           showAlertdialog();
                        }

                    }
                    else {
                        Toast.makeText(this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, LoginActivity.this, Constant.LOGIN_URL, params,true);



    }

    private void showAlertdialog() {


        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Would you request to change device ?");
        builder.setTitle("Device verification failed !");
        builder.setCancelable(false);
        builder.setPositiveButton("Send request to admin", (DialogInterface.OnClickListener) (dialog, which) -> {
            Toast.makeText(activity, "Request sent Successfully, Wait for conformation", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }
    private void ApprovalAlertdialog() {


        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Please Contact Admin");
        builder.setTitle("Wait for Approval");
        builder.setCancelable(false);
        builder.setPositiveButton("Send request to admin", (DialogInterface.OnClickListener) (dialog, which) -> {
            Toast.makeText(activity, "Request sent Successfully, Wait for conformation", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }

}