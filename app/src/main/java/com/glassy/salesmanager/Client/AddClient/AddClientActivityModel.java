package com.glassy.salesmanager.Client.AddClient;

import android.content.ContentValues;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

/**
 * Created by glassy on 1/14/18.
 */

public class AddClientActivityModel {

    private IAddClientActivityPresenter presenter;
    private DebtCollectionDBHelper dbHelper;

    public AddClientActivityModel(IAddClientActivityPresenter presenter){
        this.presenter = presenter;
        dbHelper = new DebtCollectionDBHelper(presenter.getActivityContext());
    }

    public void insertClient(Client client){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DebtCollectionContract.Client.COLUMN_FIRST_NAME,client.getFirst_name());
        contentValues.put(DebtCollectionContract.Client.COLUMN_LAST_NAME,client.getLast_name());
        contentValues.put(DebtCollectionContract.Client.COLUMN_ADDRESS,client.getAddress());
        contentValues.put(DebtCollectionContract.Client.COLUMN_PHONE_NUMBER,client.getPhoneNumber());
        contentValues.put(DebtCollectionContract.Client.COLUMN_TIN,client.getTin());
        dbHelper.insertClient(contentValues);
        presenter.insertClientSuccess();
    }
}
