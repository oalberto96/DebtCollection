package com.glassy.salesmanager.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.R;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder>{

    private ArrayList<Client> clients;

    public ClientAdapter(ArrayList<Client> clients){
        this.clients = clients;
    }


    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.client_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ClientViewHolder viewHolder = new ClientViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.bind(
                client.getFirst_name(),
                client.getLast_name()
        );

    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder{
        TextView tv_firstName;
        public ClientViewHolder(View itemView) {
            super(itemView);
            tv_firstName = (TextView) itemView.findViewById(R.id.tv_item_first_name);
        }

        void bind(String tv_firstName, String tv_lastName) {
            this.tv_firstName.setText(tv_firstName + " " + tv_lastName);
        }
    }

}
