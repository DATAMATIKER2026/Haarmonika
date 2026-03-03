package org.example.monika.Repository;


import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final DbConfig db;

    public BookingRepository(DbConfig db) {
        this.db = db;
    }

    public void insertBooking(int customerId, int coworkerId, LocalDate bookingDate, LocalTime startTime, int durationMinutes, String comment) {
        final String sql = """
                INSERT INTO bookings
                (customerId, coworkerId, booking_date, start_time, duration_minutes, comment)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ps.setInt(2, coworkerId);
            ps.setDate(3, Date.valueOf(bookingDate));
            ps.setTime(4, Time.valueOf(startTime));
            ps.setInt(5, durationMinutes);
            ps.setString(6, comment);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke indsætte booking");
        }
    }

    public List<Booking> findOverlappingBookings(int coworkerId,
                                                 LocalDate bookingDate,
                                                 LocalTime requestedStart,
                                                 int requestedDurationMinutes) {
        final String sql = """
                SELECT id, customerId, coworkerId, booking_date, start_time, duration_minutes, comment
                              FROM bookings
                              WHERE coworkerId = ?
                                AND booking_date = ?
                                AND start_time < ADDTIME(?, SEC_TO_TIME(? * 60))
                                AND ADDTIME(start_time, SEC_TO_TIME(duration_minutes * 60)) > ?
                """;

        List<Booking> results = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, coworkerId);
            ps.setDate(2, Date.valueOf(bookingDate));
            ps.setTime(3, Time.valueOf(requestedStart));
            ps.setInt(4, requestedDurationMinutes);
            ps.setTime(5, Time.valueOf(requestedStart));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking b = new Booking();
                    b.setId(rs.getInt("id"));
                    b.setCustomerId(rs.getInt("customerId"));
                    b.setCoworkerId(rs.getInt("coworkerId"));
                    b.setBookingDate(rs.getDate("booking_date").toLocalDate());
                    b.setStartTime(rs.getTime("start_time").toLocalTime());
                    b.setDurationMinutes(rs.getInt("duration_minutes"));
                    b.setComment(rs.getString("comment"));
                    results.add(b);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved søgning af overlapped bookinger");
        }
        return results;
    }
}

