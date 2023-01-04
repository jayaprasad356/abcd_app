package com.app.abcdapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.abcdapp.R;
import com.app.abcdapp.model.GenerateCodes;
import com.app.abcdapp.model.Pincode;

import java.util.ArrayList;


public class PincodeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Activity activity;
    ArrayList<GenerateCodes> generateCodes;

    public PincodeAdapter(Activity activity, ArrayList<GenerateCodes> generateCodes) {
        this.activity = activity;
        this.generateCodes = generateCodes;
    }





    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.pincode_list_layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, @SuppressLint("RecyclerView") int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final GenerateCodes generateCodes1 = generateCodes.get(position);

        //   Glide.with(activity).load(quotationList.getImage()).placeholder(R.drawable.logo).into(holder.imgProduct);
        holder.tvCity.setText(generateCodes1.getEcity());
        holder.tvPincode.setText(generateCodes1.getPin_code());




    }


    @Override
    public int getItemCount() {
        return generateCodes.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final TextView tvPincode,tvCity;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvPincode = itemView.findViewById(R.id.tvPincode);
            tvCity = itemView.findViewById(R.id.tvCity);

        }
    }
}