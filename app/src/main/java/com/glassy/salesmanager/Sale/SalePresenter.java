package com.glassy.salesmanager.Sale;

import android.content.Context;
import android.util.Log;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public class SalePresenter implements SaleEvents {
    protected final SaleModel model;
    protected final SaleView view;
    protected Sale sale;


    public SalePresenter(SaleView view) {
        this.view = view;
        this.model = new SaleModel(this);
        model.loadSaleList();
        this.sale = new Sale();
    }

    public void addClient(Client client) {
        sale.setClient(client);
    }


    public Sale getSale(){
        return sale;
    }

    public ArrayList<Product> getProducts(){
        return sale.getProducts();
    }

    public void getTotal(ArrayList<Integer> quantity){
        float total = 0;
        int i = 0;
        for (Product aux: sale.getProducts()){
            total += aux.getPrice() * quantity.get(i);
            i++;
        }
        sale.setQuantity(quantity);
        view.printTotal(total);
    }

    public void addProduct(Product product, ArrayList<Integer> quantity) {
        float total = 0;
        int i = 0;
        for (Product aux: sale.getProducts()){
            if(aux.getId() == product.getId()){
                return;
            }
            total += aux.getPrice() * quantity.get(i);
        }
        sale.setQuantity(quantity);
        total += product.getPrice();
        sale.addProduct(product);
        view.productAdded(sale.getProducts());
        view.printTotal(total);
    }

    @Override
    public void loadSalesList(ArrayList<Sale> sales) {
        view.loadSaleList(sales);
    }

    @Override
    public void loadSales() {
        model.loadSaleList();
    }


    @Override
    public void loadSale(Sale sale) {

    }

    @Override
    public void loadProducts() {
        model.loadProducts();
    }

    @Override
    public void loadProductsSucces(ArrayList<Product> products) {
        view.loadProductsSuccess(products);
    }

    @Override
    public void loadClients() {
        model.loadClients();
    }

    @Override
    public void loadClientsSuccess(ArrayList<Client> clients) {
        view.loadClientsSuccess(clients);
    }

    @Override
    public void addNewSale(Sale newSale) {
    }

    @Override
    public void addNewProductSuccess() {
        model.loadSaleList();
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    public void saveSale() {
        if (sale.getProducts() == null){
            view.saveSaleFail("product");
            return;
        }
        else if (sale.getClient() == null){
            view.saveSaleFail("client");
            return;
        }
        model.saveSale(sale);
    }
}
