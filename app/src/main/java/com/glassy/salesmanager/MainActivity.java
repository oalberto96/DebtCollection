package com.glassy.salesmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.glassy.salesmanager.MVP.Models.Seller;
import com.glassy.salesmanager.MVP.Models.User;
import com.glassy.salesmanager.MVP.Presenters.UserPresenter;
import com.glassy.salesmanager.MVP.Views.UserView;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements UserView {
    private UserPresenter presenter;
    private EventBus bus = new EventBus();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new UserPresenter(this);

    }

    public void testFailure(){
        Toast.makeText(this,"buhh",Toast.LENGTH_LONG).show();
    }

    public void testSuccess(){
        Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
    }
}
