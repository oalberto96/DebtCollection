package com.glassy.salesmanager.Client;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;


import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public interface ClientView {
    void initRecyclerView(ArrayList<Client> clients);
    Context getContext();
    void refreshRecyclerView();
}
