package com.example.bookingappointment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.MyViewHolder> {

    private Context context;
    private List<Doctors> doctorsList;

    public DoctorListAdapter(Context context, List<Doctors> doctorsList){

        //super(context,R.layout.doctor_list_layout,doctorsList);
        this.context=context;
        this.doctorsList=doctorsList;
    }

   /* @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.doctor_list_layout,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewCategory = (TextView) listViewItem.findViewById(R.id.textViewCategory);

        Doctors doctor = doctorsList.get(position);

        textViewName.setText(doctor.getName());
        textViewCategory.setText(doctor.getCategory());

        return listViewItem;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.doctor_list_layout,parent,false);
        return new MyViewHolder(view);
    }

    //@Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Doctors doctors = doctorsList.get(position);
        holder.textViewName.setText(doctors.getName());
        holder.textViewCategory.setText(doctors.getCategory());
        Picasso.with(context)
                .load(doctors.getUrl())
                .fit()
                .into(holder.imageView);
        holder.exp.setText(doctors.getExp());
        holder.location.setText(doctors.getLocation());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BookAppt.class);
                intent.putExtra("Doctor_Name",doctorsList.get(position).getName());
                ContextCompat.startForegroundService(context,intent);
                context.startActivity(intent);
            }
        });
        holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BookAppt.class);
                intent.putExtra("Doctor_Name",doctorsList.get(position).getName());
                ContextCompat.startForegroundService(context,intent);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return doctorsList.size();

    }

    class MyViewHolder extends RecyclerViewAdapter.MyViewHolder{

        TextView textViewName , textViewCategory , exp , location;
        ImageView imageView;
        CardView cardView;
        LinearLayout l;

        public MyViewHolder(View view) {

            super(view);

            cardView = view.findViewById(R.id.cardViewww);
            l = view.findViewById(R.id.ll);
            textViewName = view.findViewById(R.id.textViewName);
            textViewCategory=view.findViewById(R.id.textViewCategory);
            imageView=view.findViewById(R.id.imageViewProfile);
            exp=view.findViewById(R.id.textViewExp);
            location=view.findViewById(R.id.textViewLocation);
        }

    }
}
