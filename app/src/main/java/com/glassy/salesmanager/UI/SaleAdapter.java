package com.glassy.salesmanager.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glassy.salesmanager.Sale.Sale;
import com.glassy.salesmanager.R;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ClientViewHolder>{

    private ArrayList<Sale> sales;

    private final ListItemClickListener onClickListener;

    public interface ListItemClickListener{
        void onItemClickListener(int id);
    }

    public SaleAdapter(ArrayList<Sale> sales, ListItemClickListener onClickListener){
        this.sales = sales;
        this.onClickListener = onClickListener;
    }


    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.sale_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ClientViewHolder viewHolder = new ClientViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        Sale sale = sales.get(position);
        holder.bind(
                sale.getName(),
                sale.getClient().getFirst_name() + " " + sale.getClient().getLast_name(),
                sale.getDateSale().toString()
        );
        holder.itemView.setTag(sale.getId());

    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_saleName;
        TextView tv_saleClient;
        TextView tv_saleDate;
        public ClientViewHolder(View itemView) {
            super(itemView);
            tv_saleName = (TextView) itemView.findViewById(R.id.tv_item_sale_name);
            tv_saleClient = (TextView) itemView.findViewById(R.id.tv_item_sale_client);
            tv_saleDate = (TextView) itemView.findViewById(R.id.tv_item_sale_date);

            itemView.setOnClickListener(this);
        }


        void bind(String saleName, String saleClient, String saleDate) {
            this.tv_saleName.setText(saleName);
            this.tv_saleClient.setText(saleClient);
            this.tv_saleDate.setText(saleDate);
        }

        @Override
        public void onClick(View v) {
            int id = (int) v.getTag();
            onClickListener.onItemClickListener(id);
        }
    }

}
