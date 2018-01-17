package com.glassy.salesmanager.Product.AddProduct;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

/**
 * Created by glassy on 1/16/18.
 */

public class AddProductActivityPresenter implements IAddProductPresenter {
    private final IAddProductView view;
    private final AddProductActivityModel model;

    public AddProductActivityPresenter(IAddProductView view){
        this.view = view;
        this.model = new AddProductActivityModel(this);
    }

    @Override
    public Context getActivityContext() {
        return view.getActivityContext();
    }

    @Override
    public void insertProduct(Product product) {
        model.insertProduct(product);
    }

    @Override
    public void insertProductSuccess() {
        view.finishActivitySuccess();
    }

}
