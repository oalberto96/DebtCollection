package com.glassy.salesmanager.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.R;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ClientViewHolder>{

    private ArrayList<Product> products;

    private final ListItemClickListener onClickListener;

    public interface ListItemClickListener{
        void onItemClickListener(int id);
    }

    public ProductAdapter(ArrayList<Product> products, ListItemClickListener onClickListener){
        this.products = products;
        this.onClickListener = onClickListener;
    }


    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.product_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ClientViewHolder viewHolder = new ClientViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(
                product.getName(),
                String.valueOf(product.getPrice())
        );
        holder.itemView.setTag(product.getId());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_productName;
        TextView tv_productPrice;

        public ClientViewHolder(View itemView) {
            super(itemView);
            tv_productName = (TextView) itemView.findViewById(R.id.tv_item_product_name);
            tv_productPrice = (TextView) itemView.findViewById(R.id.tv_item_product_price);
            itemView.setOnClickListener(this);
        }

        void bind(String productName, String productPrice) {
            this.tv_productName.setText(productName);
            this.tv_productPrice.setText("$ " + productPrice);
        }

        @Override
        public void onClick(View v) {
            int id = (int) v.getTag();
            onClickListener.onItemClickListener(id);
        }
    }

}
