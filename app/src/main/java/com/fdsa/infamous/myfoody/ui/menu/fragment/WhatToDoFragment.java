package com.fdsa.infamous.myfoody.ui.menu.fragment;

/**
 * Created by FDSA on 3/27/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.Global.GlobalStaticData;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;
import com.fdsa.infamous.myfoody.ui.util.Type;
import com.fdsa.infamous.myfoody.ui.util.adapter.MenuBarAdapter;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WhatToDoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    TopMenuBarFragment mTopMenuBarFragment;
    Context context;
    LinearLayout linear_layout_parent_tab_menu;
    LinearLayout linear_layout_tab_menu_1;
    LinearLayout linear_layout_tab_menu_2;
    LinearLayout linear_layout_tab_menu_3;

    TextView text_view_tab_menu_cancel;


    ListView list_view_main_menu;
    MoreItemView moreItemView;
    View slideShowBanner;

    List<MenuBarItem> items;

    public WhatToDoFragment() {
        super();
        mapMenuBarItems = new HashMap<>();
        selectedPositionMenu = new HashMap<>();
        initDefaultPostionMenu();
    }

    private void initDefaultPostionMenu() {
        selectedPositionMenu.put(Type.LATEST, 0);
        selectedPositionMenu.put(Type.CATEGORY, 0);
        selectedPositionMenu.put(Type.AREA, 0);
    }
    public void setmTopMenuBarFragment(TopMenuBarFragment topMenuBarFragment) {
        this.mTopMenuBarFragment = topMenuBarFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_tab_menu_what_to_do, container, false);

        initView(view, inflater);

        return view;
    }

    LinearLayout linear_layout_what2do_show_item_tab_menu;
    ListView list_view_what2do_tab_menu;
    LayoutInflater inflater;

    private void initView(View view, LayoutInflater inflater) {
        context = getActivity().getApplicationContext();
        this.inflater = inflater;

        linear_layout_parent_tab_menu=(LinearLayout)view.findViewById(R.id.linear_layout_parent_what2do_tab_menu);
        linear_layout_tab_menu_1=(LinearLayout)view.findViewById(R.id.linear_layout_what2do_tab_menu_1);
        linear_layout_tab_menu_2=(LinearLayout)view.findViewById(R.id.linear_layout_what2do_tab_menu_2);
        linear_layout_tab_menu_3=(LinearLayout)view.findViewById(R.id.linear_layout_what2do_tab_menu_3);

        linear_layout_what2do_show_item_tab_menu = (LinearLayout) view.findViewById(R.id.linear_layout_what2do_show_item_tab_menu);
        list_view_what2do_tab_menu = (ListView) view.findViewById(R.id.list_view_what2do_tab_menu);
        text_view_tab_menu_cancel = (TextView) view.findViewById(R.id.text_view_what2do_tab_menu_cancel);

        list_view_main_menu = (ListView) view.findViewById(R.id.list_view_main_menu);

        list_view_what2do_tab_menu.setOnItemClickListener(this);

        // moreItemView = new MoreItemView(context);
        //slideShowBanner = inflater.inflate(R.layout.banner_image_fragment, list_view_main_menu, false);

        // list_view_main_menu.addHeaderView(slideShowBanner);
        //list_view_main_menu.addHeaderView(moreItemView);

        // list_view_main_menu.setAdapter(new MenuBarAdapter(getActivity().getApplicationContext(), getListItem(Type.LASTEST), Type.LASTEST));

        initEvent();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (menuBarAdapter != null)
            this.selectedPositionMenu.put(menuBarAdapter.getType(), position);
    }

    private void initEvent() {
        linear_layout_tab_menu_1.setOnClickListener(this);
        linear_layout_tab_menu_2.setOnClickListener(this);
        linear_layout_tab_menu_3.setOnClickListener(this);
        text_view_tab_menu_cancel.setOnClickListener(this);
    }

    MenuBarAdapter menuBarAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_what2do_tab_menu_1:
                hideMenuItem();
                showMenuItemBar(Type.LATEST);
                hideBottomBar();
                break;
            case R.id.linear_layout_what2do_tab_menu_2:
                hideMenuItem();
                showMenuItemBar(Type.CATEGORY);
                hideBottomBar();
                break;
            case R.id.linear_layout_what2do_tab_menu_3:
                hideMenuItem();
                showMenuItemBar(Type.AREA);
                hideBottomBar();
                break;
            case R.id.text_view_what2do_tab_menu_cancel:
                hideMenuItem();
                showBottomBar();
                break;
            default:
                break;
        }
    }

    private void hideBottomBar() {
        getActivity().findViewById(R.id.bottom_menu).setVisibility(View.GONE);
    }

    private void showBottomBar() {
        getActivity().findViewById(R.id.bottom_menu).setVisibility(View.VISIBLE);
    }

    private void hideMenuItem() {
        if (this.menuBarAdapter != null) {
            this.linear_layout_what2do_show_item_tab_menu.setVisibility(View.GONE);
            resetStateTabMenu();
        }

    }

    private void resetStateTabMenu() {
        this.linear_layout_tab_menu_1.setBackgroundResource(R.drawable.border);
        this.linear_layout_tab_menu_2.setBackgroundResource(R.drawable.border);
        this.linear_layout_tab_menu_3.setBackgroundResource(R.drawable.border);
    }

    Map<Type, List<MenuBarItem>> mapMenuBarItems;
    Map<Type, Integer> selectedPositionMenu;

    private void showMenuItemBar(Type type) {

        resetStateTabMenu();
        if (isNeedClose(type)) {
            hideMenuItem();
            return;
        }
        showMenu(type);

        this.mapMenuBarItems.put(type, getListMenuData(type));
        this.menuBarAdapter = new MenuBarAdapter(getActivity(), (List) this.mapMenuBarItems.get(type), type);
        this.menuBarAdapter.notifyDataSetChanged();
        this.list_view_what2do_tab_menu.setAdapter(this.menuBarAdapter);
    }

    private boolean isNeedClose(Type type) {
        return this.menuBarAdapter != null && this.menuBarAdapter.getType() == type && this.linear_layout_what2do_show_item_tab_menu.getVisibility() == View.VISIBLE;
    }

    private void showMenu(Type type) {
        this.linear_layout_what2do_show_item_tab_menu.setVisibility(View.VISIBLE);
        if (type == Type.LATEST) {
            linear_layout_tab_menu_1
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.home_menu_background_color_pressed));
        } else if (type == Type.CATEGORY) {
            linear_layout_tab_menu_2
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.home_menu_background_color_pressed));
        } else if (type == Type.AREA) {
            linear_layout_tab_menu_3
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.home_menu_background_color_pressed));
        }
        hideBottomBar();
    }

    private List<MenuBarItem> getListMenuData(Type type) {
        if (type == Type.LATEST) {
            return initLatestData();
        }
        if (type == Type.CATEGORY) {
            return initCategoryData();
        }
        return initAreaData();
    }

    private List<MenuBarItem> initLatestData() {
        List<MenuBarItem> menuBarItems = new ArrayList<>();

        menuBarItems = GlobalStaticData.initLastestData_What2do();

        int posSelected = this.selectedPositionMenu.get(Type.LATEST).intValue();
        if (posSelected < menuBarItems.size()) {
            menuBarItems.get(posSelected).setSelected(true);
        }

        return menuBarItems;
    }

    private List<MenuBarItem> initCategoryData() {
        return initLatestData();
    }

    private List<MenuBarItem> initAreaData() {
        return initLatestData();
    }

    private List<MenuBarItem> getListItem(Type type) {
        items = new ArrayList<>();
        if (type == Type.LATEST) {
            MenuBarItem item1 = new MenuBarItem(0, "Mới nhất", R.drawable.icon_tab_1_new, true);
            MenuBarItem item2 = new MenuBarItem(1, "Gần tôi", R.drawable.icon_tab_1_near, false);
            MenuBarItem item3 = new MenuBarItem(2, "Phổ biến", R.drawable.icon_tab_1_popular, false);
            MenuBarItem item4 = new MenuBarItem(3, "Du khách", R.drawable.icon_tab_1_tourist, false);
            MenuBarItem item5 = new MenuBarItem(4, "Ưu đãi E-card", R.drawable.icon_tab_1_ecard, false);
            MenuBarItem item6 = new MenuBarItem(5, "Đặt chỗ", R.drawable.icon_tab_1_book, false);
            MenuBarItem item7 = new MenuBarItem(6, "Ưu đãi thẻ", R.drawable.icon_tab_1_promote, false);
            MenuBarItem item8 = new MenuBarItem(7, "Đặt giao hàng", R.drawable.icon_tab_1_book, false);

            items.add(item1);
            items.add(item2);
            items.add(item3);
            items.add(item4);
            items.add(item5);
            items.add(item6);
            items.add(item7);
            items.add(item8);


        } else if (type == Type.CATEGORY) {
            MenuBarItem item1 = new MenuBarItem(0, "Danh mục", -1, true);
            MenuBarItem item2 = new MenuBarItem(1, "Gần tôi", R.drawable.icon_tab_1_near, false);
            MenuBarItem item3 = new MenuBarItem(2, "Phổ biến", R.drawable.icon_tab_1_popular, false);
            MenuBarItem item4 = new MenuBarItem(3, "Du khách", R.drawable.icon_tab_1_tourist, false);
            MenuBarItem item5 = new MenuBarItem(4, "Ưu đãi E-card", R.drawable.icon_tab_1_ecard, false);
            MenuBarItem item6 = new MenuBarItem(5, "Đặt chỗ", R.drawable.icon_tab_1_book, false);
            MenuBarItem item7 = new MenuBarItem(6, "Ưu đãi thẻ", R.drawable.icon_tab_1_promote, false);
            MenuBarItem item8 = new MenuBarItem(7, "Đặt giao hàng", R.drawable.icon_tab_1_book, false);

            items.add(item1);
            items.add(item2);
            items.add(item3);
            items.add(item4);
            items.add(item5);
            items.add(item6);
            items.add(item7);
            items.add(item8);
        }


        return items;
    }

}
