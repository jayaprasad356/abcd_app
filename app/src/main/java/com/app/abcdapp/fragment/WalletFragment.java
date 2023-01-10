package com.app.abcdapp.fragment;

import static com.app.abcdapp.helper.Constant.SUCCESS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.Adapter.TransactionAdapter;
import com.app.abcdapp.R;
import com.app.abcdapp.activities.MainActivity;
import com.app.abcdapp.activities.WithdrawalActivity;
import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.model.Transanction;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WalletFragment extends Fragment {

    Button btnWithdrawal;
    RecyclerView recycler;
    TransactionAdapter transactionAdapter;
    Activity activity;
    Session session;
    TextView tvBalance,tvminiwithdrawal;




    public WalletFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_wallet, container, false);
        btnWithdrawal = root.findViewById(R.id.btnWithdrawal);
        recycler = root.findViewById(R.id.recycler);
        tvBalance = root.findViewById(R.id.tvBalance);
        tvminiwithdrawal = root.findViewById(R.id.tvminiwithdrawal);
        activity = getActivity();
        session = new Session(activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearLayoutManager);

        setWalletBalance();





        walletApi();
        transactionList();

        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WithdrawalActivity.class);
                startActivity(intent);
                
            }
        });
        return root;

    }

    private void setWalletBalance() {
        double current_bal = (double) (session.getInt(Constant.CODES) * 0.17);
        tvBalance.setText(session.getData(Constant.BALANCE) + " + "+String.format("%.2f", current_bal)+"");
        tvminiwithdrawal.setText("Minimum Redeem =  ₹"+session.getData(Constant.MIN_WITHDRAWAL));

    }

    private void transactionList()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.USER_ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        ArrayList<Transanction> transanctions = new ArrayList<>();
                        Gson g = new Gson();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                Transanction group = g.fromJson(jsonObject1.toString(), Transanction.class);
                                transanctions.add(group);
                            } else {
                                break;
                            }
                        }
                        transactionAdapter = new TransactionAdapter(activity,transanctions);
                        recycler.setAdapter(transactionAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, activity, Constant.TRNSACTION_LIST_URL, params, true);


    }


    public void walletApi()
    {
        if (ApiConfig.isConnected(activity)){
            Map<String, String> params = new HashMap<>();
            params.put(Constant.USER_ID,session.getData(Constant.USER_ID));
            params.put(Constant.CODES,"0");
            ApiConfig.RequestToVolley((result, response) -> {
                Log.d("WALLET_RES",response);
                if (result) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getBoolean(Constant.SUCCESS)) {
                            session.setInt(Constant.TODAY_CODES,Integer.parseInt(jsonObject.getString(Constant.TODAY_CODES)));
                            session.setInt(Constant.TOTAL_CODES,Integer.parseInt(jsonObject.getString(Constant.TOTAL_CODES)));
                            session.setData(Constant.BALANCE, jsonObject.getString(Constant.BALANCE));
                            session.setData(Constant.CODE_GENERATE, jsonObject.getString(Constant.CODE_GENERATE));
                            session.setData(Constant.STATUS, jsonObject.getString(Constant.STATUS));

                            setWalletBalance();

                        }else {

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, activity, Constant.WALLET_URL, params, true);


        }




    }

}