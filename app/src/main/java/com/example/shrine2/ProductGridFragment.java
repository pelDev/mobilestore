package com.example.shrine2;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductGridFragment extends Fragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.product_grid_fragment, container, false);

        setUpToolbar(mView);

        // set up recycler view
        setUpRecyclerView(mView);

        // set cut corner for api 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mView.findViewById(R.id.product_grid).setBackgroundResource(R.drawable.product_grid_background_shape);
        }

        return mView;
    }

    void setUpRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.product_recycler_view);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,
//                GridLayoutManager.VERTICAL, false));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL,
                false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        StaggeredProductCardRecyclerViewAdapter adapter = new StaggeredProductCardRecyclerViewAdapter(
                ProductEntry.initProductEntryList(getResources()),
                getContext()
        );
//        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
//                ProductEntry.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);

        // add item Decoration here
        int largePadding = getResources().getDimensionPixelSize(R.dimen.product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding,smallPadding));
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(getContext(),
                view.findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator(),
                ResourcesCompat.getDrawable(getResources(), R.drawable.shr_branded_menu, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.shr_close_menu, null)));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            setUpRecyclerView(mView);
        }
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
