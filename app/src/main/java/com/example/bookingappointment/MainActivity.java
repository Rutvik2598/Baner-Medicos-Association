package com.example.bookingappointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //private Spinner spinner;
    private EditText phoneNumber;
    private EditText nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber=findViewById(R.id.phoneNumber);
        phoneNumber.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        findViewById(R.id.otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Code="91";
                String number = phoneNumber.getText().toString().trim();
                nameUser=findViewById(R.id.name);
                if(number.isEmpty() || number.length() < 10){
                    phoneNumber.setError("Number is required");
                    phoneNumber.requestFocus();
                    return;
                }

                String phone = "+" + Code + number;
                String name= nameUser.getText().toString();
                Intent intent = new Intent(MainActivity.this,VerifyPhoneActivity.class);

                Bundle bundle = new Bundle();

                bundle.putString("PhoneNumber",phone);
                bundle.putString("name",name);

                intent.putExtras(bundle);

                ContextCompat.startForegroundService(MainActivity.this,intent);
                startActivity(intent);
            }
        });
    }

    /*private void registerUser() {
        String name = nameUser.getText().toString();
        String phone = phoneNumber.getText().toString().trim();

        if(name.isEmpty()){
            nameUser.setError("Name is required");
            nameUser.requestFocus();
        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this,ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ContextCompat.startForegroundService(MainActivity.this,intent);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}
