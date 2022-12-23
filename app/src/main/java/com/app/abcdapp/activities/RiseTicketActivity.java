package com.app.abcdapp.activities;

import static com.app.abcdapp.chat.constants.IConstants.REF_CHATS;

import androidx.annotation.NonNull;
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
import com.app.abcdapp.chat.adapters.MessageAdapters;
import com.app.abcdapp.chat.managers.Utils;
import com.app.abcdapp.chat.models.Chat;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.CustomDialog;
import com.app.abcdapp.helper.Session;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
                            Query freq = Utils.getQuerySupportStatus();
                            freq.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChildren()){
                                        riseTicket(etTitle.getText().toString().trim(),etDescription.getText().toString().trim(),spinCategory.getSelectedItem().toString().trim());
                                    }
                                    else {
                                        customDialog.closeDialog();
                                        Toast.makeText(RiseTicketActivity.this, "Chat Support is Temporary Shutdown", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }
                    }
                });


            }
        });
    }

    private void riseTicket(String title, String description, String category) {
        Long tsLong = System.currentTimeMillis()/1000;
        String RandomId = session.getData(Constant.USER_ID) +"_"+ tsLong.toString();
        Query qref;
        qref = Utils.getQueryPendingTicketByMyId(session.getData(Constant.MOBILE));
        qref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChildren()) {
                    reference = FirebaseDatabase.getInstance().getReference(Constant.PENDING_TICKET).child(RandomId);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put(Constant.ID, RandomId);
                    hashMap.put(Constant.CATEGORY, category);
                    hashMap.put(Constant.TITLE, title);
                    hashMap.put(Constant.DESCRIPTION, description);
                    hashMap.put(Constant.USER_ID, session.getData(Constant.USER_ID));
                    hashMap.put(Constant.NAME, session.getData(Constant.NAME));
                    hashMap.put(Constant.MOBILE, session.getData(Constant.MOBILE));
                    hashMap.put(Constant.TYPE, Constant.PENDING_TICKET);
                    hashMap.put(Constant.SUPPORT, "Admin");
                    hashMap.put(Constant.TIMESTAMP, tsLong.toString());
                    reference.setValue(hashMap).addOnCompleteListener(task1 -> {
                        customDialog.closeDialog();
                        Toast.makeText(activity, "Ticket Sent Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(activity, MainActivity.class);
                        startActivity(intent);
                    });
                }
                else {
                    customDialog.closeDialog();
                    Toast.makeText(activity, "Your Ticket is Already in Pending Status,You Can't Send Another Ticket", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}