package com.glassy.salesmanager.MVP.Views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.glassy.salesmanager.Events.UserEvents;
import com.glassy.salesmanager.MVP.Models.Client;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public interface ClientView {
    public void loadListClient(ArrayList<Client> clients);
    public void readClient(Client client);
    public Context getContext();
}
