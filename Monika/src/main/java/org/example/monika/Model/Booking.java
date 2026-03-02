package org.example.monika.Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Booking{
    private int id;
    private int customerid;
    private int coworkerid;
    private final LocalDate date;
    private final LocalTime startTime;
    private final Duration duration;
    private final String comment;
    private Coworker coworker;



    //full contructor get booking from database
    public Booking(int id, int customerid, int coworkerid, LocalDate date, LocalTime startTime, Duration duration, String comment) {
        this.id = id;
        this.customerid = customerid;
        this.coworkerid = coworkerid;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.comment = comment;
    }

    // constructor without ID to make new bookings, database generates id automatically
    public Booking( int customerid, int coworkerid, LocalDate data, LocalTime startTime, Duration duration, String comment) {
        this.customerid = customerid;
        this.coworkerid = coworkerid;
        this.date = data;
        this.startTime = startTime;
        this.duration = duration;
        this.comment = comment;
    }


}