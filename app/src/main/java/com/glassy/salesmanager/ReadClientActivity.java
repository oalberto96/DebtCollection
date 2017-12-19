package com.glassy.salesmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Presenters.ClientPresenter;
import com.glassy.salesmanager.MVP.Views.ClientView;

import java.util.ArrayList;

public class ReadClientActivity extends AppCompatActivity implements ClientView {
    private ClientPresenter presenter;
    TextView tvFirstName;
    TextView tvLastName;
    TextView tvAddress;
    TextView tvPhoneNumber;
    TextView tvTIN;
    TextView tvNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_client);
        presenter = new ClientPresenter(this);

        tvFirstName = (TextView) findViewById(R.id.tv_first_name);
        tvLastName = (TextView) findViewById(R.id.tv_last_name);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_phone_number);
        tvTIN = (TextView) findViewById(R.id.tv_tin);
        tvNotes = (TextView) findViewById(R.id.tv_notes);

        getClientData();
    }

    public void getClientData(){
        Intent intent = getIntent();
        int clientId = intent.getIntExtra("clientId",0);
        presenter.readClient(clientId);
    }

    @Override
    public void loadListClient(ArrayList<Client> clients) {

    }

    @Override
    public void readClient(Client client) {
        fillFormViews(client);
    }

    private void fillFormViews(Client client) {
        tvFirstName.setText(client.getFirst_name());
        tvLastName.setText(client.getLast_name());
        tvAddress.setText(client.getAddress());
        tvPhoneNumber.setText(client.getPhoneNumber());
        tvNotes.setText(client.getNotes());
        tvTIN.setText(client.getTin());
    }

    @Override
    public Context getContext() {
        return this;
    }
}
