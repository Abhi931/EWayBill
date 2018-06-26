package ewaybill.nectar.com.ewaybill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.model.Cart;
import ewaybill.nectar.com.ewaybill.model.CartItem;
import ewaybill.nectar.com.ewaybill.utils.CartHelper;

public class CartItemAdapter extends BaseAdapter {
    private static final String TAG = "CartItemAdapter";
    private  ArrayList<CartItem> mAddCartList;

    private List<CartItem> cartItems = Collections.emptyList();

    private final Context context;

  /*  public CartItemAdapter(Context context, int simple_list_item_1, ArrayList<CartItem> mAddCartList) {
        this.context = context;
    }*/

    public CartItemAdapter(Context context, ArrayList<CartItem> mAddCartList) {
        this.context = context;
        this.cartItems = mAddCartList;

    }

    public CartItemAdapter(Context context) {
        this.context = context;
    }

    public void updateCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public CartItem getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView tvName;
        TextView tvUnitPrice;
        TextView tvQuantity;
        TextView tvPrice;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_cart_item, parent, false);
            tvName = (TextView) convertView.findViewById(R.id.tvCartItemName);
            tvUnitPrice = (TextView) convertView.findViewById(R.id.tvCartItemUnitPrice);
            tvQuantity = (TextView) convertView.findViewById(R.id.tvCartItemQuantity);
            tvPrice = (TextView) convertView.findViewById(R.id.tvCartItemPrice);
            convertView.setTag(new ViewHolder(tvName, tvUnitPrice, tvQuantity, tvPrice));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvCartItemName;
            tvUnitPrice = viewHolder.tvCartItemUnitPrice;
            tvQuantity = viewHolder.tvCartItemQuantity;
            tvPrice = viewHolder.tvCartItemPrice;
        }

        final Cart cart = CartHelper.getCart();
        final CartItem cartItem = getItem(position);
        tvName.setText(cartItem.getProduct().getName());
        //  tvUnitPrice.setText(ProductConstant.CURRENCY+String.valueOf(cartItem.getProduct().getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
        tvQuantity.setText(String.valueOf(cartItem.getQuantity()));
        //  tvPrice.setText(ProductConstant.CURRENCY+String.valueOf(cart.getCost(cartItem.getProduct()).setScale(2, BigDecimal.ROUND_HALF_UP)));
        return convertView;
    }

    private static class ViewHolder {
        public final TextView tvCartItemName;
        public final TextView tvCartItemUnitPrice;
        public final TextView tvCartItemQuantity;
        public final TextView tvCartItemPrice;

        public ViewHolder(TextView tvCartItemName, TextView tvCartItemUnitPrice, TextView tvCartItemQuantity, TextView tvCartItemPrice) {
            this.tvCartItemName = tvCartItemName;
            this.tvCartItemUnitPrice = tvCartItemUnitPrice;
            this.tvCartItemQuantity = tvCartItemQuantity;
            this.tvCartItemPrice = tvCartItemPrice;
        }
    }
}
