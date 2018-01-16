package com.glassy.salesmanager.Client;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by glassy on 12/16/17.
 */

public class ClientPresenter implements UserEvents{
    protected final ClientModel model;
    protected final ClientView view;
    protected ArrayList<Client> clients;

    protected ArrayList<Integer> deleteClientList;
    
    public ClientPresenter(ClientView view) {
        deleteClientList = new ArrayList<>();
        clients = new ArrayList<>();
        this.view = view;
        this.model = new ClientModel(this);
        this.view.initRecyclerView(this.clients);
        model.loadClients();
    }

    public void loadClients(){
        model.loadClients();
    }

    public void addItemToList(int id){
        deleteClientList.add(id);
    }

    public void deleteItemFromList(){
        deleteClientList.remove(0);
    }

    public void cleanList() {
        for (Integer id: deleteClientList) {
            deleteClientList.remove(id);
            deleteClient(id);
        }
    }

    @Override
    public void loadClientListSuccess(ArrayList<Client> clients) {
        if(clients.size()>0){
            this.clients.clear();
            this.clients.addAll(clients);
            view.refreshRecyclerView();
        }
    }

    public void deleteClient(int id){
        model.deleteClient(id);
    }

    @Override
    public Context getAppContext() {
        return view.getContext();
    }

    @Override
    public void deleteClientSuccess() {
        model.loadClients();
    }


}
