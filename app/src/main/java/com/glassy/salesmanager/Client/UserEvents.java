package com.glassy.salesmanager.Client;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public interface UserEvents {
    void loadClientListSuccess(ArrayList<Client> clients);
    Context getAppContext();
    void deleteClientSuccess();

}
