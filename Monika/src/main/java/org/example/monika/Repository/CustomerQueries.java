package org.example.monika.Repository;

import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Customer;

import java.util.List;

public class CustomerQueries {

    private final DbConfig db;
    public CustomerQueries(DbConfig db){
        this.db = db;
    }

    /*
    public List<Customer> searchByName (String fname){
        String sql = "SELECT *\n" +
                "FROM customer\n" +
                "JOIN "
    }

     */
}
