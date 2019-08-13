package com.example.bookingappointment;

public class Doctors {

    private String name;
    private String category;
    private String doctorId;
    private String url;
    private String exp;
    private String location;

    public Doctors(){

    }

    public Doctors(String name, String category, String doctorId, String url, String exp , String Location) {
        this.name = name;
        this.category = category;
        this.doctorId = doctorId;
        this.url=url;
        this.exp=exp;
        this.location=Location;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getUrl(){
        return  url;
    }

    public String getExp() {
        return exp;
    }

    public String getLocation() {
        return location;
    }
}
