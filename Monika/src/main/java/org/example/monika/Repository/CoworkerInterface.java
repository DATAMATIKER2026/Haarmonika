package org.example.monika.Repository;

import org.example.monika.Model.Coworker;

public interface CoworkerInterface {
    Coworker findByEmail(String email);
}
