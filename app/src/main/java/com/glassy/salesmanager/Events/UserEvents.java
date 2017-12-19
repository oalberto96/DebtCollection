package com.glassy.salesmanager.Events;

import android.content.Context;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Seller;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public interface UserEvents {
    public void addClientEvent();
    public void FailEvent();
    public void loadListSeller(ArrayList<Client> clients);
    public void addNewClient(Client newClient);
    public Context getAppContext();
    void readClientSuccessEvent(Client client);
    void updateClientSuccessEvent(int id);
}
