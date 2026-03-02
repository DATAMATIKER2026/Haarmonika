package org.example.monika.Repository;
import org.example.monika.Model.Customer;

import java.sql.SQLException;

public interface CustomerInterface {
    int create(Customer customer) throws SQLException;
    boolean update(Customer customer) throws SQLException;
    boolean deleteById(int id) throws SQLException;
}
