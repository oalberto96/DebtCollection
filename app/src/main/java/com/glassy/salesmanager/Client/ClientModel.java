package com.glassy.salesmanager.Client;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

import java.util.ArrayList;

/**
 * Created by glassy on 12/17/17.
 */

public class ClientModel {
    final protected UserEvents events;
    DebtCollectionDBHelper dbHelper;

    public ClientModel(UserEvents events) {
        this.events = events;
        dbHelper = new DebtCollectionDBHelper(events.getAppContext());
    }

    public void loadClients(){
        events.loadClientListSuccess(dbHelper.getClients());
    }


    public void deleteClient(int id) {
        dbHelper.deleteClient(id);
        events.loadClientListSuccess(dbHelper.getClients());
    }
}
