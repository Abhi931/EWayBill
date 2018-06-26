package ewaybill.nectar.com.ewaybill.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ewaybill.nectar.com.ewaybill.AddCardListActivity;
import ewaybill.nectar.com.ewaybill.GenerateEWayBillActivity;
import ewaybill.nectar.com.ewaybill.MainActivity;
import ewaybill.nectar.com.ewaybill.ProductListActivity;
import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.model.CartItem;
import ewaybill.nectar.com.ewaybill.model.Product;
import ewaybill.nectar.com.ewaybill.utils.ProductConstant;

public class EWayProductAdapter extends BaseAdapter {
    private static final String TAG = "EWayProductAdapter";
    private  ArrayList<CartItem> mAddCartList;
    private ShowSelectedItem mListener;
    ArrayList<Product> mAddCartSelectedList;
    ArrayList<Product> productNameArrayList;
    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public EWayProductAdapter(AddCardListActivity context, ArrayList<CartItem> mAddCartList) {
        this.context=context;
        this.mAddCartList=mAddCartList;
    }

    public interface ShowSelectedItem{
        void showSelectedItem(ArrayList<Product> productNameArrayList);
    }

    public EWayProductAdapter(Context context) {
        this.context = context;
    }


    public EWayProductAdapter(Context context , ArrayList<Product> mAddCartSelectedList) {
        this.context = context;
        this.mAddCartSelectedList = mAddCartSelectedList;
        this.productNameArrayList = new ArrayList<>();
        if (context instanceof MainActivity) {
            this.mListener = (ShowSelectedItem) context;
        }
    }

    public void updateProducts(List<Product> products) {
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvName;
        TextView tvPrice;
        ImageView ivImage;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_product, parent, false);
            tvName = (TextView) convertView.findViewById(R.id.tvProductName);
            tvPrice = (TextView) convertView.findViewById(R.id.tvProductPrice);
            ivImage = (ImageView) convertView.findViewById(R.id.ivProductImage);
            convertView.setTag(new ViewHolder(tvName, tvPrice, ivImage));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvProductName;
            tvPrice = viewHolder.tvProductPrice;
            ivImage = viewHolder.ivProductImage;
        }

        final Product product = getItem(position);
        tvName.setText(product.getName());
        tvPrice.setText(ProductConstant.CURRENCY+String.valueOf(product.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
        Log.d(TAG, "Context package name: " + context.getPackageName());
        ivImage.setImageResource(context.getResources().getIdentifier(
                product.getpImageName(), "drawable", context.getPackageName()));
//        bView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProductActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("product", product);
//                Log.d(TAG, "This product hashCode: " + product.hashCode());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });

        return convertView;
    }

    private static class ViewHolder {
        public final TextView tvProductName;
        public final TextView tvProductPrice;
        public final ImageView ivProductImage;

        public ViewHolder(TextView tvProductName, TextView tvProductPrice, ImageView ivProductImage) {
            this.tvProductName = tvProductName;
            this.tvProductPrice = tvProductPrice;
            this.ivProductImage = ivProductImage;
        }
    }
}
