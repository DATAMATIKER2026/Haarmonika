package org.example.monika.Model;
public class Booking{
    private int id;
    private int customerid;
    private int coworkerid;
    private String makeDate;
    private String currentDate;
    private String bookingTime;

    //full contructor get booking from database
    public Booking(int id, int customerid, int coworkerid, String makeDate, String currentDate) {
        this.id = id;
        this.customerid = customerid;
        this.coworkerid = coworkerid;
        this.makeDate = makeDate;
        this.currentDate = currentDate;
        this.bookingTime = bookingTime;
    }

    // constructor without ID to make new bookings, database generates id automatically
    public Booking( int customerid, int coworkerid, String makeDate, String currentDate, String bookingTime) {
        this.customerid = customerid;
        this.coworkerid = coworkerid;
        this.makeDate = makeDate;
        this.currentDate = currentDate;
        this.bookingTime = bookingTime;
    }


}