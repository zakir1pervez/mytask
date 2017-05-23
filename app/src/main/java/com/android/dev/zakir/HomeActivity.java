package com.android.dev.zakir;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.dev.zakir.Utility.TypeCall;
import com.android.dev.zakir.adapters.PagerAdapter;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends BaseAppActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener {

    private SliderLayout mtSlider;
    private TabLayout mTab;
    private DrawerLayout mDrawer;

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initSlider();
        addAllTabs();
    }

    private void initView() {
//        Adding Navigation Drawer
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

//        initializing navigation drawer
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mToggle.syncState();

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

//        init SliderLayout
        mtSlider = (SliderLayout) findViewById(R.id.mt_slider);

//        init TabLayout
        mTab = (TabLayout) findViewById(R.id.mt_my_tab);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_camera:
                showToast("camera Click", 0);
                break;
            case R.id.nav_gallery:
                showToast("gallery Click", 0);
                break;
            case R.id.nav_slideshow:
                showToast("slideshow Click", 0);
                break;
            case R.id.nav_manage:
                showToast("manage Click", 0);
                break;
            case R.id.nav_share:
                showToast("share Click", 0);
                break;
            case R.id.nav_send:
                showToast("send Click", 0);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * This is method is use to initialize SliderLayout
     */
    private void initSlider() {
        HashMap<String, Integer> imagesMap = new HashMap<>();
        imagesMap.put("Hannibal", R.drawable.hannibal);
        imagesMap.put("Big Bang Theory", R.drawable.bigbang);
        imagesMap.put("House of Cards", R.drawable.house);
        imagesMap.put("Game of Thrones", R.drawable.game_of_thrones);

        for (String name : imagesMap.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(imagesMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mtSlider.addSlider(textSliderView);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }


    /**
     * This method is use to initialize TabLayout
     */
    private void addAllTabs() {
        mTab.addTab(mTab.newTab().setText("Video"));
        mTab.addTab(mTab.newTab().setText("Images"));
        mTab.addTab(mTab.newTab().setText("Milestone"));
        final ViewPager mPager = (ViewPager) findViewById(R.id.mt_pager);
        PagerAdapter mAdapter = new PagerAdapter(getSupportFragmentManager(), mTab.getTabCount());
        mPager.setAdapter(mAdapter);
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//       adding icons
        mTab.getTabAt(0).setIcon(R.drawable.dwl_video);
        mTab.getTabAt(1).setIcon(R.drawable.dwl_images);
        mTab.getTabAt(2).setIcon(R.drawable.dwl_milestone);

    }


    /**
     * This method is use to return all type of media you specify.
     * @param callFrom - to check which kind of media required.
     * @param context - to get content resolver.
     * @return - List<String> which contain media path.
     */
    public static List<String> getDataList(TypeCall callFrom, Context context) {
        List<String> lstVideos = new ArrayList<>();
        Uri uri = null;
        if (TypeCall.VIDEO.equals(callFrom)) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if (TypeCall.IMAGES.equals(callFrom)) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }/*else if(TypeCall.VIDEO.equals(callFrom)){
           I am not doing anything here
        }*/
        String[] strData = {MediaStore.Video.VideoColumns.DATA};
        if (uri != null){
            Cursor c = context.getContentResolver().query(uri, strData, null, null, null);
            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                do {
                    lstVideos.add((c.getString(c.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))));
                } while (c.moveToNext());
                c.close();
            }
        }
        return lstVideos;
    }

}
