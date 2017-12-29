package com.glassy.salesmanager.MVP.Models;

/**
 * Created by glassy on 12/16/17.
 */

public class User {
    protected String first_name;
    protected String last_name;

    public User(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
