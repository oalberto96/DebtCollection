package com.glassy.salesmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Product;
import com.glassy.salesmanager.MVP.Models.Sale;
import com.glassy.salesmanager.MVP.Presenters.SalePresenter;
import com.glassy.salesmanager.MVP.Views.SaleView;
import com.glassy.salesmanager.UI.ProductAdapter;
import com.glassy.salesmanager.UI.SaleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AddSaleActivity extends AppCompatActivity implements SaleView, ProductAdapter.ListItemClickListener{

    SalePresenter presenter;

    Spinner s_clientList;

    HashMap<Integer, String> s_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);
        presenter = new SalePresenter(this);
        presenter.loadClients();
        presenter.loadProducts();
    }


    @Override
    public void loadSaleList(ArrayList<Sale> sales) {

    }

    @Override
    public void readSale(Sale sale) {

    }

    @Override
    public void loadClientsSuccess(ArrayList<Client> clients) {
        populateClientList(clients);
    }

    @Override
    public void loadProductsSuccess(ArrayList<Product> products) {
        populateProductList(products);
    }

    public void populateProductList(ArrayList<Product> products){
        RecyclerView productList = (RecyclerView) findViewById(R.id.rv_sales_products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        productList.setHasFixedSize(true);
        ProductAdapter productAdapter = new ProductAdapter(products, this);
        productList.setAdapter(productAdapter);
    }


    public void populateClientList(ArrayList<Client> clients){
        ArrayList<String> s_clients = new ArrayList<String>();
        s_map = new HashMap<Integer, String>();
        int i=0;
        for (Client client: clients){
            s_map.put(client.getId(),client.getFirst_name());
            s_clients.add(client.getFirst_name());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s_clients);
        s_clientList = (Spinner)findViewById(R.id.s_client_list);
        s_clientList.setAdapter(adapter);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClickListener(int id) {

    }
}
