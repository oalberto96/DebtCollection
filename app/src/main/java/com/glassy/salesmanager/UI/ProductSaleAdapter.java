package com.glassy.salesmanager.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.R;

import java.util.ArrayList;

/**
 * Created by glassy on 12/16/17.
 */

public class ProductSaleAdapter extends RecyclerView.Adapter<ProductSaleAdapter.ClientViewHolder> {

    protected ArrayList<Product> products;
    protected ArrayList<Integer> quantity;
    public boolean lock;

    private final ListItemClickListener onClickListener;
    private final TotalListener onTextChangedListener;

    public interface ListItemClickListener {
        void onItemClickListener(int id);
    }

    public interface TotalListener{
        void onViewTextChangedListener();
    }

    public ProductSaleAdapter(ArrayList<Product> products, ArrayList<Integer> quantity, ListItemClickListener onClickListener, TotalListener onTextChangedListener) {
        this.products = products;
        this.quantity = quantity;
        this.onClickListener = onClickListener;
        this.onTextChangedListener = onTextChangedListener;
        lock = false;
    }


    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.product_sale_list_item;
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
                String.valueOf(product.getPrice()),
                String.valueOf(quantity.get(position)),
                position
        );
        holder.itemView.setTag(product.getId());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_productName;
        TextView tv_productPrice;
        EditText et_quantity;

        public ClientViewHolder(View itemView) {
            super(itemView);
            tv_productName = (TextView) itemView.findViewById(R.id.tv_item_product_name);
            tv_productPrice = (TextView) itemView.findViewById(R.id.tv_item_product_price);
            et_quantity = (EditText) itemView.findViewById(R.id.et_quantity);
            itemView.setOnClickListener(this);
        }

        void bind(String productName, String productPrice, final String quantityText, final int position) {
            this.tv_productName.setText(productName);
            this.tv_productPrice.setText("$ " + productPrice);
            this.et_quantity.setText(quantityText);
            this.et_quantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!lock) {
                        try {
                            quantity.set(position, Integer.parseInt(et_quantity.getText().toString()));
                            onTextChangedListener.onViewTextChangedListener();
                        } catch (Exception e) {
                            Log.e("afterTextChange: ", "Error at parse text to int");
                        }

                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            int id = (int) v.getTag();
            onClickListener.onItemClickListener(id);
        }
    }

}
