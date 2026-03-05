package org.example.monika.Repository;
import org.example.monika.Model.Booking;
import org.example.monika.Model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerInterface {
    int create(Customer customer) throws SQLException;
    boolean deleteById(int id) throws SQLException;
    List<Customer> findAll() throws SQLException;
    List<Booking> findBookingByCustomerId(int id) throws SQLException;
    List<Customer> findByName(String name);
}
