package com.glassy.salesmanager.Client;

import android.content.Context;

/**
 * Created by glassy on 1/14/18.
 */

public class AddClientActivityPresenter implements IAddClientActivityPresenter {
    IAddClientActivityView view;
    AddClientActivityModel model;

    public AddClientActivityPresenter(IAddClientActivityView view){
        this.view = view;
        model = new AddClientActivityModel(this);
    }

    @Override
    public Context getActivityContext() {
        return view.getActivityContext();
    }

    @Override
    public void insertClientSuccess() {
        view.finishActivitySuccess();
    }

    public void insertClient(Client client) {
        model.insertClient(client);
    }
}