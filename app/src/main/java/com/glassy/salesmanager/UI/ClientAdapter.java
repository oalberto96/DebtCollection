package com.glassy.salesmanager.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glassy.salesmanager.R;

/**
 * Created by glassy on 12/16/17.
 */

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder>{

    private int m_number_items;

    public ClientAdapter(int number_items){
        m_number_items = number_items;

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
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return m_number_items;
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder{
        TextView item;
        public ClientViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.tv_item_first_name);
        }

        void bind(int listIndex) {
            item.setText(String.valueOf(listIndex));
        }
    }

}
