package com.glassy.salesmanager.MVP.Presenters;

import com.glassy.salesmanager.Events.UserEvents;
import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Seller;
import com.glassy.salesmanager.MVP.Models.SellerModel;
import com.glassy.salesmanager.MVP.Models.User;
import com.glassy.salesmanager.MVP.Views.SellerView;

/**
 * Created by glassy on 12/16/17.
 */

public class SellerPresenter implements UserEvents{
    protected final SellerModel model;
    protected final SellerView view;

    public SellerPresenter(SellerView view) {
        this.model = new SellerModel(this);
        this.view = view;
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
