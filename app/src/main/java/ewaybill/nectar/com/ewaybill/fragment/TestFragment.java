package ewaybill.nectar.com.ewaybill.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ewaybill.nectar.com.ewaybill.R;
import ewaybill.nectar.com.ewaybill.adapter.TestAdapter;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;

public class TestFragment extends Fragment {

    private static final String TAG = TestFragment.class.getSimpleName();
    private View rootView;


    RecyclerView mRecylerView;
    private ArrayList<String> mList;
   // private FeedChatNotifPresenterImpl mFeedPresenter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.test, container, false);
        mRecylerView=(RecyclerView) rootView.findViewById(R.id.recyclerView);

       // ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // mRecylerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecylerView.setLayoutManager(linearLayoutManager);
        mList = new ArrayList<String>();
        mList.add("I ");
        mList.add("I ");
        mList.add("I will cook food for you!!!");
        TestAdapter mTestAdapter = new TestAdapter(getActivity(), mList);
        mRecylerView.setAdapter(mTestAdapter);

        //    initAPIResources();

     /*   EndlessRecyclerViewScrollListener mPaginatedChatListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                initAPIResources(); // page should be incremented
            }
        };

        mRecylerView.addOnScrollListener(mPaginatedChatListener);
    }*/
/*
    public void initAPIResources(){
        if (NetworkUtil.isOnline(this)) {
            mFeedPresenter = new FeedChatNotifPresenterImpl(this);
            mFeedPresenter.callApi(AppConstants.FEED_KEY, page);

        }

    }*/
    }
}