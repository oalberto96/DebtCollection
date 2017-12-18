package com.glassy.salesmanager;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.glassy.salesmanager.MVP.Models.Client;

public class AddClientActivity extends AppCompatActivity {

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

        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etAddress = (EditText) findViewById(R.id.et_address);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        etTIN = (EditText) findViewById(R.id.et_tin);
        etNotes = (EditText) findViewById(R.id.et_notes);

    }

    public void onClickbtnSaveClient(View view){
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
}
