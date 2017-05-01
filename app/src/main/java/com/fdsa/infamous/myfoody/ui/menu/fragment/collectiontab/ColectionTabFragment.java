package com.fdsa.infamous.myfoody.ui.menu.fragment.collectiontab;

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
import com.fdsa.infamous.myfoody.common.myinterface.IOnTopMenuBarChange;

/**
 * Created by FDSA on 4/8/2017.
 */

public class ColectionTabFragment extends Fragment implements IOnTopMenuBarChange, ViewPager.OnPageChangeListener {
    HeaderCollectionFragment headerCollectionFragment;
    CollectionTab1Fragment collectionTab1Fragment;
    CollectionTab2Fragment collectionTab2Fragment;
    ViewPager viewPager;
    LinearLayout linear_layout_collection_top_menu_bar;

    //Hàm khởi tạo
    public ColectionTabFragment() {
        super();
        headerCollectionFragment = new HeaderCollectionFragment();
        collectionTab1Fragment = new CollectionTab1Fragment();
        collectionTab2Fragment = new CollectionTab2Fragment();
    }

    /**
     * Hàm xử lí sự kiện khi fragment được khởi tạo (khởi tạo view và event)
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.collection_tab_fragment, container, false);


        viewPager = (ViewPager) v.findViewById(R.id.viewpager_collection);
        viewPager.setAdapter(new myTabCollectionFragmentAdapter(getChildFragmentManager()));

        viewPager.addOnPageChangeListener(this);
        headerCollectionFragment.setOnPageChange(this);

        linear_layout_collection_top_menu_bar = (LinearLayout) v.findViewById(R.id.linear_layout_collection_top_menu_bar);

        getFragmentManager().beginTransaction().add(R.id.linear_layout_collection_top_menu_bar, headerCollectionFragment).show(headerCollectionFragment).commitAllowingStateLoss();

        viewPager.setCurrentItem(0);
        return v;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * Hàm xử lí sự kiện khi thay đổi vị trí của viewpager
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

        headerCollectionFragment.setNotify(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * Hàm xử lí sự kiện khi click vào các view trên top menu (thay đổi vị trí của viewpager)
     * @param index
     */
    @Override
    public void OnTopMenuBarChange(int index) {
        this.viewPager.setCurrentItem(index);
    }

    /**
     * Adapter của viewpager (Quản lí các fragment trên CollectionTabFragment)
     */
    class myTabCollectionFragmentAdapter extends FragmentPagerAdapter {
        public myTabCollectionFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getCount() {
            return 2;
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return collectionTab1Fragment;
                default:
                    return collectionTab2Fragment;
            }
        }
    }
}
