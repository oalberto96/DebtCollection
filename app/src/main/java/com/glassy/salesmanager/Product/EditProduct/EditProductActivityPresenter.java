package com.glassy.salesmanager.Product.EditProduct;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

/**
 * Created by glassy on 1/16/18.
 */

public class EditProductActivityPresenter implements IEditProductPresenter {
    private final IEditProductView view;
    private final EditProductActivityModel model;
    public Product product;

    public EditProductActivityPresenter(IEditProductView view){
        this.view = view;
        this.model = new EditProductActivityModel(this);
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
        this.product = product;
        view.fillForm(product);
    }

    @Override
    public void updateProduct() {
        model.updateProduct(this.product);
    }

    @Override
    public void updateProductSuccess() {
        view.updateProductSuccess();
    }

}
