package com.glassy.salesmanager.Client;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.glassy.salesmanager.R;

public class EditClientActivity extends AppCompatActivity implements IEditclientAcitivtyView {

    EditClientActivityPresenter presenter;

    EditText etFirstName;
    EditText etLastName;
    EditText etAddress;
    EditText etPhoneNumber;
    EditText etTIN;
    EditText etNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        Toolbar actionbar = (Toolbar) findViewById(R.id.my_action_bar);
        setSupportActionBar(actionbar);

        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new EditClientActivityPresenter(this);

        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etAddress = (EditText) findViewById(R.id.et_address);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        etTIN = (EditText) findViewById(R.id.et_tin);
        etNotes = (EditText) findViewById(R.id.et_notes);

        getExtraMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.done_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_menu_done:
                onClickbtnUpdateClient();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getExtraMessages(){
        Intent intent = getIntent();
        int idClient = intent.getIntExtra("clientId",0);
        presenter.getClient(idClient);
    }

    public void onClickbtnUpdateClient(){
        presenter.client.setFirstName(etFirstName.getText().toString());
        presenter.client.setLastName(etLastName.getText().toString());
        presenter.client.setAddress(etAddress.getText().toString());
        presenter.client.setPhoneNumber(etPhoneNumber.getText().toString());
        presenter.client.setNotes(etNotes.getText().toString());
        presenter.client.setTIN(etTIN.getText().toString());
        presenter.updateClient();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void finishActivitySuccess() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        onBackPressed();
    }

    @Override
    public void fillForm(Client client) {
        etFirstName.setText(client.getFirst_name());
        etLastName.setText(client.getLast_name());
        etAddress.setText(client.getAddress());
        etPhoneNumber.setText(client.getPhoneNumber());
        etNotes.setText(client.getNotes());
        etTIN.setText(client.getTin());
    }

    @Override
    public void getClientFailure() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        onBackPressed();
    }
}
