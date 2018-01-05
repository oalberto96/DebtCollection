package com.glassy.salesmanager.Product;

import android.content.Context;

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

    @Override
    public void loadProductSuccess(Product product) {
        view.loadProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        model.updateProduct(product);
    }

    @Override
    public void updateProductSuccess() {
        view.updateProductSuccess();
    }

    public void getProduct(int id) {
        model.getProduct(id);
    }
}
