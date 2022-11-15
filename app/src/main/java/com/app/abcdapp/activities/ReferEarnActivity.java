package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
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



                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();


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