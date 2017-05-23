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

import com.android.dev.zakir.R;
import com.android.dev.zakir.Utility.RecyclerItemDecoration;
import com.android.dev.zakir.Utility.TypeCall;
import com.android.dev.zakir.adapters.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class MilestoneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_milestone, container, false);
        initView(view);
        return view;
    }

    private void initView(View parentView) {
        RecyclerView mt_rcv_milestone = (RecyclerView) parentView.findViewById(R.id.mt_rcv_milestone);
        RelativeLayout rl_container = (RelativeLayout) parentView.findViewById(R.id.rl_container);
//        Note : I am not showing anything inside this fragment
        rl_container.setVisibility(View.VISIBLE);
        mt_rcv_milestone.setVisibility(View.GONE);

    }
}
