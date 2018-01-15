package com.glassy.salesmanager.Client;

import android.content.Context;

/**
 * Created by glassy on 1/14/18.
 */

public interface IEditclientAcitivtyView {
    Context getActivityContext();
    void finishActivitySuccess();

    void fillForm(Client client);

    void getClientFailure();
}
