package com.example.bookingappointment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class BookSlots extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10;
    DatabaseReference appointments;
    DatabaseReference databaseDoctors;
    private String time,doctor_name,phone,date1;
    private FirebaseAuth mAuth;
    Query query;
    String id;
    Appointment appt;
    String name = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slots);

        btn1 =findViewById(R.id.book_appt23);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BookSlots.this , ProfileActivity.class);
                //intent.putExtra("Doctor_Name" , doctor_name);
                ContextCompat.startForegroundService(BookSlots.this,intent);
                startActivity(intent);

                //Toast.makeText(this,"Button 1 was clicked",Toast.LENGTH_SHORT).show();
                /*time = btn1.getText().toString();
                Log.d(time,"Timeeeeeeeeeeeee");
                id = appointments.push().getKey();
                appt = new Appointment(doctor_name, date1, time ,phone);
                appointments.child(id).setValue(appt);*/
            }
        });
        btn2 = (Button) findViewById(R.id.evening);
        btn3 = (Button) findViewById(R.id.button1);
        btn4 = (Button) findViewById(R.id.button2);
        btn5 = (Button) findViewById(R.id.button3);
        btn6 = (Button) findViewById(R.id.button4);
        btn7 = (Button) findViewById(R.id.button5);
        btn8 = (Button) findViewById(R.id.button6);
        btn9 = (Button) findViewById(R.id.button7);
        btn10 = (Button) findViewById(R.id.button8);


        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        phone = mAuth.getCurrentUser().getPhoneNumber();
        Log.d(phone,"Current user phone number");

       /*FirebaseDatabase.getInstance().getReference("users").orderByChild("number").equalTo(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String name = user.getName();
                Log.d(name , "Current user name");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/






        doctor_name = getIntent().getStringExtra("Doctor_Name");
        Log.d(doctor_name,"Doctor nameeee");



        /*findViewById(R.id.morning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = btn1.getText().toString();
            }
        });*/



        /*Date date = new Date();
        int hrs = date.getHours();*/

        /*DateFormat dateFormat = new SimpleDateFormat("hh");
        String time = dateFormat.format(new Date()).toString();
        Log.d(time,"Current Time 2");*/

        setContentView(R.layout.activity_book_slots);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, 0);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 3);

        /*HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

                date1 = android.text.format.DateFormat.format("EEE , MMM d ,yyyy" , date).toString();
            }
        });

        Log.d(date1,"dateeeeee");*/




        /*String id = appointments.push().getKey();
        Appointment appt = new Appointment(doctor_name, date1, time ,phone);
        appointments.child(id).setValue(appt);*/

    }

    @Override
    public void onClick(View view) {
        appointments = FirebaseDatabase.getInstance().getReference("appointments");
        switch (view.getId()){

            case R.id.morning:
                Toast.makeText(this,"Button 1 was clicked",Toast.LENGTH_SHORT).show();
                time = btn1.getText().toString();
                id = appointments.push().getKey();
                appt = new Appointment(doctor_name, date1, time ,phone);
                appointments.child(id).setValue(appt);
                break;

            case R.id.evening:
                time = btn2.getText().toString();
                id = appointments.push().getKey();
                appt = new Appointment(doctor_name, date1, time ,phone);
                appointments.child(id).setValue(appt);
                break;

            default:
                break;

        }
    }
}
