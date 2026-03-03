package org.example.monika.Model;

import java.util.List;

public class Customer extends Person {

    // Unique attribute only for Customer
    private int customerId;
    private String address;
    // This attribute is used for comparing coworkerId
    private int coworkerId;
    private List<Booking> bookings;

    // Full constructor
    public Customer(int customerId, String fname, String mail, int tlfnr, String address) {
        // Calls constructor in Person class
        super(fname, mail, tlfnr);
        this.customerId = customerId;
        this.address = address;
    }

    public Customer(int customerId, String fname, String mail, int tlfnr) {
        super(fname, mail, tlfnr);
        this.customerId = customerId;
    }

    public Customer(String fname, String mail, int tlfnr, String address) {
        // Calls Person constructor
        super(fname, mail, tlfnr);
        this.address = address;
    }

    @Override
    public String toString() {
        return getFname();
    }

    // Getter for customerId
    public int getCustomerId() {
        return customerId;
    }
    public int getCoworkerId() {return coworkerId;}

    // Setter for customerId
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
}

