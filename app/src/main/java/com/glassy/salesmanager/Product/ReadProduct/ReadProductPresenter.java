package com.glassy.salesmanager.Product.ReadProduct;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

/**
 * Created by glassy on 1/16/18.
 */

class ReadProductPresenter implements IReadProductPresenter{

    private final IReadProductView view;
    private final ReadProductModel model;

    public ReadProductPresenter(IReadProductView view){
        this.view = view;
        this.model = new ReadProductModel(this);
    }

    @Override
    public Context getActivityContext() {
        return view.getActivityContext();
    }

    @Override
    public void getProduct(int productId) {
        model.getProduct(productId);
    }

    @Override
    public void getProductSuccess(Product product) {
        view.fillForm(product);
    }
}
