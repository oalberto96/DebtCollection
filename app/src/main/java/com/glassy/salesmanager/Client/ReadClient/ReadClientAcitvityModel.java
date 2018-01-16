package com.glassy.salesmanager.Client.ReadClient;

import android.content.ContentValues;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

/**
 * Created by glassy on 1/14/18.
 */

public class ReadClientAcitvityModel {

    private IReadClientActivityPresenter presenter;
    private DebtCollectionDBHelper dbHelper;

    public ReadClientAcitvityModel(IReadClientActivityPresenter presenter){
        this.presenter = presenter;
        dbHelper = new DebtCollectionDBHelper(presenter.getActivityContext());
    }

    public void getClient(int idClient) {
        Client client = dbHelper.getClient(idClient);
        presenter.getClientSuccess(client);
    }
}
