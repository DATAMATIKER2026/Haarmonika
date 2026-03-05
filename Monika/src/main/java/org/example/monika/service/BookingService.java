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

    public BookingService(CoworkerRepository coworkerRepo, CustomerRepository customerRepo, BookingRepository bookingRepo) {
        this.coworkerRepo = coworkerRepo;
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
    }

    public void createBooking(int customerId, int coworkerId, LocalDate date, LocalTime start, int durationMinutes, String comment) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Ukendt customerId");
        }

        if (coworkerId <= 0) {
            throw new IllegalArgumentException("Ukendt coworkerId");
        }

        //Håndtering af tidspunkter
        Objects.requireNonNull(date, "Du skal vælge en dato");
        Objects.requireNonNull(start, "Du skal vælge et start tidspunkt for tiden");

        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Du kan ikke have en negativ tid");
        }

        if (durationMinutes >= 90) {
            throw new IllegalArgumentException("Bookingen kan ikke varer mere end 90 minutter");
        }

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Du kan ikke vælge en tid i datid");
        }

        //Tjekker om coworker er ledig. Hvis ikke kalder vi en exception (som vi kan printe i UI)
        boolean available = isCoworkerAvailable(coworkerId, date, start, durationMinutes);
        if (!available) {
            throw new IllegalArgumentException("Den valgte frisør er optaget på den angivet dato eller tidspunkt");
        }

        //Inserter bookings i tilfælde af at der er en ledig frisør.
        try {
            bookingRepo.insertBooking(customerId, coworkerId, date, start, durationMinutes, comment);
        } catch (Exception e) {
            throw new RuntimeException("Kritisk fejl, kontakt administrator");
        }
    }

    //Vi tjekker om der er en overlappende bookings FØR vi opretter bookingen.
    public boolean isCoworkerAvailable(int coworkerId, LocalDate date, LocalTime start, int durationMinutes) {
        return bookingRepo.findOverlappingBookings(coworkerId, date, start, durationMinutes).isEmpty();
    }

    public List<Coworker> getAllCoworkers() {
        try {
            List<Coworker> list = coworkerRepo.findAll();
            list.sort(Comparator.comparing(Coworker::getFname));
            return list;
        } catch (Exception e) {
            throw new RuntimeException("Kritisk fejl, kontakt administrator");
        }
    }

    public List<Customer> getAllCustomers() {
        try {
            List<Customer> list = customerRepo.findAll();
            list.sort(Comparator.comparing(Customer::getFname));
            return list;
        } catch (Exception e) {
            throw new RuntimeException("Kritisk fejl kontakt administrator");
        }
    }
}
