package com.glassy.salesmanager.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.glassy.salesmanager.R;

public class EditClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
    }
/*
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
                    this.client = client;
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
    */
}
