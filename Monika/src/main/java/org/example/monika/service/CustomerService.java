package org.example.monika.service;

import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Customer;
import org.example.monika.Repository.CustomerRepository;

import java.sql.SQLException;

public class CustomerService {
    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = new CustomerRepository(new DbConfig());
    }

    public Customer create(Customer customer)throws Exception{
        //Validering af customer
        try {
            validate(customer);
            int newId = customerRepo.create(customer);
            customer.setCustomerId(newId);
            return customer;
        } catch (IllegalArgumentException iae){
            throw new IllegalArgumentException("Fejl i validering af kunde");
        } catch (SQLException sqle){
            throw new SQLException("Fejl i validering af kunde");
        } catch (Exception e){
            throw new Exception("Kritisk fejl, kontakt administrator");
        }
    }

    // I tilfælde af vi skal tilføje en remove button
    public boolean deleteById(int id) throws Exception{
        if (id <= 0) throw new IllegalArgumentException("Ukendt Id");
        return customerRepo.deleteById(id);
    }


    private void validate(Customer c){
        if (c == null) throw new IllegalArgumentException("Tomt kunde id");
        if (c.getFname() == null || c.getFname().isEmpty()) throw new IllegalArgumentException("Tomt kunde navn");
        if (c.getMail() == null || c.getMail().isEmpty()) throw new IllegalArgumentException("Tomt kunde mail");
        if (c.getTlfnr() <= 0) throw new IllegalArgumentException("Telefon nummer kan ikke være mindre end 0");
    }
}
