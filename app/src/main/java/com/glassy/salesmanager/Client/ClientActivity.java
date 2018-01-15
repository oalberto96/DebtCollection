package com.glassy.salesmanager.Client;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.glassy.salesmanager.R;
import com.glassy.salesmanager.UI.ClientAdapter;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity implements ClientView, ClientAdapter.ListItemClickListener{
    private ClientPresenter presenter;
    private ClientAdapter clientAdapter;
    private RecyclerView clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Toolbar actionbar = (Toolbar) findViewById(R.id.my_action_bar);
        setSupportActionBar(actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new ClientPresenter(this);

    }

    public void onClickbtnNewClient(View view){
        Intent intent = new Intent(
                getApplicationContext(),
                AddClientActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            presenter.loadClients();
        }
    }


    @Override
    public void loadListClient(ArrayList<Client> clients) {
        clientList = (RecyclerView) findViewById(R.id.rv_clients);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        clientList.setLayoutManager(layoutManager);
        clientList.setHasFixedSize(true);
        clientAdapter = new ClientAdapter(clients, this);
        clientList.setAdapter(clientAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                showDeleteSnackbar(id);
            }
        }).attachToRecyclerView(clientList);
    }

    public void showDeleteSnackbar(final int id){
        presenter.addItemToList(id);
        Snackbar.make(findViewById(R.id.client_activity_layout),
                R.string.client_deleted,
                Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadClients();
                        presenter.deleteItemFromList();
                    }
                }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                presenter.cleanList();
            }
        }).show();
    }
    @Override
    public void readClient(Client client) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClickListener(int id) {
        Intent intent = new Intent(
                this,
                ReadClientActivity.class);
        intent.putExtra("clientId",id);
        startActivity(intent);
    }
}
