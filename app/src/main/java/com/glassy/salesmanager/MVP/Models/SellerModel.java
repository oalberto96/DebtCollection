package com.glassy.salesmanager.MVP.Models;

import com.glassy.salesmanager.Events.UserEvents;

/**
 * Created by glassy on 12/17/17.
 */

public class SellerModel {
    final protected UserEvents events;

    public SellerModel(UserEvents events) {
        this.events = events;
    }


}
