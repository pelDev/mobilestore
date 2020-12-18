package com.example.shrine2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StaggeredProductCardRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredProductCardViewHolder> {

    private List<ProductEntry> productList;
    private Context mContext;

    public StaggeredProductCardRecyclerViewAdapter(List<ProductEntry> productList, Context context) {
        this.productList = productList;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @NonNull
    @Override
    public StaggeredProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.shr_staggered_product_card_first;
        if (viewType == 1) {
            layoutId = R.layout.shr_staggered_product_card_second;
        } else if (viewType == 2) {
            layoutId = R.layout.shr_staggered_product_card_third;
        }

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new StaggeredProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);

            Glide.with(mContext).load(product.url).into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
