package com.app.abcdapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.abcdapp.R;
import com.app.abcdapp.model.Cities;

import java.util.ArrayList;

import android.annotation.SuppressLint;



public class CitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Activity activity;
    ArrayList<Cities> cities;

    public CitiesAdapter(Activity activity, ArrayList<Cities> cities) {
        this.activity = activity;
        this.cities = cities;
    }





    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cities_list_layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, @SuppressLint("RecyclerView") int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Cities cities1 = cities.get(position);

        //   Glide.with(activity).load(quotationList.getImage()).placeholder(R.drawable.logo).into(holder.imgProduct);
        holder.tvCity.setText(cities1.getCity());
        holder.tvCity.setText(cities1.getCity());
        holder.tvCity.setText(cities1.getCity());



    }


    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final TextView tvName ,tvMobileNo,tvCity;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMobileNo = itemView.findViewById(R.id.tvMobileNo);
            tvCity = itemView.findViewById(R.id.tvCity);

        }
    }
}