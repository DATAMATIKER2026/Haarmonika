package org.example.monika.ui;

import org.example.monika.Model.Coworker;

public class UserSession {
    private static Coworker currentUser;

    public static void set(Coworker user) {
        currentUser = user;
    }

    public static Coworker get() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}