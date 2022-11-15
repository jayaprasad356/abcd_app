package com.app.abcdapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.app.abcdapp.R;
import com.app.abcdapp.activities.ReferEarnActivity;
import com.app.abcdapp.activities.UpdateProfileActivity;
import com.app.abcdapp.helper.Session;


public class ProfileFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    CardView cardView1;
    ImageView imgMenu;
    Session session;
    Activity activity;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root  =  inflater.inflate(R.layout.fragment_profile, container, false);


        activity = getActivity();
        session = new Session(getActivity());

        cardView1 = root.findViewById(R.id.cardView1);
        imgMenu = root.findViewById(R.id.imgMenu);



        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReferEarnActivity.class);
                startActivity(intent);
            }
            
            
            
        });


        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpopup(view);
            }
        });




        return root;
    }

    private void showpopup(View v) {

        PopupMenu popup = new PopupMenu(getActivity(),v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.logoutitem){
            session.logoutUser(activity);
        }

        else if (item.getItemId() == R.id.ReferEarn){

            Intent intent = new Intent(activity,ReferEarnActivity.class);
            startActivity(intent);

        }
        else if (item.getItemId() == R.id.Uptdatepofile){

            Intent intent = new Intent(activity, UpdateProfileActivity.class);
            startActivity(intent);

        }
        return false;
    }
}