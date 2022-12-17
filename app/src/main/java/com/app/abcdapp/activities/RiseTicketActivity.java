package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemeUtils;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.abcdapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class RiseTicketActivity extends AppCompatActivity {

    protected Button btnRiseToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rise_ticket);

        btnRiseToken = findViewById(R.id.RiseToken);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        btnRiseToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.risetoken_bottom_sheet_lyt);
                dialog.show();
            }
        });
    }
}