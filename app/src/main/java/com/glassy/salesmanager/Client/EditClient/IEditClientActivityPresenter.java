package com.glassy.salesmanager.Client.EditClient;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;

/**
 * Created by glassy on 1/14/18.
 */

interface IEditClientActivityPresenter {
    Context getActivityContext();
    void updateClientSuccess();

    void getClientSuccess(Client client);
}
