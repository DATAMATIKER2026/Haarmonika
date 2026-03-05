package org.example.monika.service;

import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Coworker;
import org.example.monika.Repository.CoworkerRepository;

public class LoginService {

    private final CoworkerRepository coworkerRepo;

    public LoginService(){
        this.coworkerRepo = new CoworkerRepository(new DbConfig());
    }

    public Coworker login(String email, String password)throws IllegalArgumentException{
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Email må ikke være tom");
        }
        if (password == null || password.isBlank()){
            throw new IllegalArgumentException("Password må ikke være tomt");
        }

        Coworker c = coworkerRepo.findByEmail(email);

        if (c == null){
            throw new IllegalArgumentException("Forkert password eller mail");
        }

        if (c.getPassword() == null || !c.getPassword().equals(password)){
            throw new IllegalArgumentException("Forkert password eller mail");
        }
        return c;
    }
}
