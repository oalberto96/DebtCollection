package com.glassy.salesmanager.Client;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.glassy.salesmanager.R;

import java.util.ArrayList;

public class ReadClientActivity extends AppCompatActivity implements ClientView {
    private ClientPresenter presenter;
    TextView tvFirstName;
    TextView tvAddress;
    TextView tvPhoneNumber;
    TextView tvTIN;
    TextView tvNotes;
    CardView cvClientName;
    CardView cvClientAddress;
    CardView cvClientPhoneN;
    CardView cvClientTin;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_client);
        presenter = new ClientPresenter(this);

        tvFirstName = (TextView) findViewById(R.id.tv_first_name);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_phone_number);
        tvTIN = (TextView) findViewById(R.id.tv_tin);
        tvNotes = (TextView) findViewById(R.id.tv_notes);

        cvClientName = (CardView) findViewById(R.id.cv_client_name);

        getClientData();
    }

    public void getClientData(){
        Intent intent = getIntent();
        int clientId = intent.getIntExtra("clientId",0);
        presenter.readClient(clientId);
    }

    public void onClickbtnUpdateClient(View view){
        Intent intent = new Intent(
                getApplicationContext(),
                AddClientActivity.class);
        intent.putExtra("mode","UPDATE");
        intent.putExtra("client", (Parcelable) client);
        startActivityForResult(intent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 3 && resultCode == RESULT_OK){
            Client new_client = data.getParcelableExtra("newClient");
            presenter.updateClient(new_client);
        }
    }

    @Override
    public void loadListClient(ArrayList<Client> clients) {

    }

    @Override
    public void readClient(Client client) {
        fillFormViews(client);
    }

    private void fillFormViews(Client client) {
        this.client = client;
        tvFirstName.setText(client.getFirst_name() + " " + client.getLast_name() );
        /*if (client.getAddress().isEmpty()){
            cvClientAddress.setVisibility(View.GONE);
        } else {
        }*/
        tvAddress.setText(client.getAddress());

            tvPhoneNumber.setText(client.getPhoneNumber());

            tvTIN.setText(client.getTin());
        tvNotes.setText(client.getNotes());
    }

    @Override
    public Context getContext() {
        return this;
    }
}
