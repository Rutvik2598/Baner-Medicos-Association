package com.example.bookingappointment;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Time;
import java.util.Date;

public class BookAppt extends AppCompatActivity {

    private String doctor_name;
    Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appt);



        doctor_name = getIntent().getStringExtra("Doctor_Name");
        Log.d(doctor_name,"Doc name");
        button = findViewById(R.id.book_appt);
        button2 = findViewById(R.id.button25);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BookAppt.this , BookApptss.class);
                intent.putExtra("Doctor_Name" , doctor_name);
                ContextCompat.startForegroundService(BookAppt.this,intent);
                startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BookAppt.this , ProfileActivity.class);
                intent.putExtra("Doctor_Name" , doctor_name);
                ContextCompat.startForegroundService(BookAppt.this,intent);
                startActivity(intent);

            }
        });


    }
}
