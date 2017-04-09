package com.fdsa.infamous.myfoody.ui.menu.fragment.collectiontab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.fragment.hometab.TopMenuBarFragment;


/**
 * Created by FDSA on 4/8/2017.
 */

public class HeaderCollectionFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    public Tab tab_item_1_header_collection;
    public Tab tab_item_2_header_collection;
    TabLayout tab_layout_header_collection;
    TopMenuBarFragment.IOnTopMenuBarChange onPageChange;

    public HeaderCollectionFragment() {
        super();
    }

    public void setOnPageChange(TopMenuBarFragment.IOnTopMenuBarChange onPageChange) {
        this.onPageChange = onPageChange;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.collection_tab_header, container, false);

        tab_layout_header_collection = (TabLayout) v.findViewById(R.id.tab_layout_header_collection);

        tab_item_1_header_collection = tab_layout_header_collection.getTabAt(0);
        tab_item_2_header_collection = tab_layout_header_collection.getTabAt(1);

        tab_layout_header_collection.addOnTabSelectedListener(this);
        setNotify(0);

        return v;
    }

    public void setNotify(int currentPageIndex) {
        if (tab_item_1_header_collection == null || tab_item_2_header_collection == null) {
            Log.d("NULL", "null");
            return;
        }

        Log.d("RUNNNIN", "Index: " + currentPageIndex + "");
        if (currentPageIndex == 0) {
            tab_item_1_header_collection.select();
            return;
        }

        tab_item_2_header_collection.select();
    }

    @Override
    public void onTabSelected(Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                onPageChange.OnTopMenuBarChange(0);
                break;
            default:
                onPageChange.OnTopMenuBarChange(1);
                break;
        }
    }

    @Override
    public void onTabUnselected(Tab tab) {

    }

    @Override
    public void onTabReselected(Tab tab) {

    }
}
