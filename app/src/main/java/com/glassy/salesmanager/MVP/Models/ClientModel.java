package com.glassy.salesmanager.MVP.Models;

import com.glassy.salesmanager.Events.UserEvents;

import java.util.ArrayList;

/**
 * Created by glassy on 12/17/17.
 */

public class ClientModel {
    final protected UserEvents events;

    public ClientModel(UserEvents events) {
        this.events = events;
    }

    public void loadClients(){
        events.loadListSeller(getClients());
    }

    public ArrayList<Client> getClients(){
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(new Client("Jane","Doe"));
        return clients;
    }

}
