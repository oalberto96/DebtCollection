package com.glassy.salesmanager.Client;

import android.content.Context;

/**
 * Created by glassy on 1/14/18.
 */

public class EditClientActivityPresenter implements IEditClientActivityPresenter {

    Client client;
    IEditclientAcitivtyView view;
    EditClientAcitvityModel model;

    public EditClientActivityPresenter(IEditclientAcitivtyView view) {
        this.view = view;
        model = new EditClientAcitvityModel(this);
    }

    @Override
    public Context getActivityContext() {
        return view.getActivityContext();
    }

    @Override
    public void updateClientSuccess() {
        view.finishActivitySuccess();
    }

    @Override
    public void getClientSuccess(Client client) {
        this.client = client;
        view.fillForm(client);
    }

    public void updateClient() {
        model.updateClient(this.client);
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
