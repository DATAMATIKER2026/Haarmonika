package org.example.monika.Repository;

import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Coworker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CoworkerRepository implements CoworkerInterface {
    private final DbConfig db;

    public CoworkerRepository(DbConfig db) {
        this.db = db;
    }

    public Coworker findByEmail(String email) {
        String sql = "SELECT * FROM coworker WHERE mail like ?";

        try (Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, "%" + email + "%");

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new Coworker(
                        rs.getInt("coworkerId"),
                        rs.getString("fname"),
                        rs.getString("mail"),
                        rs.getInt("tlfnr"),
                        rs.getString("pass"),
                        rs.getInt("securitylevel")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not find a user");
        }
        return null;
    }
}
