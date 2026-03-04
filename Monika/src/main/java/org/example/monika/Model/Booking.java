package org.example.monika.Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Booking{
    private int id;
    private int customerid;
    private int coworkerid;
    private LocalDate date;
    private LocalTime startTime;
    private int duration;
    private String comment;

    //full contructor get booking from database
    public Booking(int id, int customerid, int coworkerid, LocalDate date, LocalTime startTime, int duration, String comment) {
        this.id = id;
        this.customerid = customerid;
        this.coworkerid = coworkerid;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.comment = comment;
    }

    // constructor without ID to make new bookings, database generates id automatically
    public Booking( int customerid, int coworkerid, LocalDate date, LocalTime startTime, int duration, String comment) {
        this.customerid = customerid;
        this.coworkerid = coworkerid;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.comment = comment;
    }

    public Booking(){
    }

    public void setId(int id) {
    }

    public void setCustomerId(int customerId) {
    }

    public void setCoworkerId(int coworkerId) {
    }

    public void setBookingDate(LocalDate bookingDate) {
    }

    public void setStartTime(LocalTime startTime) {
    }

    public void setDurationMinutes(int durationMinutes) {
    }

    public void setComment(String comment) {
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public int getCoworkerId(){
        return coworkerid;
    }

    public int getDuration() {
        return duration;
    }

    public String getComment() {
        return comment;
    }

    public int getCustomerId() {
        return customerid;
    }
}