package com.app.abcdapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.abcdapp.R;
import com.app.abcdapp.Model.Wallet;
import com.app.abcdapp.Adapter.WalletAdapter;

import java.util.ArrayList;


public class TransactionFragment extends Fragment {

    RecyclerView recycler;
    WalletAdapter walletAdapter;


    public TransactionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_transaction, container, false);


        recycler = root.findViewById(R.id.recycler);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearLayoutManager);


        walletlist();




        return root;
    }

    private void walletlist() {


        ArrayList<Wallet> wallets = new ArrayList<>();


        Wallet wallet1 = new Wallet("","Amount Credited For Qr Code","+0.17 ₹","12-11-2022 ","11:34:50 AM");
        Wallet wallet2 = new Wallet("","Amount Credited For Qr Code","+0.17 ₹","12-11-2022 ","11:34:50 AM");
        Wallet wallet3 = new Wallet("","Amount Credited For Qr Code","+0.17 ₹","12-11-2022 ","11:34:50 AM");


        wallets.add(wallet1);
        wallets.add(wallet2);
        wallets.add(wallet3);


        walletAdapter = new WalletAdapter(getActivity(),wallets);
        recycler.setAdapter(walletAdapter);
    }
}