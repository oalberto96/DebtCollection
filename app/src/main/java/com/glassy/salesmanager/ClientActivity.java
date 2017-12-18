package com.glassy.salesmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.glassy.salesmanager.Events.UserEvents;
import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Presenters.ClientPresenter;
import com.glassy.salesmanager.MVP.Views.ClientView;
import com.glassy.salesmanager.UI.ClientAdapter;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity implements ClientView{
    private ClientPresenter presenter;
    private ClientAdapter clientAdapter;
    private RecyclerView clientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        presenter = new ClientPresenter(this);

    }

    public void onClickbtnNewClient(View view){
        startActivityForResult(new Intent(
                getApplicationContext(),
                AddClientActivity.class),
                1
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            Client new_client = data.getParcelableExtra("newClient");
            presenter.addNewClient(new_client);

        }
    }

    @Override
    public void testFailure() {

    }

    @Override
    public void testSuccess() {

    }

    @Override
    public void loadListClient(ArrayList<Client> clients) {
        clientList = (RecyclerView) findViewById(R.id.rv_clients);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        clientList.setLayoutManager(layoutManager);
        clientList.setHasFixedSize(true);
        clientAdapter = new ClientAdapter(clients);
        clientList.setAdapter(clientAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                presenter.deleteClient(id);

            }
        }).attachToRecyclerView(clientList);
    }



    @Override
    public Context getContext() {
        return this;
    }
}
