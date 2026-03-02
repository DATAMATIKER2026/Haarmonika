package org.example.monika.service;

import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;
import org.example.monika.Repository.CoworkerRepository;
import org.example.monika.Repository.CustomerRepository;

import java.util.Comparator;
import java.util.List;

public class BookingService {
    private final CoworkerRepository coworkerRepo;
    private final CustomerRepository customerRepo;

    public BookingService(CoworkerRepository coworkerRepo, CustomerRepository customerRepo) {
        this.coworkerRepo = coworkerRepo;
        this.customerRepo = customerRepo;
    }

    public List<Coworker> getAllCoworkers() {
        List<Coworker> list = coworkerRepo.findAll();
        list.sort(Comparator.comparing(Coworker::getFname));
        return list;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = customerRepo.findAll();
        list.sort(Comparator.comparing(Customer::getFname));
        return list;
    }
}
