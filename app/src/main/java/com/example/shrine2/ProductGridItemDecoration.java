package com.example.shrine2;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductGridItemDecoration extends RecyclerView.ItemDecoration {

    private int largePadding;
    private int smallPadding;

    public ProductGridItemDecoration(int largePadding, int smallPadding) {
        this.largePadding = largePadding;
        this.smallPadding = smallPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = smallPadding;
        outRect.right = largePadding;
    }

}
