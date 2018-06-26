package ewaybill.nectar.com.ewaybill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.AllTransporterListActivity;
import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.supplier.SupplierData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.transporter.TransporterData;

public class AllTransportersAdapter extends RecyclerView.Adapter<AllTransportersAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<TransporterData> mList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(TransporterData transporterData);
    }
    public AllTransportersAdapter(Context context, ArrayList<TransporterData> list) {
        this.mContext =context;
        this.mList = list;
        if(context instanceof AllTransporterListActivity) {
            this.mListener = (OnItemClickListener) context;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.supplier_item_layout,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        TransporterData transporterData = mList.get(position);
        viewHolder.mNameTextView.setText("Name: " + transporterData.getTransporterName().trim());
        viewHolder.mGstnTextView.setText("GSTIN: " + transporterData.getTransporterGSTIN().trim());
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onItemClick(mList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
}
