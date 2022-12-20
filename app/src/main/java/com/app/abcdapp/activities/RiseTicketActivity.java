package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemeUtils;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.abcdapp.R;
import com.app.abcdapp.chat.ChatActivity;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.CustomDialog;
import com.app.abcdapp.helper.Session;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RiseTicketActivity extends AppCompatActivity {

    protected Button btnRiseToken;
    DatabaseReference reference;
    Session session;
    Activity activity;
    CustomDialog customDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rise_ticket);

        btnRiseToken = findViewById(R.id.RiseToken);

        activity = RiseTicketActivity.this;
        customDialog = new CustomDialog(activity);
        session = new Session(activity);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        btnRiseToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.risetoken_bottom_sheet_lyt);
                dialog.show();

                EditText etTitle = dialog.findViewById(R.id.etTitle);
                EditText etDescription = dialog.findViewById(R.id.etDescription);
                Spinner spinCategory = dialog.findViewById(R.id.spinCategory);
                Button btnRiseToken = dialog.findViewById(R.id.btnRiseToken);

                btnRiseToken.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (etTitle.getText().toString().trim().equals("")){
                            Toast.makeText(RiseTicketActivity.this, "Title is Empty", Toast.LENGTH_SHORT).show();
                        }else if (etDescription.getText().toString().trim().equals("")){
                            Toast.makeText(RiseTicketActivity.this, "Description is Empty", Toast.LENGTH_SHORT).show();
                        }else {
                            customDialog.showDialog();
                            riseTicket(etTitle.getText().toString().trim(),etDescription.getText().toString().trim(),spinCategory.getSelectedItem().toString().trim());
                        }
                    }
                });


            }
        });
    }

    private void riseTicket(String title, String description, String category) {
        Long tsLong = System.currentTimeMillis()/1000;
        String RandomId = session.getData(Constant.USER_ID) +"_"+ tsLong.toString();
        reference = FirebaseDatabase.getInstance().getReference(Constant.PENDING_TICKET).child(RandomId);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(Constant.ID, RandomId);
        hashMap.put(Constant.CATEGORY, category);
        hashMap.put(Constant.TITLE, title);
        hashMap.put(Constant.DESCRIPTION, description);
        hashMap.put(Constant.USER_ID, session.getData(Constant.USER_ID));
        hashMap.put(Constant.NAME, session.getData(Constant.NAME));
        hashMap.put(Constant.MOBILE, session.getData(Constant.MOBILE));
        reference.setValue(hashMap).addOnCompleteListener(task1 -> {
            customDialog.closeDialog();
            Intent intent = new Intent(activity, ChatActivity.class);
            startActivity(intent);
        });
    }
}