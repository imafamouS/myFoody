package com.fdsa.infamous.myfoody.ui.menu.fragment.hometab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fdsa.infamous.myfoody.R;

/**
 * Created by FDSA on 3/26/2017.
 */

public class TabHomeFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener, TopMenuBarFragment.IOnTopMenuBarChange {
    TopMenuBarFragment mTopMenuBarFragment;
    Context context;
    LinearLayout linear_layout_top_menu_bar;
    WhereToGoFragment whereToGoFragment;
    WhatToDoFragment whatToDoFragment;
    ViewPager viewPager;
    LinearLayout linear_layout_home_fragment_menu_parent;

    public TabHomeFragment() {
        super();
        mTopMenuBarFragment = new TopMenuBarFragment();
        whereToGoFragment=new WhereToGoFragment();
        whatToDoFragment=new WhatToDoFragment();

        whereToGoFragment.setWhatToDoFragment(whatToDoFragment);
        whatToDoFragment.setWhereToGoFragment(whereToGoFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_tab_fragment, container, false);

        init(view);

        return view;
    }

    /**
     * Init
     */
    private void init(View view){
        // mTopMenuBar = new TopMenuBar(getActivity().getApplicationContext(), this.getFragmentManager());
        mTopMenuBarFragment.setContext(getActivity().getApplicationContext());
        mTopMenuBarFragment.setFragmentManager(this.getFragmentManager());

        viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new myTabHomeFragmentAdapter(getChildFragmentManager()));

        viewPager.addOnPageChangeListener(this);
        mTopMenuBarFragment.setOnPageChange(this);

        linear_layout_top_menu_bar = (LinearLayout) view.findViewById(R.id.linear_layout_top_menu_bar);
        // hom_new_ui_linear_layout_location_bar.removeAllViews();
        getFragmentManager().beginTransaction().add(R.id.linear_layout_top_menu_bar, mTopMenuBarFragment).show(mTopMenuBarFragment).commitAllowingStateLoss();

        // getFragmentManager().beginTransaction().replace(R.id.linear_layout_top_menu_bar, mTopMenuBarFragment).commit();

        viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTopMenuBarFragment.setNotify(position);

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
        if(index==1){
            whatToDoFragment.onVisible();
        }else{
            whereToGoFragment.onVisible();
        }
    }

    class myTabHomeFragmentAdapter extends FragmentPagerAdapter {
        public myTabHomeFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getCount() {
            return 2;
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return whereToGoFragment;
                default:
                    return whatToDoFragment;
            }

        }
    }
}
