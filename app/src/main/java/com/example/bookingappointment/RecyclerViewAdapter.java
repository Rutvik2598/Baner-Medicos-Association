package com.example.bookingappointment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<DoctorCategory> mData;

    public RecyclerViewAdapter(Context mContext, List<DoctorCategory> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_category,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.category.setText(mData.get(position).getCategory());
        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,DoctorListDisplay.class);
                intent.putExtra("Category",mData.get(position).getCategory());
                ContextCompat.startForegroundService(mContext,intent);
                mContext.startActivity(intent);
            }
        });
        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,DoctorsList.class);
                intent.putExtra("Category",mData.get(position).getCategory());
                ContextCompat.startForegroundService(mContext,intent);
                mContext.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView category;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);

            category = (TextView) itemView.findViewById(R.id.doctor_category);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }

    public void updateList(List<DoctorCategory> newList){
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }

}
