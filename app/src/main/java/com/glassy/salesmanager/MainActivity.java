package com.glassy.salesmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Seller;
import com.glassy.salesmanager.MVP.Models.User;

public class MainActivity extends AppCompatActivity {
    //private UserPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickbtnClient(View view){
        startActivity(new Intent(
                this,
                ClientActivity.class
        ));
    }

    public void onClickbtnProduct(View view){

    }

    public void onClickbtnSale(View view){

    }


}
