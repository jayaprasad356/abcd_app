package com.app.abcdapp.fragment;

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
import com.app.abcdapp.activities.WithdrawalActivity;
import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.model.Transanction;
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
    TextView tvBalance;



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
        activity = getActivity();
        session = new Session(activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearLayoutManager);

        tvBalance.setText("Available Balance = ₹"+session.getData(Constant.BALANCE));

        walletApi();

        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WithdrawalActivity.class);
                startActivity(intent);
                
            }
        });

        return root;

    }

    private void walletApi()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.CODES,session.getInt(Constant.CODES)+"");
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("WALLET_RES",response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        session.setData(Constant.BALANCE,jsonObject.getString(Constant.BALANCE));
                        tvBalance.setText("Available Balance = ₹"+session.getData(Constant.BALANCE));
                        session.setInt(Constant.CODES,0);
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        JSONArray bankArray = object.getJSONArray(Constant.BANK_DETAILS);
                        if (bankArray.length() != 0){
                            session.setData(Constant.ACCOUNT_NUM,bankArray.getJSONObject(0).getString(Constant.ACCOUNT_NUM));
                            session.setData(Constant.HOLDER_NAME,bankArray.getJSONObject(0).getString(Constant.HOLDER_NAME));
                            session.setData(Constant.BANK,bankArray.getJSONObject(0).getString(Constant.BANK));
                            session.setData(Constant.BRANCH,bankArray.getJSONObject(0).getString(Constant.BRANCH));
                            session.setData(Constant.IFSC,bankArray.getJSONObject(0).getString(Constant.IFSC));
                        }
                        if (jsonArray.length() != 0){
                            Gson g = new Gson();
                            ArrayList<Transanction> transanctions = new ArrayList<>();

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

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.WALLET_URL, params, true);



    }

}