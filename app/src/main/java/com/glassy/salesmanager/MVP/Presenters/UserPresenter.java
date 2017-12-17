package com.glassy.salesmanager.MVP.Presenters;

import com.glassy.salesmanager.Events.UserEvents;
import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Seller;
import com.glassy.salesmanager.MVP.Models.User;
import com.glassy.salesmanager.MVP.Views.UserView;

/**
 * Created by glassy on 12/16/17.
 */

public class UserPresenter implements UserEvents{
    protected final Client model;
    protected final UserView view;

    public UserPresenter(UserView view) {
        this.model = new Client(this);
        this.view = view;
        model.test();
    }

    @Override
    public void SuccesEvent() {
        view.testSuccess();
    }

    @Override
    public void FailEvent() {
        view.testFailure();
    }
}
