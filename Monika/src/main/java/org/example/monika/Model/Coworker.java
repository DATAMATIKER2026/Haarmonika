package org.example.monika.Model;
public class Coworker extends Person {

    // Unique attributes only for Coworker
    private int coworkerId;
    private String password;
    private int securitylevel;

    // Full constructor
    public Coworker(int coworkerId, String fname, String mail, int tlfnr,
                    String password, int securitylevel) {
        // Calls Person constructor
        super(fname, mail, tlfnr);
        this.coworkerId = coworkerId;
        this.password = password;
        this.securitylevel = securitylevel;
    }

    // Constructor without ID
    public Coworker(String fname, String mail, int tlfnr,
                    String password, int securitylevel) {
        // Calls Person constructor
        super(fname, mail, tlfnr);
        this.password = password;
        this.securitylevel = securitylevel;
    }

    @Override
    public String toString() {
        return getFname();
    }

    // Getter for coworkerId
    public int getCoworkerId() {
        return coworkerId;
    }

    // Setter for coworkerId
    public void setCoworkerId(int coworkerId) {
        this.coworkerId = coworkerId;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for security level
    public int getSecuritylevel() {
        return securitylevel;
    }

    // Setter for security level
    public void setSecuritylevel(int securitylevel) {
        this.securitylevel = securitylevel;
    }
}
