package org.example.monika.Infastructure;

import org.example.monika.Repository.DataAccessException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConfig {

    private final String url;
    private final String user;
    private final String password;

    public DbConfig() {
        Properties props = new Properties();

        try (InputStream is = DbConfig.class.getResourceAsStream("/db.properties");) {
            if (is == null) {
                throw new DataAccessException("Kunne ikke finde db.properties i resources");
            }
            props.load(is);
        } catch (Exception e) {
            throw new DataAccessException("Kunne ikke læse db.properties");
        }

        this.url = props.getProperty("db.url");
        this.user = props.getProperty("db.user");
        this.password = props.getProperty("db.password");
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new DataAccessException("Kunne ikke oprette forbindelse til databasen" + e.getMessage());
        }
    }
}

