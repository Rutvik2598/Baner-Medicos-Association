package com.example.bookingappointment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorsList extends AppCompatActivity {

    private String[] name = {"Dr. Pranav Radkar","Dr. Anagha Behere","Dr. Akash Kadam"};
    private String[] category = {"Ophthalmologist","Ophthalmologist","Dentist"} ;
    private String[] url = {"https://firebasestorage.googleapis.com/v0/b/bookingappointment-7b278.appspot.com/o/Pranav%20Radkar1.png?alt=media&token=78f0cde7-cdcd-4168-a449-d15b5c225568",
            "https://firebasestorage.googleapis.com/v0/b/bookingappointment-7b278.appspot.com/o/Anagha%20Behere.jpg?alt=media&token=877c577c-904f-4b19-ab50-e3732ca1ae7b",
            "https://firebasestorage.googleapis.com/v0/b/bookingappointment-7b278.appspot.com/o/Akash%20Kadam.jpg?alt=media&token=65b1bbb4-9c91-49ce-978c-7e3c69029c3d"};
    private String[] exp = {"5 Years Experience","10 Years Experience","10 Years Experience"};
    private String[] location = {"Baner","Balewadi","Mhalunhge"};
    DatabaseReference databaseDoctors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        databaseDoctors = FirebaseDatabase.getInstance().getReference("doctors");

        for(int i=0;i<name.length;i++) {
            String id = databaseDoctors.push().getKey();
            Doctors doctors = new Doctors(name[i], category[i], id ,url[i] , exp[i] , location[i]);
            databaseDoctors.child(id).setValue(doctors);
       }
    }
}
