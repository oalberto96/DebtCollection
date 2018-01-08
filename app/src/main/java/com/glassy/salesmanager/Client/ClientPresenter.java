package com.glassy.salesmanager.Client;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public class ClientPresenter implements UserEvents{
    protected final ClientModel model;
    protected final ClientView view;

    protected ArrayList<Integer> deleteClientList;
    
    public ClientPresenter(ClientView view) {
        deleteClientList = new ArrayList<Integer>();
        this.view = view;
        this.model = new ClientModel(this);
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
    public void addClientEvent() {
        model.loadClients();
    }

    @Override
    public void FailEvent() {
    }

    @Override
    public void loadListSeller(ArrayList<Client> clients) {
        if(clients.size()>0){
            view.loadListClient(clients);
        }
    }

    public void updateClient(Client client){
        model.updateClient(client);
    }

    
    
    public void deleteClient(int id){
        model.deleteClient(id);
    }

    public void readClient(int id){ model.readClient(id);}

    @Override
    public void addNewClient(Client newClient) {
        model.createClient(newClient);
    }

    @Override
    public Context getAppContext() {
        return view.getContext();
    }

    @Override
    public void readClientSuccessEvent(Client client) {
        view.readClient(client);
    }

    @Override
    public void updateClientSuccessEvent(int id) {
        model.readClient(id);
    }



}
