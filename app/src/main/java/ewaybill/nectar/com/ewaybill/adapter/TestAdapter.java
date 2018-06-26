package ewaybill.nectar.com.ewaybill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ewaybill.nectar.com.ewaybill.R;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {

    private static final String TAG = TestAdapter.class.getSimpleName();
    private final Context mContext;
    private final List<String> mList;

    public TestAdapter(Context context, List<String> mStrings){
        this.mContext = context;
        this.mList = mStrings;
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item,parent,false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(TestHolder holder, int position) {
        holder.mItemTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class TestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.itemTextView)
        TextView mItemTextView;
        public TestHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext,"Hi Abhi" + getAdapterPosition() , Toast.LENGTH_SHORT).show();
        }
    }
}
