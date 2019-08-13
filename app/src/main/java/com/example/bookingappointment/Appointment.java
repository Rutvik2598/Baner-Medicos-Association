package com.example.bookingappointment;

public class Appointment {

    private String doctorName;
    private String date;
    private String apptTime;
    private String userName;

    public Appointment(String doctorName, String date, String apptTime, String userName) {
        this.doctorName = doctorName;
        this.date = date;
        this.apptTime = apptTime;
        this.userName = userName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return date;
    }

    public String getApptTime() {
        return apptTime;
    }

    public String getUserName() {
        return userName;
    }
}
