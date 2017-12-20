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

public class ProductPresenter implements ProductEvents {
    protected final ProductModel model;
    protected final ProductView view;

    public ProductPresenter(ProductView view) {
        this.view = view;
        this.model = new ProductModel(this);

    }

    @Override
    public void loadProductsList(ArrayList<Product> products) {

    }

    @Override
    public void addNewProduct(Product newProduct) {
        model.createProduct(newProduct);
    }

    @Override
    public void addNewProductSuccess() {

    }

    @Override
    public Context getContext() {
        return view.getContext();
    }
}
