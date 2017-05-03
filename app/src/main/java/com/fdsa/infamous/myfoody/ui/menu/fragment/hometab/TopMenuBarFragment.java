package com.fdsa.infamous.myfoody.ui.menu.fragment.hometab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fdsa.infamous.myfoody.MainActivity;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.myinterface.IOnTopMenuBarChange;
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

    MainActivity mainActivity;

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    //Hàm khởi tạo
    public TopMenuBarFragment() {
        super();

    }

    //Hàm set interface IontopMenuBarChange
    public void setOnPageChange(IOnTopMenuBarChange onPageChange) {
        this.onPageChange = onPageChange;
    }

    //Hàm set context
    public void setContext(Context context) {
        this.context = context;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    /**
     * Hàm xử lí sự kiện khi fragment được tạo (khởi tạo các view)
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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

    //Hàm khởi tạo sự kiện
    private void initEvent() {
        tab_parent_top_menu.addOnTabSelectedListener(this);

        image_view_top_menu_plus_menu.setOnClickListener(this);

        linear_layout_top_menu_change_domain.setOnClickListener(this);
    }

    //Hàm thay đổi vị trí các tab
    public void setNotify(int currentPageIndex) {
        if (tab_where2go_top_menu == null || tab_what2do_top_menu == null) {
            return;
        }

        if (currentPageIndex == 0) {
            tab_where2go_top_menu.select();
            return;
        }

        tab_what2do_top_menu.select();
    }

    //Hàm xử lí sự kiện khi tab bị thay đổi
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

    //Hàm xử lí sự kiện click
    @Override
    public void onClick(View v) {
        int viewID = v.getId();

        switch (viewID) {
            case R.id.image_view_top_menu_plus_menu:
                //Plus menu
                onPlusMenu();
                break;
            case R.id.linear_layout_top_menu_change_domain:
                //Domain (F)
                onChangeDomain();
                break;
            default:
                break;
        }
    }

    //Click vào Ở đâu
    private void onWhere2Go() {
        onPageChange.OnTopMenuBarChange(0);
    }

    //CLick vào ăn gì
    private void onWhat2Do() {
        onPageChange.OnTopMenuBarChange(1);
    }

    //Click vào plus menu
    private void onPlusMenu() {
        new PlusActionView().show(fragmentManager, "Dialog");
    }

    //Click vào domain (F)
    private void onChangeDomain() {
        Intent intent = new Intent(context, DomainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
