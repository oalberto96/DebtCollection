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
    protected int idToRemove;


    public SalePresenter(SaleView view) {
        this.view = view;
        this.model = new SaleModel(this);
        model.loadSaleList();
        this.sale = new Sale();
        idToRemove = -1;
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

    public void getTotal(){
        float total = 0;
        int i = 0;
        for (Product aux: sale.getProducts()){
            total += aux.getPrice() * sale.getProduct_quantity().get(i);
            i++;
        }
        view.printTotal(total);
    }

    public void addProduct(Product product) {
        for (Product aux: sale.getProducts()){
            if(aux.getId() == product.getId()){
                return;
            }
        }
        sale.getProduct_quantity().add(1);
        sale.addProduct(product);
        view.productAdded(sale.getProducts());
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
    public void saveSaleSuccess() {
        view.saveSaleSuccess();
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

    @Override
    public void deleteSaleSuccess() {
        view.deleteSaleSucces();
    }

    @Override
    public void retrieveSale(int saleId) {
        model.retrieveSale(saleId);
    }

    @Override
    public void loadSaleSuccess(Sale sale) {
        this.sale = sale;
        view.loadSaleSuccess();
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

    public void deleteSale(int id) {
        model.deleteSale(id);
    }

    public void addItemToList(int id) {
        idToRemove = id;
    }

    public void deleteItemFromList() {
        idToRemove = -1;
    }

    public void cleanList() {
        if (idToRemove >= 0){
            sale.getProducts().remove(idToRemove);
            sale.getProduct_quantity().remove(idToRemove);
            idToRemove = 0;
            view.refreshProductList();
        }
    }
}
