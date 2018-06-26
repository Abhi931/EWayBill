package ewaybill.nectar.com.ewaybill.adapter;

/**
 * Created by Abhishek on 4/10/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ewaybill.nectar.com.ewaybill.ProductListActivity;
import ewaybill.nectar.com.ewaybill.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private static final String TAG = ProductAdapter.class.getSimpleName();
    private final Context mContext;
    private ShowSelectedItem mListener;
    private List<ProductName> productsList;
    private boolean isMultiSelection = false;
    ArrayList<ProductName> productNameArrayList;

    public interface ShowSelectedItem{
        void showSelectedItem(ArrayList<ProductName> productNameArrayList);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        final ImageView logoImage;
        final FrameLayout overlayLayout;
        public TextView title, description;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            overlayLayout = (FrameLayout) view.findViewById(R.id.overlayLayout);
            logoImage = (ImageView) view.findViewById(R.id.logoImage);
            description = (TextView) view.findViewById(R.id.description);
        }
    }


    public ProductAdapter(Context context , List<ProductName> productsList) {
        this.mContext = context;
        this.productsList = productsList;
        this.productNameArrayList = new ArrayList<>();
        if(context instanceof ProductListActivity) {
            this.mListener = (ShowSelectedItem) context;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);

        final MyViewHolder holder = new MyViewHolder(itemView);

        if(mContext instanceof ProductListActivity) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (!isMultiSelection) {
                        ProductName productName = (ProductName) v.getTag();
                        if (!productName.isSelected) {
                            isMultiSelection = true;
                            productName.setSelected(true);
                            v.setSelected(true);
                            v.findViewById(R.id.overlayLayout).setVisibility(View.VISIBLE);
                            productNameArrayList.clear();
                            productNameArrayList.add(productName);
                            if (mListener != null) {
                                Log.d(TAG, productNameArrayList.size() + "");
                                mListener.showSelectedItem(productNameArrayList);
                            }
                            return true;
                        } else {
                            productName.setSelected(false);
                            v.setSelected(false);
                            holder.overlayLayout.setVisibility(View.GONE);
                            return true;
                        }
                    }
                    return false;
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isMultiSelection && productNameArrayList.size() > 0) {
                        ProductName productName = (ProductName) v.getTag();
                        if (!productName.isSelected) {
                            productName.setSelected(true);
                            v.setSelected(true);
                            v.findViewById(R.id.overlayLayout).setVisibility(View.VISIBLE);
                            productNameArrayList.add(productName);
                        } else {
                            productName.setSelected(false);
                            v.setSelected(false);
                            holder.overlayLayout.setVisibility(View.GONE);
                            //productNameArrayList.remove(productName);

                            Iterator itr = productNameArrayList.iterator();
                            while (itr.hasNext()) {
                                ProductName productName1 = (ProductName) itr.next();
                                if (productName1.equals(productName))
                                    itr.remove();
                            }
                        }
                        if (mListener != null) {
                            Log.d(TAG, productNameArrayList.size() + "");
                            mListener.showSelectedItem(productNameArrayList);
                        }
                    } else {
                        isMultiSelection = false;
                    }
                }
            });
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ProductName movie = productsList.get(position);
        holder.itemView.setTag(movie);
        holder.title.setText(movie.getProduct_name());
        holder.description.setText(movie.getDescription());
        holder.logoImage.setImageResource(movie.getImage());
    /*    holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());*/

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
}