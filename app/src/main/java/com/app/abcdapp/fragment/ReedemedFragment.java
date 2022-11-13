package com.app.abcdapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.abcdapp.R;
import com.app.abcdapp.Model.Redeem;
import com.app.abcdapp.Adapter.RedeemedAdapter;

import java.util.ArrayList;


public class ReedemedFragment extends Fragment {


    RecyclerView recycler;
    RedeemedAdapter redeemedAdapter;



    public ReedemedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_reedemed, container, false);


        recycler = root.findViewById(R.id.recycler);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearLayoutManager);


        walletlist();

        return root;
    }


    private void walletlist() {


        ArrayList<Redeem> redeems = new ArrayList<>();


        Redeem wallet1 = new Redeem("","Cash Payment","+91999999999","₹ 2000","11:34:50 AM","12-11-2022","Paid");
        Redeem wallet2 = new Redeem("","Cash Payment","+91999999999","₹ 2000","11:34:50 AM","12-11-2022","Paid");
        Redeem wallet3 = new Redeem("","Cash Payment","+91999999999","₹ 2000","11:34:50 AM","12-11-2022","Paid");




        redeems.add(wallet1);
        redeems.add(wallet2);
        redeems.add(wallet3);


        redeemedAdapter = new RedeemedAdapter(getActivity(),redeems);
        recycler.setAdapter(redeemedAdapter);
    }
}