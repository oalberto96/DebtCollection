package com.glassy.salesmanager.MVP.Presenters;

import android.content.Context;

import com.glassy.salesmanager.Events.ProductEvents;
import com.glassy.salesmanager.Events.SaleEvents;
import com.glassy.salesmanager.MVP.Models.Product;
import com.glassy.salesmanager.MVP.Models.ProductModel;
import com.glassy.salesmanager.MVP.Models.Sale;
import com.glassy.salesmanager.MVP.Models.SaleModel;
import com.glassy.salesmanager.MVP.Views.ProductView;
import com.glassy.salesmanager.MVP.Views.SaleView;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public class SalePresenter implements SaleEvents {
    protected final SaleModel model;
    protected final SaleView view;

    public SalePresenter(SaleView view) {
        this.view = view;
        this.model = new SaleModel(this);
        model.loadProductsList();
    }

    @Override
    public void loadProductsList(ArrayList<Sale> sales) {

    }

    @Override
    public void loadSale(Sale sale) {

    }

    @Override
    public void addNewSale(Sale newSale) {
    }

    @Override
    public void addNewProductSuccess() {
        model.loadProductsList();
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }
}
