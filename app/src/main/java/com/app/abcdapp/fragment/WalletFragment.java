package com.app.abcdapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.abcdapp.R;
import com.app.abcdapp.activities.WalletActivity;


public class WalletFragment extends Fragment {

    Button btnViewDetails;


    public WalletFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_wallet, container, false);


        btnViewDetails = root.findViewById(R.id.btnViewDetails);

        btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent);
                
            }
        });

        return root;

    }
}