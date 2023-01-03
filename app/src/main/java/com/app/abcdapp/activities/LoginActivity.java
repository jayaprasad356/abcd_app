package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.R;
import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.helper.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button btnSignUp;
    EditText EtPhoneNumber,EtPassword;
    Button btnLogin;
    Session session;
    Activity activity;
    String Mobile,Password;
    TextView tvMakePayment;
    ImageView imgMenu;
    CheckBox checkTerms;
    LinearLayout whatsppjoin;
    TextView tvTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activity = LoginActivity.this;
        session = new Session(activity);

        btnLogin = findViewById(R.id.btnLogin);
        tvMakePayment = findViewById(R.id.tvMakePayment);
        EtPhoneNumber = findViewById(R.id.EtPhoneNumber);
        EtPassword = findViewById(R.id.EtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        imgMenu = findViewById(R.id.imgMenu);
        checkTerms = findViewById(R.id.checkTerms);
        whatsppjoin = findViewById(R.id.whatsppjoin);
        tvTerms = findViewById(R.id.tvTerms);

        EtPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_show, 0);


        Utils.setHideShowPassword(EtPassword);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(""+session.getData(Constant.JOB_DETAILS_LINK)); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpopup(view);
            }
        });

        whatsppjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWhatsApp();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EtPhoneNumber.getText().toString().trim().equals("") ){
                    Toast.makeText(LoginActivity.this, "Phone Number is empty", Toast.LENGTH_SHORT).show();
                }
                else if (EtPassword.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
                }
                else if (!checkTerms.isChecked()){
                    Toast.makeText(LoginActivity.this, "Please Check the box and login", Toast.LENGTH_SHORT).show();
                }
                else{


                    Login();
                }
            }
        });



        tvMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(session.getData(Constant.PAYMENT_LINK)));
                    startActivity(intent);
                }catch (Exception e){

                }

            }
        });

    }
    private void openWhatsApp() {

        String url = "https://api.whatsapp.com/send?phone="+"91"+session.getData(Constant.WHATSAPP);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);


    }


    private void showpopup(View v) {

        PopupMenu popup = new PopupMenu(activity,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.login_popup);
        popup.show();
    }


    private void Login() {
        Mobile = EtPhoneNumber.getText().toString().trim();
        Password = EtPassword.getText().toString().trim();

        Map<String, String> params = new HashMap<>();
        params.put(Constant.MOBILE,EtPhoneNumber.getText().toString().trim());
        params.put(Constant.PASSWORD,EtPassword.getText().toString().trim());
        params.put(Constant.DEVICE_ID,Constant.getDeviceId(activity));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                clearFields();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        if (jsonObject.getBoolean(Constant.USER_VERIFY)){
                            if (jsonObject.getBoolean(Constant.DEVICE_VERIFY)){
                                String codegenerate = "0",withdrawal_status = "0";
                                JSONArray userArray = jsonObject.getJSONArray(Constant.DATA);
                                JSONArray setArray = jsonObject.getJSONArray(Constant.SETTINGS);
                                session.setData(Constant.SYNC_TIME,setArray.getJSONObject(0).getString(Constant.SYNC_TIME));
                                session.setData(Constant.REWARD,setArray.getJSONObject(0).getString(Constant.REWARD));
                                session.setData(Constant.AD_SHOW_TIME,setArray.getJSONObject(0).getString(Constant.AD_SHOW_TIME));
                                session.setData(Constant.MIN_WITHDRAWAL,setArray.getJSONObject(0).getString(Constant.MIN_WITHDRAWAL));
                                session.setData(Constant.AD_STATUS,setArray.getJSONObject(0).getString(Constant.AD_STATUS));
                                session.setData(Constant.FETCH_TIME,setArray.getJSONObject(0).getString(Constant.FETCH_TIME));
                                session.setData(Constant.AD_REWARD_ID,setArray.getJSONObject(0).getString(Constant.AD_REWARD_ID));

                                if (setArray.getJSONObject(0).getString(Constant.CODE_GENERATE).equals("1")){
                                    codegenerate = userArray.getJSONObject(0).getString(Constant.CODE_GENERATE);
                                }
                                if (setArray.getJSONObject(0).getString(Constant.WITHDRAWAL_STATUS).equals("1")){
                                    withdrawal_status = userArray.getJSONObject(0).getString(Constant.WITHDRAWAL_STATUS);
                                }

                                Toast.makeText(this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                                session.setUserData(userArray.getJSONObject(0).getString(Constant.ID),
                                        userArray.getJSONObject(0).getString(Constant.NAME),
                                        userArray.getJSONObject(0).getString(Constant.MOBILE),
                                        userArray.getJSONObject(0).getString(Constant.PASSWORD),
                                        userArray.getJSONObject(0).getString(Constant.DOB),
                                        userArray.getJSONObject(0).getString(Constant.EMAIL),
                                        userArray.getJSONObject(0).getString(Constant.CITY),
                                        userArray.getJSONObject(0).getString(Constant.REFERRED_BY),
                                        userArray.getJSONObject(0).getString(Constant.EARN),
                                        userArray.getJSONObject(0).getString(Constant.WITHDRAWAL),
                                        userArray.getJSONObject(0).getString(Constant.TOTAL_REFERRALS),
                                        userArray.getJSONObject(0).getInt(Constant.TODAY_CODES),
                                        userArray.getJSONObject(0).getInt(Constant.TOTAL_CODES),
                                        userArray.getJSONObject(0).getString(Constant.BALANCE),
                                        userArray.getJSONObject(0).getString(Constant.DEVICE_ID),
                                        userArray.getJSONObject(0).getString(Constant.STATUS),
                                        userArray.getJSONObject(0).getString(Constant.REFER_CODE),
                                        userArray.getJSONObject(0).getString(Constant.REFER_BONUS_SENT),
                                        codegenerate,
                                        userArray.getJSONObject(0).getString(Constant.CODE_GENERATE_TIME),
                                        userArray.getJSONObject(0).getString(Constant.LAST_UPDATED),
                                        userArray.getJSONObject(0).getString(Constant.JOINED_DATE),
                                        withdrawal_status);
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

                        }else {
                            ApprovalAlertdialog(jsonObject.getString(Constant.MESSAGE));
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

    private void clearFields() {
        EtPhoneNumber.getText().clear();
        EtPassword.getText().clear();
    }

    private void showAlertdialog() {


        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Would you request to change device ?");
        builder.setTitle("Device verification failed !");
        builder.setCancelable(false);
        builder.setPositiveButton("Send request to admin", (DialogInterface.OnClickListener) (dialog, which) -> {
            changeDeviceApi(dialog);
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }

    private void changeDeviceApi(DialogInterface dialog)
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.MOBILE,Mobile);
        params.put(Constant.PASSWORD,Password);
        params.put(Constant.DEVICE_ID,Constant.getDeviceId(activity));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Toast.makeText(this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        dialog.cancel();

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
        }, activity, Constant.CHANGE_DEVICE_LIST_URL, params,true);



    }

    private void ApprovalAlertdialog(String msg) {


        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg);
        builder.setTitle("Failed to Login");
        builder.setCancelable(false);
        builder.setPositiveButton("ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.payment){
            try {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(session.getData(Constant.PAYMENT_LINK)));
                startActivity(intent);
            }catch (Exception e){

            }
        }

        return false;
    }
}