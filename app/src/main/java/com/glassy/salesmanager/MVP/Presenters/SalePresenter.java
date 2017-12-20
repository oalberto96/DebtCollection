package com.glassy.salesmanager.MVP.Presenters;

import android.content.Context;

import com.glassy.salesmanager.Events.ProductEvents;
import com.glassy.salesmanager.MVP.Models.Product;
import com.glassy.salesmanager.MVP.Models.ProductModel;
import com.glassy.salesmanager.MVP.Views.ProductView;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public class SalePresenter implements ProductEvents {
    protected final ProductModel model;
    protected final ProductView view;

    public SalePresenter(ProductView view) {
        this.view = view;
        this.model = new ProductModel(this);
        model.loadProductsList();
    }

    @Override
    public void loadProductsList(ArrayList<Product> products) {
        view.loadProductsList(products);
    }

    @Override
    public void addNewProduct(Product newProduct) {
        model.createProduct(newProduct);
    }

    public void deleteClient(int id){
        model.deleteClient(id);
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
