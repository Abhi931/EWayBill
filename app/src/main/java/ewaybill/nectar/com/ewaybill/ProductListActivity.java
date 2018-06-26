package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.adapter.ProductAdapter;
import ewaybill.nectar.com.ewaybill.adapter.ProductName;
import ewaybill.nectar.com.ewaybill.listener.RecyclerTouchListener;

/**
 * Created by Abhishek on 4/15/2018.
 */


public class ProductListActivity extends Activity implements ProductAdapter.ShowSelectedItem {

    private static final String TAG = ProductListActivity.class.getSimpleName();
    private List<ProductName> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private Button mAddCartButton;
    public ArrayList<ProductName> mAddCartList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        mAddCartButton = (Button) findViewById(R.id.addCartButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new ProductAdapter(this,productList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ProductName productname = productList.get(position);
                Toast.makeText(ProductListActivity.this, productname.getProduct_name() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        prepareMovieData();

        mAddCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddCartList != null && mAddCartList.size() > 0) {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("selectedCartItemsList", mAddCartList);
                    setResult(RESULT_OK,intent);
                    finish();
                } else {
                    Toast.makeText(ProductListActivity.this, "Please select atleast one item", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void prepareMovieData() {
        ProductName product = new ProductName(1, "Apple", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(2, "Apricot", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(3, "Kingston", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(4, "Lenovo", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(5, "Mango", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(6, "Melon", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(7, "Sugar Apple", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(8, "Xiami Note", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(9, "Philips", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(10, "Iron", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(11, "Vivo", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(12, "Banana", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(13, "Table", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(14, "Vegitables", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        product = new ProductName(15, "Sweet Corn", "jhjmb", R.mipmap.ic_launcher);
        ;
        productList.add(product);

        product = new ProductName(16, "Capsicum", "jhjmb", R.mipmap.ic_launcher);
        productList.add(product);

        mAdapter.notifyDataSetChanged();
    }

/*    @Override
    public void showSelectedItem(ArrayList<ProductName> productNameArrayList) {
        if(productNameArrayList != null && productNameArrayList.size() >= 0) {
            mAddCartList = productNameArrayList;

            if(productNameArrayList.size() == 0){
                mAddCartButton.setText("Add to cart");
            }else {
                mAddCartButton.setText("Add to cart (" + productNameArrayList.size() + ")");
            }
            mAddCartButton.setVisibility(View.VISIBLE);
        }
    }*/

    @Override
    public void showSelectedItem(ArrayList<ProductName> productNameArrayList) {
        if (productNameArrayList != null && productNameArrayList.size() >= 0) {
            mAddCartList = productNameArrayList;

            if (productNameArrayList.size() == 0) {
                mAddCartButton.setText("Add to cart");
            } else {
                mAddCartButton.setText("Add to cart (" + productNameArrayList.size() + ")");
            }
            mAddCartButton.setVisibility(View.VISIBLE);
        }
    }
}