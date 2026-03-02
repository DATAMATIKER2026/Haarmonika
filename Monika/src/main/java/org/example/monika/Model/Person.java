package org.example.monika.Model;
// Superclass that contains common attributes for Customer and Coworker
public class Person {

    private String fname;
    private String mail;
    private int tlfnr;

    // Constructor used by subclasses with super(...)
    public Person(String fname, String mail, int tlfnr) {
        this.fname = fname;
        this.mail = mail;
        this.tlfnr = tlfnr;
    }

    // Getter
    public String getFname() {
        return fname;
    }
    public String getMail() {
        return mail;
    }
    public int getTlfnr() {
        return tlfnr;
    }
    // Setter 
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public void setTlfnr(int tlfnr) {
        this.tlfnr = tlfnr;
    }
}