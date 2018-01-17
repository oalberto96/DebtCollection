package com.glassy.salesmanager.Product;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public class ProductPresenter implements ProductEvents {

    protected final ProductModel model;
    protected final ProductView view;
    protected ArrayList<Product> products;

    protected int idToDelete;

    public ProductPresenter(ProductView view) {
        products = new ArrayList<>();
        this.view = view;
        this.model = new ProductModel(this);
        this.view.initRecyclerView(this.products);
        model.loadProducts();
        idToDelete = -1;
    }

    public void loadProducts(){
        model.loadProducts();
    }

    @Override
    public void addItemToList(int id) {
        idToDelete = id;
    }

    @Override
    public void deleteItemFromList() {
        idToDelete = -1;
    }

    @Override
    public void loadProductList() {
        model.loadProducts();
    }

    @Override
    public void cleanList() {
        if (idToDelete >= 0){
            deleteProduct(idToDelete);
            deleteItemFromList();
        }
    }

    @Override
    public void loadProductsListSuccess(ArrayList<Product> products) {
        if(products.size()>0){
            this.products.clear();
            this.products.addAll(products);
            view.refreshRecyclerView();
        }
    }

    public void deleteProduct(int id){
        model.deleteProduct(id);
    }

    @Override
    public void deleteProductSuccess() {
        model.loadProducts();
    }

    @Override
    public Context getAppContext() {
        return view.getAppContext();
    }







}
