package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.adapter.EWayProductAdapter;
import ewaybill.nectar.com.ewaybill.adapter.ProductAdapter;
import ewaybill.nectar.com.ewaybill.adapter.ProductName;
import ewaybill.nectar.com.ewaybill.model.CartItem;
import ewaybill.nectar.com.ewaybill.model.Product;

public class AddCardListActivity extends Activity {

    private static final String TAG = AddCardListActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private EWayProductAdapter mAdapter;
    private ArrayList<CartItem> mAddCartList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_layout);

        mAddCartList = getIntent().getParcelableArrayListExtra("LIST");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new EWayProductAdapter(AddCardListActivity.this,mAddCartList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       // recyclerView.setAdapter(mAdapter);
    }
}
