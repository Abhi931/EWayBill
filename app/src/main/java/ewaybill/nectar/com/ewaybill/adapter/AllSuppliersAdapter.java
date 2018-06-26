package ewaybill.nectar.com.ewaybill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindViews;
import butterknife.ButterKnife;
import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.userregistration.UserSupplierRegistrationResponse;

public class AllSuppliersAdapter extends RecyclerView.Adapter<AllSuppliersAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<SupplierData> mList;

    public AllSuppliersAdapter(Context context, ArrayList<SupplierData> list) {
        this.mContext =context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.supplier_item_layout,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        SupplierData supplierData = mList.get(position);
        viewHolder.mNameTextView.setText("Name: " + supplierData.getSupplierName().trim());
        viewHolder.mGstnTextView.setText("GSTIN: " + supplierData.getSupplierGSTIN().trim());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //@BindViews({R.id.nameTextView,R.id.gstnTextView})
        TextView mNameTextView,mGstnTextView;

        public ViewHolder(View itemView) {
            super(itemView);
           // ButterKnife.bind(this,itemView);
            mNameTextView=(TextView) itemView.findViewById(R.id.nameTextView);
            mGstnTextView=(TextView) itemView.findViewById(R.id.gstnTextView);
        }
    }
}
