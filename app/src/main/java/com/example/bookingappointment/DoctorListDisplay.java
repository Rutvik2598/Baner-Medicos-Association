package com.example.bookingappointment;

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DoctorListDisplay extends AppCompatActivity {

    ListView listViewDoctors;
    Toolbar toolbar;
    private DoctorListAdapter adapter;
    List<Doctors> doctorsList;
    private RecyclerView recyclerView;
    DatabaseReference databaseDoctors;
    DatabaseReference databaseReference1;
    private String category;
    Query query;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list_display);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        databaseDoctors = FirebaseDatabase.getInstance().getReference("doctors");
        databaseReference1=databaseDoctors.child("url");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorsList = new ArrayList<>();
        adapter = new DoctorListAdapter(this, doctorsList);
        recyclerView.setAdapter(adapter);

        //listViewDoctors=(ListView) findViewById(R.id.listViewDoctors);
        category=getIntent().getStringExtra("Category");
        Log.d(category,"Category");

        query = FirebaseDatabase.getInstance().getReference("doctors").orderByChild("category").equalTo(category);
        query.addListenerForSingleValueEvent(valueEventListener);



        /*DoctorListAdapter adapter = new DoctorListAdapter(DoctorListDisplay.this,doctorsList);
        listViewDoctors.setAdapter(adapter);*/
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        databaseDoctors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doctorsList.clear();

                for(DataSnapshot doctorsSnapshot: dataSnapshot.getChildren()){
                    Doctors doctors = doctorsSnapshot.getValue(Doctors.class);
                    doctorsList.add(doctors);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            doctorsList.clear();
            if(dataSnapshot.exists()) {
                for (DataSnapshot doctorsSnapshot : dataSnapshot.getChildren()) {
                    Doctors doctors = doctorsSnapshot.getValue(Doctors.class);
                    doctorsList.add(doctors);

                }
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
