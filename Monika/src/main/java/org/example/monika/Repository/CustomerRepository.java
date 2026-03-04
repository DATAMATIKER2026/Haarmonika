package org.example.monika.Repository;

import org.example.monika.Infastructure.DbConfig;


import org.example.monika.Model.Booking;
import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET fname=?, mail=?, tlfnr=? WHERE customerId=?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getFname());
            ps.setString(2, customer.getMail());
            ps.setInt(3, customer.getTlfnr());
            ps.setInt(4, customer.getCustomerId());
            ps.setString(5, customer.getAddress());
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

    public List<Customer> findByName(String name) {
        List<Customer> customers = new ArrayList<>();
        // Finds customerid, name, mail and phonenumber from customer table in database
        // Finds coworkerid from bookingtable and left joins the two ids
        String sql = "SELECT customerId, fname, mail, tlfnr\n" +
                "                FROM customer\n" +
                "                WHERE fname LIKE ?\n" +
                "                ORDER BY fname";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customers.add(new Customer(
                            rs.getInt("customerId"),
                            rs.getString("fname"),
                            rs.getString("mail"),
                            rs.getInt("tlfnr")
                    ));
                }
            }
        } catch (SQLException e) {
            //Debugging
            System.err.println("SQL Error in findByName: " + e.getMessage());
            e.printStackTrace();
        }
        return customers;
    }

    public List<Booking> findBookingByCustomerId(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = """
                    SELECT id, coworkerId, booking_date, start_time, duration_minutes, comment
                    FROM bookings
                    WHERE customerId = ?
                    ORDER BY booking_date, start_time
                """;

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookings.add(new Booking(
                            rs.getInt("id"),
                            customerId,
                            rs.getInt("coworkerId"),
                            rs.getDate("booking_date").toLocalDate(),
                            rs.getTime("start_time").toLocalTime(),
                            rs.getInt("duration_minutes"),
                            rs.getString("comment")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fejl i at finde booking");
        }
        return bookings;
    }

    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";

        List<Customer> customer = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                customer.add(new Customer(
                        rs.getInt("customerId"),
                        rs.getString("fname"),
                        rs.getString("mail"),
                        rs.getInt("tlfnr"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

}
