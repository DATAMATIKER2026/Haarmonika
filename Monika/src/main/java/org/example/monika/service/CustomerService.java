package org.example.monika.service;

import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;
import org.example.monika.Repository.CustomerRepository;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepo = new CustomerRepository(new DbConfig());
    }

    public Customer create(Customer customer) throws SQLException{
        int newId = customerRepo.create(customer);
        customer.setCustomerId(newId);
        return customer;
    }

    public boolean update(Customer customer) throws SQLException{
        validate(customer);
        if (customer.getCustomerId() <= 0){
            throw new IllegalArgumentException("Customer id has to be higher than 0");
        }
        return customerRepo.update(customer);
    }

    public boolean deleteById(int id) throws SQLException {
        if (id <= 0) throw new IllegalArgumentException("Unknown ID");
        return customerRepo.deleteById(id);
    }

    private void validate(Customer c){
        if (c == null) throw new IllegalArgumentException("Customer is null");
        if (c.getFname() == null || c.getFname().isEmpty()) throw new IllegalArgumentException("Customer name is empty");
        if (c.getMail() == null || c.getMail().isEmpty()) throw new IllegalArgumentException("Customer mail is empty");
        if (c.getTlfnr() <= 0) throw new IllegalArgumentException("Telephone nummer cant be less then 0 or 0");
    }
}
