package org.example.monika.service;

import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;
import org.example.monika.Repository.BookingRepository;
import org.example.monika.Repository.CoworkerRepository;
import org.example.monika.Repository.CustomerRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BookingService {
    private final CoworkerRepository coworkerRepo;
    private final CustomerRepository customerRepo;
    private final BookingRepository bookingRepo;

    public BookingService(CoworkerRepository coworkerRepo, CustomerRepository customerRepo,  BookingRepository bookingRepo) {
        this.coworkerRepo = coworkerRepo;
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
    }

    public void createBooking(int customerId, int coworkerId, LocalDate date, LocalTime start, int durationMinutes, String comment){
        if (customerId <= 0){
            throw new IllegalArgumentException("Ukendt customerId");
        }

        if (coworkerId <= 0){
            throw new IllegalArgumentException("Ukendt coworkerId");
        }

        Objects.requireNonNull(date, "Du skal vælge en dato");
        Objects.requireNonNull(start, "Du skal vælge et start tidspunkt for tiden");
        if (durationMinutes <= 0){
            throw new IllegalArgumentException("Du kan ikke have en negativ tid");
        }

        if (durationMinutes >= 90){
            throw new IllegalArgumentException("Bookingen kan ikke varer mere end 90 minutter");
        }

        if (date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Du kan ikke vælge en tid i datid");
        }

        boolean available = isCoworkerAvailable(coworkerId, date, start, durationMinutes);
        if (!available){
            throw new IllegalArgumentException("Den valgte frisør er optaget på den angivet dato eller tidspunkt");
        }

        bookingRepo.insertBooking(customerId, coworkerId, date, start, durationMinutes, comment);
    }

    public boolean isCoworkerAvailable(int coworkerId, LocalDate date, LocalTime start, int durationMinutes){
        return bookingRepo.findOverlappingBookings(coworkerId, date, start, durationMinutes).isEmpty();
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
