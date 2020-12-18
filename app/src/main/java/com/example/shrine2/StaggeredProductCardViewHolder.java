package com.example.shrine2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StaggeredProductCardViewHolder extends RecyclerView.ViewHolder {

    public ImageView productImage;
    public TextView productTitle;
    public TextView productPrice;

    StaggeredProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        productPrice = itemView.findViewById(R.id.product_description);
    }

}
