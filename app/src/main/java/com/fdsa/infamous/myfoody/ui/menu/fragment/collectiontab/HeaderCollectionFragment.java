package com.fdsa.infamous.myfoody.ui.menu.fragment.collectiontab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.myinterface.IOnTopMenuBarChange;


/**
 * Created by FDSA on 4/8/2017.
 */

public class HeaderCollectionFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    public Tab tab_item_1_header_collection;
    public Tab tab_item_2_header_collection;
    TabLayout tab_layout_header_collection;
    IOnTopMenuBarChange onPageChange;

    //Hàm khởi tạo
    public HeaderCollectionFragment() {
        super();
    }

    /**
     * Hàm set interface IOnTopMenuBarChange
     *
     * @param onPageChange
     */
    public void setOnPageChange(IOnTopMenuBarChange onPageChange) {
        this.onPageChange = onPageChange;
    }

    /**
     * Hàm xử lí sự kiện khi fragment được khởi tạo (khởi tạo view)
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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

    /**
     * Hàm thay đổi  vị trí của các tab
     *
     * @param currentPageIndex
     */
    public void setNotify(int currentPageIndex) {
        if (tab_item_1_header_collection == null || tab_item_2_header_collection == null) {
            return;
        }
        if (currentPageIndex == 0) {
            tab_item_1_header_collection.select();
            return;
        }

        tab_item_2_header_collection.select();
    }

    /**
     * Hàm xử lí sự kiện khi thay đổi vị trí các tab
     *
     * @param tab
     */
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
