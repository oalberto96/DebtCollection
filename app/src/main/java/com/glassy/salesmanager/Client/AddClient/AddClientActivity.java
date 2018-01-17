package com.glassy.salesmanager.Client.AddClient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.R;

public class AddClientActivity extends AppCompatActivity implements IAddClientActivityView {

    AddClientActivityPresenter presenter;

    EditText etFirstName;
    EditText etLastName;
    EditText etAddress;
    EditText etPhoneNumber;
    EditText etTIN;
    EditText etNotes;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.done_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        Toolbar actionbar = (Toolbar) findViewById(R.id.my_action_bar);
        setSupportActionBar(actionbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new AddClientActivityPresenter(this);

        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etAddress = (EditText) findViewById(R.id.et_address);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        etTIN = (EditText) findViewById(R.id.et_tin);
        etNotes = (EditText) findViewById(R.id.et_notes);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_menu_done:
                onClickbtnAddClient();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickbtnAddClient(){
        Client newClient = new Client(
                etFirstName.getText().toString(),
                etLastName.getText().toString(),
                etAddress.getText().toString(),
                etPhoneNumber.getText().toString(),
                etNotes.getText().toString(),
                etTIN.getText().toString()
        );
        presenter.insertClient(newClient);
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
}
