package com.app.abcdapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.abcdapp.R;
import com.app.abcdapp.activities.GenrateQRActivity;
import com.app.abcdapp.activities.MainActivity;
import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GenrateQRFragment extends Fragment {
    Handler handler;
    Session session;
    Activity activity;


    public GenrateQRFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_genrate_q_r, container, false);
        activity = getActivity();
        session = new Session(activity);


        handler = new Handler();
        GotoActivity();
        return root;


    }

    public void walletApi() {
        if (ApiConfig.isConnected(activity)) {
            if (session.getInt(Constant.CODES) != 0) {
                Map<String, String> params = new HashMap<>();
                params.put(Constant.USER_ID, session.getData(Constant.USER_ID));
                params.put(Constant.CODES, session.getInt(Constant.CODES) + "");
                session.setInt(Constant.CODES, 0);
                ApiConfig.RequestToVolley((result, response) -> {
                    Log.d("WALLET_RES", response);
                    if (result) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean(Constant.SUCCESS)) {
                                session.setBoolean(Constant.RUN_API, false);
                                session.setInt(Constant.TODAY_CODES, Integer.parseInt(jsonObject.getString(Constant.TODAY_CODES)));
                                session.setInt(Constant.TOTAL_CODES, Integer.parseInt(jsonObject.getString(Constant.TOTAL_CODES)));
                                session.setData(Constant.BALANCE, jsonObject.getString(Constant.BALANCE));


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, activity, Constant.WALLET_URL, params, true);


            }

        }
    }






    private void GotoActivity()
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.getData(Constant.WORK_ACTIVITY).equals("Find Missing")){
                    MainActivity.fm.beginTransaction().replace(R.id.Container, new FindMissingFragment()).commitAllowingStateLoss();



                }else {
                    MainActivity.fm.beginTransaction().replace(R.id.Container, new HomeFragment()).commitAllowingStateLoss();


                }

            }
        },1000);
    }
}