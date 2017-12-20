package com.glassy.salesmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Sale;
import com.glassy.salesmanager.MVP.Presenters.SalePresenter;
import com.glassy.salesmanager.MVP.Views.SaleView;

import java.util.ArrayList;
import java.util.HashMap;

public class AddSaleActivity extends AppCompatActivity implements SaleView{

    SalePresenter presenter;

    Spinner s_clientList;

    HashMap<Integer, String> s_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);
        presenter = new SalePresenter(this);
        presenter.loadClients();
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
}
