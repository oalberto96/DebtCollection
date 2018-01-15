package com.glassy.salesmanager.Client;

import android.content.Context;

/**
 * Created by glassy on 1/14/18.
 */

interface IEditClientActivityPresenter {
    Context getActivityContext();
    void updateClientSuccess();

    void getClientSuccess(Client client);
}
