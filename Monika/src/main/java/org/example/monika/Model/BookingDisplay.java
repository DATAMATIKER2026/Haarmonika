package org.example.monika.Model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDisplay {
    private String customerName;
    private LocalDate date;
    private LocalTime startTime;
    private int coworkerId;

    public BookingDisplay(String customerName, LocalDate date, LocalTime startTime, int coworkerId) {
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.coworkerId = coworkerId;
    }

    public String getCustomerName() {
        return customerName;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public int getCoworkerId() {
        return coworkerId;
    }
}
