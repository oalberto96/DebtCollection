package com.glassy.salesmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.glassy.salesmanager.UI.ClientAdapter;

public class ClientActivity extends AppCompatActivity {
    private static final int NUM_LIST_ITEMS = 100;
    private ClientAdapter clientAdapter;
    private RecyclerView clientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        clientList = (RecyclerView) findViewById(R.id.rv_clients);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        clientList.setLayoutManager(layoutManager);
        clientList.setHasFixedSize(true);
        clientAdapter = new ClientAdapter(NUM_LIST_ITEMS);
        clientList.setAdapter(clientAdapter);

    }
}
