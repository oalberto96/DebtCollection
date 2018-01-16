package com.glassy.salesmanager.Client.ReadClient;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;

/**
 * Created by glassy on 1/14/18.
 */

public interface IReadClientActivityView {
    Context getActivityContext();
    void finishActivitySuccess();

    void fillForm(Client client);

    void getClientFailure();
}
