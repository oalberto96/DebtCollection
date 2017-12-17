package com.glassy.salesmanager.Events;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Seller;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public interface UserEvents {
    public void SuccesEvent();
    public void FailEvent();
    public void loadListSeller(ArrayList<Client> clients);
}
