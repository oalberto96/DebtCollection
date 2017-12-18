package com.glassy.salesmanager.MVP.Presenters;

import android.content.Context;

import com.glassy.salesmanager.Events.UserEvents;
import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.ClientModel;
import com.glassy.salesmanager.MVP.Views.ClientView;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public class ClientPresenter implements UserEvents{
    protected final ClientModel model;
    protected final ClientView view;

    public ClientPresenter(ClientView view) {
        this.view = view;
        this.model = new ClientModel(this);
        model.loadClients();
    }

    @Override
    public void addClientEvent() {
        model.loadClients();
    }

    @Override
    public void FailEvent() {
        view.testFailure();
    }

    @Override
    public void loadListSeller(ArrayList<Client> clients) {
        if(clients.size()>0){
            view.loadListClient(clients);
        }
    }

    @Override
    public void addNewClient(Client newClient) {
        model.createClient(newClient);
    }

    @Override
    public Context getAppContext() {
        return view.getContext();
    }


}
