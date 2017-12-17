package com.glassy.salesmanager.MVP.Presenters;

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
        this.model = new ClientModel(this);
        this.view = view;
        model.loadClients();
    }

    @Override
    public void SuccesEvent() {
        view.testSuccess();
    }

    @Override
    public void FailEvent() {
        view.testFailure();
    }

    @Override
    public void loadListSeller(ArrayList<Client> clients) {
        view.loadListClient(clients);

    }
}
