package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.R;

public class ReferEarnActivity extends AppCompatActivity {


    ImageView backbtn;
    TextView tvRefercode;
    Button btncopy;

    private ClipboardManager myClipboard;
    private ClipData myClip;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);


        backbtn = findViewById(R.id.backbtn);
        tvRefercode = findViewById(R.id.tvRefercode);
        btncopy = findViewById(R.id.btncopy);

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        text = tvRefercode.getText().toString();


        btncopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "DOWNLOAD THE APP AND GET UNLIMITED EARNING .you can also Download App from below link and enter referral code while login-"+"\n"+text+"\n"+"https://play.google.com/store/apps/details?id=abcdjob.workonline.com.qrcode";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

                myClip = ClipData.newPlainText("text", shareBody);
                myClipboard.setPrimaryClip(myClip);




//
//                Toast.makeText(getApplicationContext(), "Text Copied",
//                        Toast.LENGTH_SHORT).show();


            }
        });



        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}