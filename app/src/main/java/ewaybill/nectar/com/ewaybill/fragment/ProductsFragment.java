package ewaybill.nectar.com.ewaybill.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.adapter.ProductAdapter;
import ewaybill.nectar.com.ewaybill.adapter.ProductName;
import ewaybill.nectar.com.ewaybill.listener.RecyclerTouchListener;

public class ProductsFragment extends Fragment {

    private List<ProductName> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private static final String TAG = ProductsFragment.class.getSimpleName();
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.test, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

       // mAdapter = new ProductAdapter(this,productList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

         recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ProductName productname = productList.get(position);
              //  Toast.makeText(getActivity(), productname.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        prepareMovieData();

        return rootView;
    }


    private void prepareMovieData() {
      /*  ProductName product = new ProductName("Apple");
        productList.add(product);

        product = new ProductName("Apricot");
        productList.add(product);

        product = new ProductName("Kingston");
        productList.add(product);

        product = new ProductName("Lenovo");
        productList.add(product);

        product = new ProductName("Mango");
        productList.add(product);

        product = new ProductName("Melon");
        productList.add(product);

        product = new ProductName("Sugar Apple");
        productList.add(product);

        product = new ProductName("Xiami Note");
        productList.add(product);

        product = new ProductName("Philips");
        productList.add(product);

        product = new ProductName("Iron");
        productList.add(product);

        product = new ProductName("Vivo");
        productList.add(product);

        product = new ProductName("Banana");
        productList.add(product);

        product = new ProductName("Table");
        productList.add(product);

        product = new ProductName("Vegitables");
        productList.add(product);

        product = new ProductName("Sweet Corn");
        productList.add(product);

        product = new ProductName("Capsicum");
        productList.add(product);

        mAdapter.notifyDataSetChanged();*/
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}