package ewaybill.nectar.com.ewaybill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ewaybill.nectar.com.ewaybill.adapter.CartItemAdapter;
import ewaybill.nectar.com.ewaybill.model.Cart;
import ewaybill.nectar.com.ewaybill.model.CartItem;
import ewaybill.nectar.com.ewaybill.model.Product;
import ewaybill.nectar.com.ewaybill.model.Saleable;
import ewaybill.nectar.com.ewaybill.utils.CartHelper;
import ewaybill.nectar.com.ewaybill.utils.ProductConstant;

public class ShoppingCartActivity extends AppCompatActivity {
    private static final String TAG = "ShoppingCartActivity";

    ListView lvCartItems;
    Button bClear;
    Button bShop;
    TextView tvTotalPrice;
    private Button bCheckOut;
    private ArrayList<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvCartItems = (ListView) findViewById(R.id.lvCartItems);
        LayoutInflater layoutInflater = getLayoutInflater();

        final Cart cart = CartHelper.getCart();
        final TextView tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText(ProductConstant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));

        lvCartItems.addHeaderView(layoutInflater.inflate(R.layout.cart_header, lvCartItems, false));

        final CartItemAdapter cartItemAdapter = new CartItemAdapter(this);
        cartItemAdapter.updateCartItems(getCartItems(cart));

        lvCartItems.setAdapter(cartItemAdapter);

        bClear = (Button) findViewById(R.id.bClear);
        bShop = (Button) findViewById(R.id.bShop);

        bClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clearing the shopping cart");
                cart.clear();
                cartItemAdapter.updateCartItems(getCartItems(cart));
                cartItemAdapter.notifyDataSetChanged();
                tvTotalPrice.setText(ProductConstant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
            }
        });

        bShop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lvCartItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ShoppingCartActivity.this)
                        .setTitle(getResources().getString(R.string.delete_item))
                        .setMessage(getResources().getString(R.string.delete_item_message))
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cartItems = getCartItems(cart);
                                cart.remove(cartItems.get(position-1).getProduct());
                                cartItems.remove(position-1);
                                cartItemAdapter.updateCartItems(cartItems);
                                cartItemAdapter.notifyDataSetChanged();
                                tvTotalPrice.setText(ProductConstant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();
                return false;
            }
        });

        lvCartItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                List<CartItem> cartItems = getCartItems(cart);
                Product product = cartItems.get(position-1).getProduct();
                Log.d(TAG, "Viewing product: " + product.getName());
                bundle.putParcelable("product", product);
                Intent intent = new Intent(ShoppingCartActivity.this, ProductActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        bCheckOut=(Button)findViewById(R.id.bCheckOut);
        bCheckOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItems = getCartItems(cart);
                if (cartItems != null && cartItems.size() > 0) {
                 /*   Intent intent = new Intent(ShoppingCartActivity.this, GenerateEWayBillActivity.class);
                    intent.putParcelableArrayListExtra("LIST",cartItems);
                    startActivity(intent);*/
                    /*intent.putParcelableArrayListExtra("selectedCartItemsList", (ArrayList<? extends Parcelable>) cartItems);
                    setResult(RESULT_OK,intent);
                    finish();*/
                } else {
                    Toast.makeText(ShoppingCartActivity.this, "Please select atleast one item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<CartItem> getCartItems(Cart cart) {
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

        for (Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }
}
