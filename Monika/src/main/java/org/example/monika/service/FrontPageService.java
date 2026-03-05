package org.example.monika.service;

import javafx.collections.ObservableList;
import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Booking;
import org.example.monika.Model.BookingDisplay;
import org.example.monika.Model.Customer;
import org.example.monika.Repository.BookingRepository;
import org.example.monika.Repository.CustomerRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        if (foundCustomers == null || foundCustomers.size() != 1) {
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

    public void loadAllBookings(LocalDate date,
                                ObservableList<String> List1,
                                ObservableList<String> List2,
                                ObservableList<String> List3,
                                ObservableList<String> List4) throws Exception {
        try {
            List<BookingDisplay> allBookings = bookingRepo.getAllBookings(date);

            List1.clear();
            List2.clear();
            List3.clear();
            List4.clear();

        if (allBookings != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (BookingDisplay b : allBookings) {
                    String dateString = b.getDate() != null ? b.getDate().format(dtf) : "Ingen dato";
                    String displayText = b.getCustomerName() + " | " + dateString + " " + b.getStartTime();
                    int coworkerId = b.getCoworkerId();

                    switch (coworkerId) {
                        case 1:
                            List1.add(displayText);
                            break;
                        case 2:
                            List2.add(displayText);
                            break;
                        case 3:
                            List3.add(displayText);
                            break;
                        case 4:
                            List4.add(displayText);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("Kritisk fejl, kontakt administrator", ex);
        }
    }
}