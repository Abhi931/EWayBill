package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.adapter.CartItemAdapter;
import ewaybill.nectar.com.ewaybill.adapter.EWayProductAdapter;
import ewaybill.nectar.com.ewaybill.adapter.ProductName;
import ewaybill.nectar.com.ewaybill.model.CartItem;
import ewaybill.nectar.com.ewaybill.model.Order;
import ewaybill.nectar.com.ewaybill.model.Product;
import ewaybill.nectar.com.ewaybill.model.User;
import ewaybill.nectar.com.ewaybill.sql.DatabaseHelper;

import static ewaybill.nectar.com.ewaybill.R.id.etTransporterPlace;
import static ewaybill.nectar.com.ewaybill.R.id.tvSupplierGstinNo;
import static ewaybill.nectar.com.ewaybill.R.id.tvSupplierName;

/**
 * Created by Abhishek on 4/17/2018.
 */
public class GenerateEWayBillActivity extends Activity implements View.OnClickListener {

    private EditText etSupplierUserName;
    private Button btnGetSupplier;
    private TextView tvSupplierName;
    private TextView tvSupplierGstinNo;
    private EditText etClientUserName;
    private Button btnGetClient;
    private TextView tvClientUserName;
    private TextView tvClientGstinNo;
    private EditText etTransporterUserName;
    private Button btnGetTransporter;
    private TextView tvTransporterUserName;
    private TextView tvTransporterGstinNo;
    private SQLiteDatabase db;
    private static final String TABLE_CLIENT = "client_details";
    private static final String COLUMN_USER_CLIENT_ID = "client_id";
    private static final String COLUMN_USER_GSTIN_NO = "gstin_no";
    private static final String COLUMN_USER_EMAILID = "email_id";
    private static final String COLUMN_USER_NAME_CLIENT = "client_name";
    private static final String COLUMN_USER_MOBILENO = "mobile_no";
    private static final String COLUMN_USER_PINCODE = "client_pincode";
    private static final String COLUMN_USER_STATE = "client_state";
    private static final String COLUMN_USER_PLACE = "client_place";
    private static final String TABLE_SUPPLIER = "supplier_details";
    private static final String COLUMN_SUPPIER_ID = "supplier_id";
    private static final String COLUMN_SUPPIER_GSTIN_NO = "supplier_gstin_no";
    private static final String COLUMN_SUPPIER_EMAILID = "supplier_email_id";
    private static final String COLUMN_SUPPIER_NAME_CLIENT = "supplier_name";
    private static final String COLUMN_SUPPIER_MOBILENO = "supplier_mobile_no";
    private static final String COLUMN_SUPPIER_PINCODE = "supplier_pincode";
    private static final String COLUMN_SUPPIER_STATE = "supplier_state";
    private static final String COLUMN_SUPPIER_PLACE = "supplier_place";
    private static final String TABLE_TRANSPORTER = "transporter_details";
    private static final String COLUMN_TRANS_ID = "transporter_id";
    private static final String COLUMN_ID_TRANSPORTER = "idTrans";
    private static final String COLUMN_TRANSPORTER_ADDRESS = "transporter_address";
    private static final String COLUMN_TRANSPORTER_EMAILID = "transporter_emailid";
    private static final String COLUMN_TRANSPORTER_NAME = "transporter_name";
    private static final String COLUMN_TRANSPORTER_MOBILENO = "transporter_mobileno";
    private static final String COLUMN_TRANSPORTER_PINCODE = "transporter_pincode";
    private static final String COLUMN_TRANSPORTER_STATE = "transporter_state";
    private static final String COLUMN_TRANSPORTER_PLACE = "transporter_place";
    private static final String COLUMN_INVOICE_ORDER_ID = "order_id";
    private static final String COLUMN_INVOICE_CLIENT_NAME = "orderClientName";
    private static final String COLUMN_INVOICE_CLIENT_GSTIN = "orderclientGstin";
    private static final String COLUMN_INVOICE_SUPPLIER_NAME = "orderSupplierName";
    private static final String COLUMN_INVOICE_SUPPLIER_GSTIN = "orderSupplierGstin";
    private static final String COLUMN_INVOICE_TRANSPORTER_NAME = "orderTransporterName";
    private static final String COLUMN_INVOICE_TRANSPORTER_GSTIN = "orderTransporterGstin";
    private static final String COLUMN_INVOICE_PRODUCT = "orderProductName";
    private static final String TABLE_INVOICE_ORDER = "order_details";

    private String clientUser;
    private String clientGSTIN;
    private String supplierUser;
    private String supplierGSTIN;
    private String transporterUser;
    private String transporterGSTIN;
    private Button btnAddProduct;

