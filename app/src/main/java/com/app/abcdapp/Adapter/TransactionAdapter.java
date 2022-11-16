package com.app.abcdapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.app.abcdapp.R;
import com.app.abcdapp.model.Transanction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Transanction> wallets;

    public TransactionAdapter(Activity activity, ArrayList<Transanction> wallets) {
        this.activity = activity;
        this.wallets = wallets;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.wallet_list_layout, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        final Transanction wallet = wallets.get(position);

        holder.tvDateTime.setText(wallet.getDatetime());
        holder.tvAmount.setText("+ ₹"+wallet.getAmount());

    }


    @Override
    public int getItemCount() {
        return wallets.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final TextView tvTitle,tvDateTime,tvAmount;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvAmount = itemView.findViewById(R.id.tvAmount);



        }
    }
}
