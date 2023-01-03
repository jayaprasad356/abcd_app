package com.app.abcdapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.R;
import com.app.abcdapp.chat.TicketFragment;
import com.app.abcdapp.fragment.HomeFragment;
import com.app.abcdapp.fragment.ProfileFragment;
import com.app.abcdapp.fragment.WalletFragment;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {

    public static FragmentManager fm = null;
    private BottomNavigationView navbar;
    Activity activity;
    Session session;
    String NOTIFY_CHAT;
    Dialog dialog = null;
    long fetch_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        navbar = findViewById(R.id.bottomNavigation);
        navbar.setSelectedItemId(R.id.Home);
        activity = MainActivity.this;
        session = new Session(activity);
        NOTIFY_CHAT = getIntent().getStringExtra("NOTIFY_CHAT");

        if (NOTIFY_CHAT != null){
            navbar.setSelectedItemId(R.id.Support);
            fm.beginTransaction().replace(R.id.Container, new TicketFragment()).commit();


        }
        else {
            fm.beginTransaction().replace(R.id.Container, new HomeFragment()).commit();


        }

        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Profile:
                        fm.beginTransaction().replace(R.id.Container, new ProfileFragment()).commit();
                        break;
                    case R.id.Home:
                        fm.beginTransaction().replace(R.id.Container, new HomeFragment()).commit();
                        break;
                    case R.id.Wallet:
                        try {
                            fetch_time = Long.parseLong(session.getData(Constant.FETCH_TIME)) * 1000;


                        }catch (Exception e){
                            fetch_time = 5 * 1000;


                        }
                        showAlertDialog();

                        break;

                    case R.id.Support:
                        fm.beginTransaction().replace(R.id.Container,new TicketFragment()).commit();
                        break;
                }
                return true;
            }
        });
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
            session.setData(Constant.FCM_ID, token);
        });

    }

    private void showAlertDialog() {

        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.wallet_dialog);
        dialog.setCancelable(false);
        EditText edMobile = dialog.findViewById(R.id.edMobile);
        TextView tvCancel = dialog.findViewById(R.id.tvCancel);
        TextView tvViewWallet = dialog.findViewById(R.id.tvViewWallet);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        tvViewWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edMobile.getText().toString().equals(session.getData(Constant.MOBILE))){
                    edMobile.getText().clear();
                    Toast.makeText(activity, "Invalid Mobile number", Toast.LENGTH_SHORT).show();

                }else {
                    dialog.dismiss();
                    ProgressDialog progressDialog = new ProgressDialog(activity);
                    progressDialog.setTitle("Please wait...");
                    progressDialog.setMessage("Fetching Transactions");
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            progressDialog.dismiss();
                            fm.beginTransaction().replace(R.id.Container, new WalletFragment()).commit();





                        }
                    },fetch_time);



                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        WalletFragment fragm = new WalletFragment();
        fragm.walletApi(session,activity);
    }
}