    private ListView listView;
    public CartItemAdapter mAdapter;
    private ArrayList<Product> mAddCartSelectedList;
    private static final int REQUEST_PRODUCT_LIST = 100;
    private String arrayListProduct;
    private DatabaseHelper databaseHelper= new DatabaseHelper(this);
    private ArrayList<CartItem> mAddCartList;
    private RelativeLayout registeredLayout;
    private Button saveOrderDetails;
    private Order order;
    private ListView showListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewaybillgeneration_layout);

        etSupplierUserName=(EditText)findViewById(R.id.etSupplierUserName);
        tvSupplierName=(TextView)findViewById(R.id.tvSupplierName);
        tvSupplierGstinNo=(TextView)findViewById(R.id.tvSupplierGstinNo);
        btnGetSupplier=(Button)findViewById(R.id.btnGetSupplier);
        btnGetSupplier.setOnClickListener(this);


        etClientUserName=(EditText)findViewById(R.id.etClientUserName);
        btnGetClient=(Button)findViewById(R.id.btnGetClient);
        tvClientUserName=(TextView)findViewById(R.id.tvClientUserName);
        tvClientGstinNo=(TextView)findViewById(R.id.tvClientGstinNo);
        btnGetClient.setOnClickListener(this);

        saveOrderDetails=(Button)findViewById(R.id.saveOrderDetails);
        saveOrderDetails.setOnClickListener(this);

        registeredLayout=(RelativeLayout)findViewById(R.id.registeredLayout);

        etTransporterUserName=(EditText)findViewById(R.id.etTransporterUserName);
        btnGetTransporter=(Button)findViewById(R.id.btnGetTransporter);
        tvTransporterUserName=(TextView)findViewById(R.id.tvTransporterUserName);
        tvTransporterGstinNo=(TextView)findViewById(R.id.tvTransporterGstinNo);
        btnGetTransporter.setOnClickListener(this);

        btnAddProduct=(Button)findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(this);

        mAddCartList = getIntent().getParcelableArrayListExtra("LIST");
        showListView = (ListView)findViewById(R.id.listCartView);

        mAdapter = new CartItemAdapter(this,mAddCartList);
        //mAdapter.updateCartItems(mAddCartList);
        showListView.setAdapter(mAdapter);

        // initXMLResources();
    }

   /* private void initXMLResources(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        mAddCartSelectedList = new ArrayList<>();
        mAdapter = new EWayProductAdapter(this,mAddCartSelectedList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }*/

    public void clientDataDetaills(){
        String name= etClientUserName.getText().toString().trim();

        String[] columns = {
                COLUMN_USER_CLIENT_ID,
                COLUMN_USER_GSTIN_NO,
                COLUMN_USER_EMAILID,
                COLUMN_USER_NAME_CLIENT,
                COLUMN_USER_MOBILENO,
                COLUMN_USER_PINCODE,
                COLUMN_USER_STATE,
                COLUMN_USER_PLACE

        };
        DatabaseHelper db1=new DatabaseHelper(this);
        db =  db1.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME_CLIENT + " = ?";

        // selection arguments
        String[] selectionArgs = {name};
        Cursor cursor = db.query(TABLE_CLIENT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        if (cursorCount > 0) {

            if (!cursor.isAfterLast()) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {


                    clientUser= cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME_CLIENT));
                    clientGSTIN= cursor.getString(cursor.getColumnIndex(COLUMN_USER_GSTIN_NO));

                    cursor.moveToNext();
                }
            }

        }
    }

    public void supplierDataDetaills(){
        String name= etSupplierUserName.getText().toString().trim();

        String[] columns = {
                COLUMN_SUPPIER_ID,
                COLUMN_SUPPIER_GSTIN_NO,
                COLUMN_SUPPIER_EMAILID,
                COLUMN_SUPPIER_NAME_CLIENT,
                COLUMN_SUPPIER_MOBILENO,
                COLUMN_SUPPIER_PINCODE,
                COLUMN_SUPPIER_STATE,
                COLUMN_SUPPIER_PLACE

        };
        DatabaseHelper db1=new DatabaseHelper(this);
        db =  db1.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_SUPPIER_NAME_CLIENT + " = ?";

        // selection arguments
        String[] selectionArgs = {name};
        Cursor cursor = db.query(TABLE_SUPPLIER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        if (cursorCount > 0) {

            if (!cursor.isAfterLast()) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {


                    supplierUser= cursor.getString(cursor.getColumnIndex(COLUMN_SUPPIER_NAME_CLIENT));
                    supplierGSTIN= cursor.getString(cursor.getColumnIndex(COLUMN_SUPPIER_GSTIN_NO));

                    cursor.moveToNext();
                }
            }

        }
    }

    public void transporterDataDetaills(){
        String name= etTransporterUserName.getText().toString().trim();

        String[] columns = {
                COLUMN_TRANS_ID,
                COLUMN_ID_TRANSPORTER,
                COLUMN_TRANSPORTER_ADDRESS,
                COLUMN_TRANSPORTER_EMAILID,
                COLUMN_TRANSPORTER_NAME,
                COLUMN_TRANSPORTER_MOBILENO,
                COLUMN_TRANSPORTER_PINCODE,
                COLUMN_TRANSPORTER_STATE,
                COLUMN_TRANSPORTER_PLACE
        };

        DatabaseHelper db1=new DatabaseHelper(this);
        db =  db1.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_TRANSPORTER_NAME + " = ?";

        // selection arguments
        String[] selectionArgs = {name};
        Cursor cursor = db.query(TABLE_TRANSPORTER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        if (cursorCount > 0) {

            if (!cursor.isAfterLast()) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {


                    transporterUser= cursor.getString(cursor.getColumnIndex(COLUMN_TRANSPORTER_NAME));
                    transporterGSTIN= cursor.getString(cursor.getColumnIndex(COLUMN_ID_TRANSPORTER));

                    cursor.moveToNext();
                }
            }

        }
    }


    private void generateOrderInvoiec(){
        Order order=new Order();

        order.setClientName(tvClientUserName.getText().toString().trim());
        order.setClientGSTINNo(tvClientGstinNo.getText().toString().trim());
        order.setSupplierName(tvSupplierName.getText().toString().trim());
        order.setSupplierGSTINNo(tvSupplierGstinNo.getText().toString().trim());
        order.setTransporterName(tvTransporterUserName.getText().toString().trim());
        order.setTransporterGSTINNo(tvTransporterGstinNo.getText().toString().trim());
        JSONObject json = new JSONObject();
        order.setProductDescription(getArrayListProduct());

        databaseHelper.addOrder(order);

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(registeredLayout, getString(R.string.Order_success_message), Snackbar.LENGTH_LONG).show();
        //  emptyInputEditText();

    }

    public void getInvoice( ) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_INVOICE_ORDER_ID,
                COLUMN_INVOICE_CLIENT_NAME,
                COLUMN_INVOICE_CLIENT_GSTIN,
                COLUMN_INVOICE_SUPPLIER_NAME,
                COLUMN_INVOICE_SUPPLIER_GSTIN,
                COLUMN_INVOICE_TRANSPORTER_NAME,
                COLUMN_INVOICE_TRANSPORTER_GSTIN,
                COLUMN_INVOICE_PRODUCT
        };
        DatabaseHelper data= new DatabaseHelper(this);
        SQLiteDatabase db = data.getReadableDatabase();
        // selection criteria
        //String selection = COLUMN_INVOICE_ORDER_ID + " = ?";

        // selection arguments
        //  String[] selectionArgs = {name, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_INVOICE_ORDER, //Table to query
                columns,                    //columns to return
                null,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        //  cursor.close();
        // db.close();
        if (cursorCount > 0) {

            if (!cursor.isAfterLast()) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {

                    order=new Order();
                    order.setOrderId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE_ORDER_ID))));
                    order.setClientName(cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE_CLIENT_NAME)));
                    order.setClientGSTINNo(cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE_CLIENT_GSTIN)));
                    order.setSupplierName(cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE_SUPPLIER_NAME)));
                    order.setSupplierGSTINNo(cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE_SUPPLIER_GSTIN)));
                    order.setTransporterName(cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE_TRANSPORTER_NAME)));
                    order.setTransporterGSTINNo(cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE_TRANSPORTER_GSTIN)));
                    order.setProductDescription(cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE_PRODUCT)));

                    cursor.moveToNext();
                }
            }
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetSupplier:
                supplierDataDetaills();
                tvSupplierName.setText("Supplier Name: " + supplierUser);
                tvSupplierGstinNo.setText("GSTIN No :" + supplierGSTIN);

                break;
            case R.id.btnGetClient:
                clientDataDetaills();
                tvClientUserName.setText("Client Name :" + clientUser);
                tvClientGstinNo.setText("GSTIN No :" + clientGSTIN);

                break;
            case R.id.btnGetTransporter:
                transporterDataDetaills();

                tvTransporterUserName.setText("Transporter Name :" + transporterUser);
                tvTransporterGstinNo.setText("GSTIN No :" + transporterGSTIN);
                break;

            case R.id.btnAddProduct:
                Intent intent=new Intent(GenerateEWayBillActivity.this,MainActivity.class);
                startActivityForResult(intent,REQUEST_PRODUCT_LIST);
                break;
            case R.id.saveOrderDetails:
                generateOrderInvoiec();
                getInvoice();
                Intent i =new Intent(GenerateEWayBillActivity.this,ShoppingCartActivity.class);
                startActivity(i);
                break;

        }
    }

    public String getArrayListProduct() {
        return arrayListProduct;
    }

    public void setArrayListProduct(String arrayListProduct) {
        this.arrayListProduct = arrayListProduct;
    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_PRODUCT_LIST){
            if(resultCode == RESULT_OK){
                if(data != null){
                   mAddCartList = data.getParcelableArrayListExtra("selectedCartItemsList");
                    if(mAddCartSelectedList == null){
                        mAddCartSelectedList = new ArrayList<>();
                    }
                    mAddCartSelectedList.addAll(mAddCartList);
                   JSONObject json = new JSONObject();
                    try {
                        json.put("uniqueArrays", new JSONArray(mAddCartList));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arrayListProduct = json.toString();

                    mAdapter.notifyDataSetChanged();
                    Log.d("hi",mAddCartList.size()+"");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}
