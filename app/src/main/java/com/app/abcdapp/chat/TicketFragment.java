package com.app.abcdapp.chat;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.abcdapp.R;
import com.app.abcdapp.chat.adapters.TicketAdapters;
import com.app.abcdapp.chat.managers.Utils;
import com.app.abcdapp.chat.models.Ticket;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TicketFragment extends Fragment {
    public RecyclerView mRecyclerView;
    public ArrayList<Ticket> mTickets;
    public TicketAdapters ticketAdapters;
    Activity activity;
    Chip chipPending,chipOpened,chipClosed;
    Session session;
    String type = "";


    public TicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_ticket, container, false);
        activity = getActivity();
        session = new Session(activity);
        mRecyclerView = root.findViewById(R.id.recyclerView);
        chipPending = root.findViewById(R.id.chipPending);
        chipOpened = root.findViewById(R.id.chipOpened);
        chipClosed = root.findViewById(R.id.chipClosed);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        readTickets();

        chipPending.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    readTickets();
                }
            }
        });
        chipOpened.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    readTickets();
                }
            }
        });
        chipClosed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    readTickets();
                }
            }
        });

        return root;
    }

    private void readTickets() {

        mTickets = new ArrayList<>();
        Query reference;
        if (chipOpened.isChecked()){
            type = chipOpened.getText().toString();
            reference = Utils.getQueryOpenedTicketByMyId(session.getData(Constant.MOBILE));
        }else if (chipClosed.isChecked()){
            type = chipClosed.getText().toString();
            reference = Utils.getQueryClosedTicketByMyId(session.getData(Constant.MOBILE));
        }
        else {
            type = chipPending.getText().toString();
            reference = Utils.getQueryPendingTicketByMyId(session.getData(Constant.MOBILE));

        }
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTickets.clear();
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Ticket user = snapshot.getValue(Ticket.class);
                        assert user != null;
                        mTickets.add(user);
                    }

                }
                setAdatper();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setAdatper() {
        ticketAdapters = new TicketAdapters(activity, mTickets,type);
        mRecyclerView.setAdapter(ticketAdapters);
    }
}