package com.glassy.salesmanager.Client.ReadClient;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;

/**
 * Created by glassy on 1/14/18.
 */

public class ReadClientActivityPresenter implements IReadClientActivityPresenter {

    Client client;
    IReadClientActivityView view;
    ReadClientAcitvityModel model;

    public ReadClientActivityPresenter(IReadClientActivityView view) {
        this.view = view;
        model = new ReadClientAcitvityModel(this);
    }

    @Override
    public Context getActivityContext() {
        return view.getActivityContext();
    }

    @Override
    public void getClientSuccess(Client client) {
        this.client = client;
        view.fillForm(client);
    }

    public void getClient(int idClient) {
        if (idClient > 0){
            model.getClient(idClient);
        }
        else {
            view.getClientFailure();
        }
    }
}
