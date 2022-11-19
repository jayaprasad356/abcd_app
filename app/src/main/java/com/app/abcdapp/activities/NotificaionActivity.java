package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.abcdapp.Adapter.NotificationAdapter;
import com.app.abcdapp.R;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.model.Notification;

import java.util.ArrayList;

public class NotificaionActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Activity activity;
    NotificationAdapter notificationAdapter;
    Session session;
    ImageView ivBackbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaion);
        activity = NotificaionActivity.this;
        session = new Session(activity);
        recyclerView = findViewById(R.id.recyclerView);
        ivBackbtn = findViewById(R.id.backbtn);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));

        notificationList();
        ivBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void notificationList()
    {

        ArrayList<Notification> notifications = new ArrayList<>();


        Notification serviceModel1  = new Notification("","Amount Transfer","₹499");
        Notification serviceModel2  = new Notification("","Amount Transfer","₹499");
        Notification serviceModel3  = new Notification("","Amount Transfer","₹499");


        notifications.add(serviceModel1);
        notifications.add(serviceModel2);
        notifications.add(serviceModel3);

        notificationAdapter = new NotificationAdapter(activity, notifications);
        recyclerView.setAdapter(notificationAdapter);


//        Map<String, String> params = new HashMap<>();
//        ApiConfig.RequestToVolley((result, response) -> {
//            if (result) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
//                        JSONObject object = new JSONObject(response);
//                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
//                        Gson g = new Gson();
//                        ArrayList<Notification> notifications = new ArrayList<>();
//
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                            if (jsonObject1 != null) {
//                                Notification group = g.fromJson(jsonObject1.toString(), Notification.class);
//                                notifications.add(group);
//                            } else {
//                                break;
//                            }
//                        }
//                        notificationAdapter = new NotificationAdapter(activity, notifications);
//                        recyclerView.setAdapter(notificationAdapter);
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, activity, Constant.NOTIFICATION_LIST, params, true);

    }

}