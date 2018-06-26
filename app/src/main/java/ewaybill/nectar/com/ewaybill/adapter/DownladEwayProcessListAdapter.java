package ewaybill.nectar.com.ewaybill.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.jsonModelResponses.Invoice.InvoiceData;
import ewaybill.nectar.com.ewaybill.utils.DownloadManagerUtils;

public class DownladEwayProcessListAdapter extends RecyclerView.Adapter<DownladEwayProcessListAdapter.ViewHolder> {

    private static final String TAG = DownladEwayProcessListAdapter.class.getSimpleName();
    private final Context mContext;
    private final ArrayList<InvoiceData> mList;

    public DownladEwayProcessListAdapter(Context context, ArrayList<InvoiceData> list) {
        this.mContext =context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.download_eway_item_layout,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final InvoiceData invoiceData = mList.get(position);
        viewHolder.mTicketTokenNo.setText("E-Way Ticket No " + invoiceData.getEwayTicketId().trim()
                + "" + invoiceData.getInvoiceTitle().trim());
        // viewHolder.mTicketInvoiceTitle.setText("Invoice Title: " + invoiceData.getInvoiceTitle().trim());

        viewHolder.mDownloadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadManagerUtils(mContext, Uri.parse(invoiceData.getUploaded_invoice_data())).downloadSingleFile();
            }
        });

        viewHolder.mShareImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String title =  "Checkout this " + invoiceData.getInvoiceTitle() + " uploaded invoice : " + invoiceData.getEwayTicketId();
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title + " " + title);
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, title + " " + title + "\n");
                    ((Activity)mContext).startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageButton mDownloadImageButton,mShareImageButton;
        //@BindViews({R.id.nameTextView,R.id.gstnTextView})
        TextView mTicketInvoiceTitle,mTicketTokenNo;

        public ViewHolder(View itemView) {
            super(itemView);
            // ButterKnife.bind(this,itemView);
            mTicketInvoiceTitle=(TextView) itemView.findViewById(R.id.ticketInvoiceTitle);
            mTicketTokenNo=(TextView) itemView.findViewById(R.id.ticketTokenNo);
            mDownloadImageButton = (ImageButton) itemView.findViewById(R.id.downloadImageButton);
            mShareImageButton = (ImageButton) itemView.findViewById(R.id.shareImageButton);
        }
    }
}
