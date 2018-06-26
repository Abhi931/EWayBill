package ewaybill.nectar.com.ewaybill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nectar on 25-06-2018.
 */

class FileAdapter extends BaseAdapter {

    Context con;
    ArrayList<String> imagesEncodedList;
    public FileAdapter(InvoiceEWayBillActivity invoiceEWayBillActivity, ArrayList<String> imagesEncodedList) {
        this.con=invoiceEWayBillActivity;
        this.imagesEncodedList=imagesEncodedList;
    }

    @Override
    public int getCount() {
        return imagesEncodedList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater=LayoutInflater.from(con);
        row=inflater.inflate(R.layout.fileitem,parent,false);
        TextView filename=(TextView)row.findViewById(R.id.filename);
        File file = new File(imagesEncodedList.get(position));
        filename.setText(file.getName());
        return row;
    }
}
