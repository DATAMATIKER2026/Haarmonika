package org.example.monika.Model;
public class Customer extends Person {

    // Unique attribute only for Customer
    private int customerId;

    // Full constructor
    public Customer(int customerId, String fname, String mail, int tlfnr) {
        // Calls constructor in Person class
        super(fname, mail, tlfnr);
        this.customerId = customerId;
    }

    // Constructor without ID
    public Customer(String fname, String mail, int tlfnr) {
        // Calls Person constructor
        super(fname, mail, tlfnr);
    }

    // Getter for customerId
    public int getCustomerId() {
        return customerId;
    }

    // Setter for customerId
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

public class Costumer {
}
