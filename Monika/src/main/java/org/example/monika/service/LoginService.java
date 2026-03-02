package org.example.monika.service;

import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Model.Coworker;
import org.example.monika.Repository.CoworkerRepository;

public class LoginService {

    private final CoworkerRepository coworkerRepo;

    public LoginService(){
        this.coworkerRepo = new CoworkerRepository(new DbConfig());
    }

    public Coworker login(String email, String password)throws Exception{
        if (email == null || email.isBlank()){
            throw new Exception("Email må ikke være tom");
        }
        if (password == null || password.isBlank()){
            throw new Exception("Password må ikke være tomt");
        }

        Coworker c = coworkerRepo.findByEmail(email);

        if (c == null){
            throw new Exception("Bruger findes ikke");
        }

        if (!c.getPassword().equals(password)){
            throw new Exception("Forkert password eller navn");
        }
        return c;
    }
}
