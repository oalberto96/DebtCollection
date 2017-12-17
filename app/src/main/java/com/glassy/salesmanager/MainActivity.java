package com.glassy.salesmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.glassy.salesmanager.MVP.Models.Seller;
import com.glassy.salesmanager.MVP.Models.User;

public class MainActivity extends AppCompatActivity {
    //private UserPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //presenter = new UserPresenter(this);

    }

    public void testFailure(){
        Toast.makeText(this,"buhh",Toast.LENGTH_LONG).show();
    }

    public void testSuccess(){
        Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
    }
}
