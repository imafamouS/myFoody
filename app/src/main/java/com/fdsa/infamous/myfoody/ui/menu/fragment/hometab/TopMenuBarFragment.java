package com.fdsa.infamous.myfoody.ui.menu.fragment.hometab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.activity.DomainActivity;
import com.fdsa.infamous.myfoody.ui.menu.views.PlusActionView;

/**
 * Created by FDSA on 3/26/2017.
 */

public class TopMenuBarFragment extends Fragment implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    Context context;
    FragmentManager fragmentManager;
    TabLayout tab_parent_top_menu;

    Tab tab_where2go_top_menu;
    Tab tab_what2do_top_menu;

    ImageView image_view_top_menu_plus_menu;

    LinearLayout linear_layout_top_menu_change_domain;
    ImageView image_view_top_menu_change_domain;

    View view;

    IOnTopMenuBarChange onPageChange;

    public TopMenuBarFragment() {
        super();

    }

    public void setOnPageChange(IOnTopMenuBarChange onPageChange) {
        this.onPageChange = onPageChange;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.home_top_menu, container, false);

        tab_parent_top_menu = (TabLayout) view.findViewById(R.id.tab_parent_top_menu);

        tab_where2go_top_menu = tab_parent_top_menu.getTabAt(0);
        tab_what2do_top_menu = tab_parent_top_menu.getTabAt(1);

        image_view_top_menu_plus_menu = (ImageView) view.findViewById(R.id.image_view_top_menu_plus_menu);

        linear_layout_top_menu_change_domain = (LinearLayout) view.findViewById(R.id.linear_layout_top_menu_change_domain);
        image_view_top_menu_change_domain = (ImageView) view.findViewById(R.id.image_view_top_menu_change_domain);

        initEvent();
        setNotify(0);
        return view;
    }

    private void initEvent() {
        tab_parent_top_menu.addOnTabSelectedListener(this);

        image_view_top_menu_plus_menu.setOnClickListener(this);

        linear_layout_top_menu_change_domain.setOnClickListener(this);
    }

    public void setNotify(int currentPageIndex) {
        if (tab_where2go_top_menu == null || tab_what2do_top_menu == null) {
            Log.d("NULL", "null");
            return;
        }

        Log.d("RUNNNIN", "Index: " + currentPageIndex + "");
        if (currentPageIndex == 0) {
            tab_where2go_top_menu.select();
            return;
        }

        tab_what2do_top_menu.select();
    }

    @Override
    public void onTabSelected(Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                onWhere2Go();
                break;
            default:
                onWhat2Do();
                break;
        }
    }

    @Override
    public void onTabUnselected(Tab tab) {

    }

    @Override
    public void onTabReselected(Tab tab) {

    }

    @Override
    public void onClick(View v) {
        int viewID = v.getId();

        switch (viewID) {
            case R.id.image_view_top_menu_plus_menu:
                onPlusMenu();
                break;
            case R.id.linear_layout_top_menu_change_domain:
                onChangeDomain();
                break;
            default:
                break;
        }
    }

    private void onWhere2Go() {
        onPageChange.OnTopMenuBarChange(0);
    }

    private void onWhat2Do() {
        onPageChange.OnTopMenuBarChange(1);
    }

    private void onPlusMenu() {
        new PlusActionView().show(fragmentManager, "Dialog");
    }

    private void onChangeDomain() {
        Intent intent = new Intent(context, DomainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public interface IOnTopMenuBarChange {
        void OnTopMenuBarChange(int index);
    }
}
