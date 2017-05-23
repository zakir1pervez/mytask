package com.android.dev.zakir.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.dev.zakir.fragments.ImagesFragment;
import com.android.dev.zakir.fragments.MilestoneFragment;
import com.android.dev.zakir.fragments.VideoFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new VideoFragment();
            case 1:
                return new ImagesFragment();
            case 2:
                return new MilestoneFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
