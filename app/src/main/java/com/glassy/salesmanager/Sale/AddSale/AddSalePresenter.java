package com.glassy.salesmanager.Sale.AddSale;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.Sale.Sale;

import java.util.ArrayList;

/**
 * Created by glassy on 1/18/18.
 */

class AddSalePresenter implements IAddSalePresenter {

    private IAddSaleView view;
    private AddSaleModel model;
    Sale sale;

    public AddSalePresenter(IAddSaleView view) {
        this.view = view;
        model = new AddSaleModel(this);
        sale = new Sale();
    }

    @Override
    public Context getActivityContext() {
        return view.getActivityContext();
    }

    @Override
    public void getProducts(){
        model.getProducts();
    }

    @Override
    public void getClients(){
        model.getClients();
    }

    @Override
    public void loadProductsSuccess(ArrayList<Product> products) {
        view.populateProductList(products);
    }

    @Override
    public void loadClientsSuccess(ArrayList<Client> clients) {
        view.populateClientList(clients);
    }

    @Override
    public void loadClientSuccess(Client client) {

    }

    @Override
    public void loadProductSuccess(Product product) {

    }

    @Override
    public Sale getSale(){
        return sale;
    }

    @Override
    public void addProduct(Product product) {
        for (Product aux: sale.getProducts()){
            if(aux.getId() == product.getId()){
                return;
            }
        }
        sale.getProduct_quantity().add(1);
        sale.addProduct(product);
        view.productAddedToSale();
    }

    @Override
    public void getSaleTotal() {
        float total = 0;
        int i = 0;
        for (Product aux: sale.getProducts()){
            total += aux.getPrice() * sale.getProduct_quantity().get(i);
            i++;
        }
        view.printTotal(total);
    }

    @Override
    public void saveSale() {
        model.insertSale(sale);
    }

    @Override
    public void saveSaleSuccess() {
        view.saveSaleSuccess();
    }
}
