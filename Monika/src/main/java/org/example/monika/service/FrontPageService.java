package org.example.monika.service;

import javafx.collections.ObservableList;
import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Booking;
import org.example.monika.Model.Customer;
import org.example.monika.Repository.BookingRepository;
import org.example.monika.Repository.CustomerRepository;

import java.util.List;

public class FrontPageService {

    private final CustomerRepository customerRepo;
    private final BookingRepository bookingRepo;

    public FrontPageService() {
        this.customerRepo = new CustomerRepository(new DbConfig());
        this.bookingRepo = new BookingRepository(new DbConfig());
    }

    public void searchAndSort(String searchTerm,
                              ObservableList<String> list1,
                              ObservableList<String> list2,
                              ObservableList<String> list3,
                              ObservableList<String> list4) {

        list1.clear();
        list2.clear();
        list3.clear();
        list4.clear();

        List<Customer> foundCustomers = customerRepo.findByName(searchTerm);

        if (foundCustomers.size() != 1) {
            return;
        }
        Customer customer = foundCustomers.get(0);

        List<Booking> bookings = customerRepo.findBookingByCustomerId(customer.getCustomerId());

        for (Booking b : bookings) {

            String entry = customer.getFname() + " | "
                    + b.getDate() + " | "
                    + b.getStartTime();

            switch (b.getCoworkerId()) {
                case 1 -> list1.add(entry);
                case 2 -> list2.add(entry);
                case 3 -> list3.add(entry);
                case 4 -> list4.add(entry);

            }
        }
    }
}