package com.app.abcdapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.abcdapp.Model.Redeem;
import com.app.abcdapp.R;

import java.util.ArrayList;

public class RedeemedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Redeem> redeems;

    public RedeemedAdapter(Activity activity, ArrayList<Redeem> redeems) {
        this.activity = activity;
        this.redeems = redeems;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.redeem_list_layout, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        final Redeem redeem = redeems.get(position);

        holder.tvPaymentmode.setText(redeem.getMode());
        holder.tvMobileno.setText(redeem.getMobile());
        holder.tvAmount.setText(redeem.getAmount()+"\n\n"+redeem.getStatus()+"\n\n"+redeem.getDate()+" | "+redeem.getTime());

    }


    @Override
    public int getItemCount() {
        return redeems.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final TextView tvPaymentmode,tvMobileno,tvAmount;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvPaymentmode = itemView.findViewById(R.id.tvPaymentmode);
            tvMobileno = itemView.findViewById(R.id.tvMobileno);
            tvAmount = itemView.findViewById(R.id.tvAmount);



        }
    }
}

