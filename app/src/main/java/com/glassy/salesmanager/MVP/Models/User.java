package com.glassy.salesmanager.MVP.Models;

import com.glassy.salesmanager.Events.UserEvents;

/**
 * Created by glassy on 12/16/17.
 */

public class User {
    final protected UserEvents events;

    protected User(UserEvents events) {

        this.events = events;
    }

    public void test(){
        events.FailEvent();
    }

}
