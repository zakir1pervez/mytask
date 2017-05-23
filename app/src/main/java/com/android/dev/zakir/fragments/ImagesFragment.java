package com.android.dev.zakir.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.dev.zakir.HomeActivity;
import com.android.dev.zakir.R;
import com.android.dev.zakir.Utility.RecyclerItemDecoration;
import com.android.dev.zakir.Utility.TypeCall;
import com.android.dev.zakir.adapters.RVAdapter;

import java.util.List;

public class ImagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images, container, false);
        initView(view);
        return view;
    }

    private void initView(View parentView) {
        RecyclerView mt_rcv_images = (RecyclerView) parentView.findViewById(R.id.mt_rcv_images);
        RelativeLayout rl_container = (RelativeLayout) parentView.findViewById(R.id.rl_container);
        List<String> lstItem = HomeActivity.getDataList(TypeCall.IMAGES, getActivity());
        if(lstItem.size() > 0){
            rl_container.setVisibility(View.GONE);
            mt_rcv_images.setVisibility(View.VISIBLE);
            RVAdapter adapter = new RVAdapter(getActivity(), lstItem, TypeCall.IMAGES);
            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getActivity());
            mt_rcv_images.setLayoutManager(mLayoutManager);
            mt_rcv_images.setAdapter(adapter);

        }else{
            rl_container.setVisibility(View.VISIBLE);
            mt_rcv_images.setVisibility(View.GONE);
        }
        mt_rcv_images.addItemDecoration(new RecyclerItemDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.rcv_divider)));
    }

}
