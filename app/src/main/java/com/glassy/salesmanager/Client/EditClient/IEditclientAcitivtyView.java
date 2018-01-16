package com.glassy.salesmanager.Client.EditClient;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;

/**
 * Created by glassy on 1/14/18.
 */

public interface IEditclientAcitivtyView {
    Context getActivityContext();
    void finishActivitySuccess();

    void fillForm(Client client);

    void getClientFailure();
}
