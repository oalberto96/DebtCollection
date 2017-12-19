package com.glassy.salesmanager;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.glassy.salesmanager.MVP.Models.Client;

public class AddClientActivity extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    EditText etAddress;
    EditText etPhoneNumber;
    EditText etTIN;
    EditText etNotes;
    Button btnSaveClient;
    Button btnAddClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etAddress = (EditText) findViewById(R.id.et_address);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        etTIN = (EditText) findViewById(R.id.et_tin);
        etNotes = (EditText) findViewById(R.id.et_notes);
        btnAddClient = (Button) findViewById(R.id.btn_add_client);
        btnSaveClient = (Button) findViewById(R.id.btn_save_client);

        getExtraMessages();
    }

    private void getExtraMessages(){
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        if (mode != null){
            switch (mode){
                case "CREATE":
                    btnSaveClient.setVisibility(View.INVISIBLE);
                    break;
                case "UPDATE":
                    btnAddClient.setVisibility(View.INVISIBLE);
                    Client client = intent.getParcelableExtra("client");
                    fillFormViews(client);
            }
        }
    }

    private void fillFormViews(Client client) {
        etFirstName.setText(client.getFirst_name());
        etLastName.setText(client.getLast_name());
        etAddress.setText(client.getAddress());
        etPhoneNumber.setText(client.getPhoneNumber());
        etNotes.setText(client.getNotes());
        etTIN.setText(client.getTin());
    }

    public void onClickbtnAddClient(View view){
        Client newClient = new Client(
                etFirstName.getText().toString(),
                etLastName.getText().toString(),
                etAddress.getText().toString(),
                etPhoneNumber.getText().toString(),
                etNotes.getText().toString(),
                etTIN.getText().toString()
        );
        Intent intent = new Intent();
        intent.putExtra("newClient",(Parcelable) newClient);
        setResult(RESULT_OK,intent);
        onBackPressed();
    }

    public void onClickbtnSaveClient(View view){

    }
}
