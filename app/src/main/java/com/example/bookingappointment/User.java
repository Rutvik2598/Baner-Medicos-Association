package com.example.bookingappointment;

public class User {
    public String name,number;

    public User(){

    }

    public User(String name,String number){
        this.name=name;
        this.number=number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
