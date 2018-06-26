package ewaybill.nectar.com.ewaybill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice.InvoiceData;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.client.ClientData;

public class AllInvoicProcessListAdapter extends RecyclerView.Adapter<AllInvoicProcessListAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<InvoiceData> mList;

    public AllInvoicProcessListAdapter(Context context, ArrayList<InvoiceData> list) {
        this.mContext =context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invoice_item_layout,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        InvoiceData invoiceData = mList.get(position);
        viewHolder.mTicketTokenNo.setText("E-Way Ticket No " + invoiceData.getEwayTicketId().trim()
        + "" + invoiceData.getInvoiceTitle().trim());
       // viewHolder.mTicketInvoiceTitle.setText("Invoice Title: " + invoiceData.getInvoiceTitle().trim());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //@BindViews({R.id.nameTextView,R.id.gstnTextView})
        TextView mTicketInvoiceTitle,mTicketTokenNo;

        public ViewHolder(View itemView) {
            super(itemView);
           // ButterKnife.bind(this,itemView);
            mTicketInvoiceTitle=(TextView) itemView.findViewById(R.id.ticketInvoiceTitle);
            mTicketTokenNo=(TextView) itemView.findViewById(R.id.ticketTokenNo);
        }
    }
}
