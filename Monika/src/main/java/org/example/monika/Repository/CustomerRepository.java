package org.example.monika.Repository;
import  org.example.monika.Infastructure.DbConfig;


import org.example.monika.Model.Customer;

import java.sql.*;

public class CustomerRepository implements CustomerInterface {

    private final DbConfig db;

    public CustomerRepository(DbConfig db) {
        this.db = db;
    }

    @Override
    public int create(Customer customer) throws SQLException {
         String sql = " INSERT INTO customer (fname, mail, tlfnr, address) VALUES (?, ?, ?, ?) ";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, customer.getFname());
            ps.setString(2, customer.getMail());
            ps.setInt(3, customer.getTlfnr());
            ps.setString(4, customer.getAddress());

            ps.executeUpdate();
                // henter auto generet id
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    customer.setCustomerId(newId);
                    return newId;
                }
            }
        }

        throw new SQLException("Could not create customer: no generated key returned.");
    }

    @Override
    public boolean update (Customer customer) throws SQLException{
    String sql = "UPDATE customer SET fname=?, mail=?, tlfnr=? WHERE customerId=?";

    try (Connection con = db.getConnection();
    PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, customer.getFname());
        ps.setString(2, customer.getMail());
        ps.setInt(3, customer.getTlfnr());
        ps.setInt(4, customer.getCustomerId());
        // execute
        int affectedRows = ps.executeUpdate();
        // retuns true if 1 row was affected
        return affectedRows == 1;
    }
   }
   @Override
    public boolean deleteById(int id) throws SQLException {
        //Finale = the varible can only be assigned once no changes can be made later
        final String sql = "DELETE FROM customer WHERE customerId=?";

        try (Connection con = db.getConnection();

        PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            // execute
            int affectedRows = ps.executeUpdate();
            // return true if 1 row was affected
            return affectedRows == 1;
        }

    }

}
