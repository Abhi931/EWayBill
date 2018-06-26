package ewaybill.nectar.com.ewaybill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.client.ClientData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierData;

public class AllClientsAdapter extends RecyclerView.Adapter<AllClientsAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<ClientData> mList;

    public AllClientsAdapter(Context context, ArrayList<ClientData> list) {
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

        ClientData clientData = mList.get(position);
        viewHolder.mNameTextView.setText("Name: " + clientData.getClientName().trim());
        viewHolder.mGstnTextView.setText("GSTIN: " + clientData.getClientGSTIN().trim());
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
