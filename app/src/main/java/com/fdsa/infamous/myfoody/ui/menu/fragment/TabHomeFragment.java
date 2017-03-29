package com.fdsa.infamous.myfoody.ui.menu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.views.TopMenuBar;

/**
 * Created by FDSA on 3/26/2017.
 */

public class TabHomeFragment extends Fragment implements ViewPager.OnPageChangeListener,View.OnClickListener, TopMenuBar.IOnTopMenuBarChange {
    TopMenuBar mTopMenuBar;
    Context context;
    LinearLayout hom_new_ui_linear_layout_location_bar;
    WhereToGoFragment whereToGoFragment;
    WhatToDoFragment whatToDoFragment;
    ViewPager viewPager;
    LinearLayout linear_layout_home_fragment_menu_parent;

    public TabHomeFragment() {
        super();
        whereToGoFragment=new WhereToGoFragment();
        whatToDoFragment=new WhatToDoFragment();
    }
    class myTabHomeFragmentAdapter extends  FragmentPagerAdapter{
        public myTabHomeFragmentAdapter(FragmentManager fm){
            super(fm);
        }
        public int getCount() {
            return 2;
        }

        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return whereToGoFragment;
                default:
                    return whatToDoFragment;
            }

        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_home_fragment, container, false);

        init(view);

        return view;
    }
    /**
     * Init
     */
    private void init(View view){
        mTopMenuBar = new TopMenuBar(getActivity().getApplicationContext(), this.getFragmentManager());

        viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new myTabHomeFragmentAdapter(getChildFragmentManager()));
        viewPager.addOnPageChangeListener(this);
        mTopMenuBar.setOnPageChange(this);
        hom_new_ui_linear_layout_location_bar = (LinearLayout) view.findViewById(R.id.hom_new_ui_linear_layout_location_bar);
        hom_new_ui_linear_layout_location_bar.removeAllViews();
        //hom_new_ui_linear_layout_location_bar.addView(mTopMenuBar);


       // mTopMenuBar.setNotify(0);
        viewPager.setCurrentItem(0);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
      //  mTopMenuBar.setNotify(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view){

    }

    @Override
    public void OnTopMenuBarChange(int index) {
        this.viewPager.setCurrentItem(index);
    }
}
