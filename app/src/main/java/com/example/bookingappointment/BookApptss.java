package com.example.bookingappointment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


public class BookApptss extends AppCompatActivity implements View.OnClickListener {

    Button apt1,apt2,apt3,apt4,apt5,apt6,apt7,confrm;
    private FirebaseAuth mAuth;
    String phone,doctorName,date1,time=null;
    DatabaseReference appointments;
    CalendarView calendar;
    String id,text_msg,phone_number;
    Calendar calendar1;
    Appointment appt;
    Toolbar toolbar;
    boolean check = false;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_apptss);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Select date and time");
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        phone = mAuth.getCurrentUser().getPhoneNumber();

        doctorName = getIntent().getStringExtra("Doctor_Name");
        Log.d(doctorName,"Doctor nameeee");

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date1 = formatter.format(todayDate);

        calendar = findViewById(R.id.calender2);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1++;
                String day=null,month=null;
                day = String.valueOf(i2);
                month = String.valueOf(i1);
                if(i1 < 10)
                {
                    month = String.format("%01d" , i1);
                }

                if(i2 < 10)
                {
                    day = String.format("%01d" , i2);
                }

                date1 =(day + "-"+ month + "-" + i);
            }
        });



        apt1 = findViewById(R.id.appt1);
        apt2 = findViewById(R.id.appt2);
        apt3 = findViewById(R.id.appt3);
        apt4 = findViewById(R.id.appt4);
        apt5 = findViewById(R.id.appt5);
        apt6 = findViewById(R.id.appt6);
        apt7 = findViewById(R.id.appt7);

        apt1.setOnClickListener(this);
        apt2.setOnClickListener(this);
        apt3.setOnClickListener(this);
        apt4.setOnClickListener(this);
        apt5.setOnClickListener(this);
        apt6.setOnClickListener(this);
        apt7.setOnClickListener(this);

        confrm = findViewById(R.id.confirm);

        confrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time != null){
                id = appointments.push().getKey();
                appt = new Appointment(doctorName, date1, time ,phone);
                appointments.child(id).setValue(appt);


                text_msg = "You have new booking with patient:" + phone + " at " + time + " on date " + date1;
                phone_number = "+917038169863";

                SendMail sm = new SendMail(BookApptss.this , "kulkarnirutvikvishwas@gmail.com" , "New Booking" , text_msg);

                sm.execute();

                Toast.makeText(BookApptss.this,"Booking Confirmed",Toast.LENGTH_SHORT).show();


                if(checkPermission(Manifest.permission.SEND_SMS)){
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phone_number , null , text_msg , null , null);
                }

                Intent intent = new Intent(BookApptss.this , ProfileActivity.class);
                ContextCompat.startForegroundService(BookApptss.this,intent);
                startActivity(intent);

            }
                else{
                    Toast.makeText(BookApptss.this,"Select a time slot",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        appointments = FirebaseDatabase.getInstance().getReference("appointments");
        switch (view.getId()){

            case R.id.appt1:
                //Toast.makeText(this, "Button 1 was clicked",Toast.LENGTH_SHORT).show();
                time = apt1.getText().toString();
                break;

            case R.id.appt2:
                //Toast.makeText(this,"Button 2 was clicked",Toast.LENGTH_SHORT).show();
                time = apt2.getText().toString();
                break;

            case R.id.appt3:
                //Toast.makeText(this,"Button 2 was clicked",Toast.LENGTH_SHORT).show();
                time = apt3.getText().toString();
                break;

            case R.id.appt4:
                //Toast.makeText(this,"Button 2 was clicked",Toast.LENGTH_SHORT).show();
                time = apt4.getText().toString();
                break;

            case R.id.appt5:
                //Toast.makeText(this,"Button 2 was clicked",Toast.LENGTH_SHORT).show();
                time = apt5.getText().toString();
                break;

            case R.id.appt6:
                //Toast.makeText(this,"Button 2 was clicked",Toast.LENGTH_SHORT).show();
                time = apt6.getText().toString();
                break;

            case R.id.appt7:
                //Toast.makeText(this,"Button 2 was clicked",Toast.LENGTH_SHORT).show();
                time = apt6.getText().toString();
                break;

            default:
                break;

        }

    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this , permission);
        return  (check == PackageManager.PERMISSION_GRANTED);
    }
}




