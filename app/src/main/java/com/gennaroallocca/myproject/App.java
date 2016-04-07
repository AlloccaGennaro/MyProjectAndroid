package com.gennaroallocca.myproject;

import android.app.Application;

/**
 * Created by Gennaro Allocca on 01/04/2016.
 */
public class App extends Application {
    Session session = new Session();

    public String getEmail() {
        return session.email;
    }

    public void setEmail(String email) {
        session.email = email;
    }
}
