package com.glassy.salesmanager.Client.ReadClient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.glassy.salesmanager.Client.Client;

import com.glassy.salesmanager.Client.EditClient.EditClientActivity;
import com.glassy.salesmanager.R;


public class ReadClientActivity extends AppCompatActivity implements IReadClientActivityView {
    private ReadClientActivityPresenter presenter;

    TextView tvFirstName;
    TextView tvAddress;
    TextView tvPhoneNumber;
    TextView tvTIN;
    TextView tvNotes;
    CardView cvClientName;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_client);

        Toolbar actionbar = (Toolbar) findViewById(R.id.my_action_bar);
        setSupportActionBar(actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new ReadClientActivityPresenter(this);
        tvFirstName = (TextView) findViewById(R.id.tv_first_name);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_phone_number);
        tvTIN = (TextView) findViewById(R.id.tv_tin);
        tvNotes = (TextView) findViewById(R.id.tv_notes);
        cvClientName = (CardView) findViewById(R.id.cv_client_name);

        getClientData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_menu_edit:
                onClickbtnUpdateClient();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void getClientData(){
        Intent intent = getIntent();
        int clientId = intent.getIntExtra("clientId",0);
        presenter.getClient(clientId);
    }

    public void onClickbtnUpdateClient(){
        Intent intent = new Intent(
                getApplicationContext(),
                EditClientActivity.class);
        intent.putExtra("clientId", client.getId());
        startActivityForResult(intent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 3 && resultCode == RESULT_OK){
            getClientData();
       }
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void finishActivitySuccess() {

    }

    @Override
    public void getClientFailure() {

    }

    @Override
    public void fillForm(Client client) {
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
}
