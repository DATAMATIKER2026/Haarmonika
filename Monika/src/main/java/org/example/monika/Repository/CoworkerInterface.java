package org.example.monika.Repository;

import org.example.monika.Model.Coworker;

import java.util.List;

public interface CoworkerInterface {
    Coworker findByEmail(String email);
    List<Coworker> findAll();
}
