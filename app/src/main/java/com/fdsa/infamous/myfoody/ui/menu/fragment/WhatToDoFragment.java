package com.fdsa.infamous.myfoody.ui.menu.fragment;

/**
 * Created by FDSA on 3/27/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.Global.GlobalStaticData;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.Activity.ChooseProvinceActivity;
import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;
import com.fdsa.infamous.myfoody.ui.util.Type;
import com.fdsa.infamous.myfoody.ui.util.adapter.ChooseDistrictAdapter;
import com.fdsa.infamous.myfoody.ui.util.adapter.MenuBarAdapter;
import com.fdsa.infamous.myfoody.ui.util.bean.District;
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
    FrameLayout frame_layout_layout_parent_what2do;
    TextView text_view_tab_menu_1;
    TextView text_view_tab_menu_2;
    TextView text_view_tab_menu_3;

    TextView text_view_tab_menu_cancel;

    ListView list_view_main_menu;
    MoreItemView moreItemView;
    View slideShowBanner;

    List<MenuBarItem> items;
    MenuBarAdapter menuBarAdapter;
    Map<Type, List<MenuBarItem>> mapMenuBarItems;
    Map<Type, Integer> selectedPositionMenu;

    /*AreaTabMenu*/
    LinearLayout linear_layout_choose_disctrict_parent_menu;
    LinearLayout linear_layout_choose_disctrict_item;
    TextView text_view_parent_district;
    LinearLayout linear_layout_change_district;
    ListView list_view_city;
    TextView text_view_close_change_district;
    List<District> districtList;
    ChooseDistrictAdapter chooseDistrictAdapter;

    LinearLayout linear_layout_what2do_show_item_tab_menu;
    ListView list_view_what2do_tab_menu;
    LayoutInflater inflater;

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
        View view = inflater.inflate(R.layout.home_tab_menu_what_to_do, container, false);

        initView(view, inflater);

        return view;
    }



    private void initView(View view, LayoutInflater inflater) {
        context = getActivity().getApplicationContext();
        this.inflater = inflater;

        frame_layout_layout_parent_what2do = (FrameLayout) view.findViewById(R.id.frame_layout_layout_parent_what2do);

        linear_layout_parent_tab_menu = (LinearLayout) view.findViewById(R.id.linear_layout_parent_what2do_tab_menu);
        linear_layout_tab_menu_1 = (LinearLayout) view.findViewById(R.id.linear_layout_what2do_tab_menu_1);
        linear_layout_tab_menu_2 = (LinearLayout) view.findViewById(R.id.linear_layout_what2do_tab_menu_2);
        linear_layout_tab_menu_3 = (LinearLayout) view.findViewById(R.id.linear_layout_what2do_tab_menu_3);

        text_view_tab_menu_1 = (TextView) view.findViewById(R.id.text_view_what2do_tab_menu_1);
        text_view_tab_menu_2 = (TextView) view.findViewById(R.id.text_view_what2do_tab_menu_2);
        text_view_tab_menu_3 = (TextView) view.findViewById(R.id.text_view_what2do_tab_menu_3);

        linear_layout_what2do_show_item_tab_menu = (LinearLayout) view.findViewById(R.id.linear_layout_what2do_show_item_tab_menu);
        list_view_what2do_tab_menu = (ListView) view.findViewById(R.id.list_view_what2do_tab_menu);
        text_view_tab_menu_cancel = (TextView) view.findViewById(R.id.text_view_what2do_tab_menu_cancel);

        initViewMenuTabArea(view);


        list_view_main_menu = (ListView) view.findViewById(R.id.list_view_main_menu);

        list_view_what2do_tab_menu.setOnItemClickListener(this);

        // moreItemView = new MoreItemView(context);
        //slideShowBanner = inflater.inflate(R.layout.banner_image_fragment, list_view_main_menu, false);

        //list_view_main_menu.addHeaderView(slideShowBanner);
        //list_view_main_menu.addHeaderView(moreItemView);

        //list_view_main_menu.setAdapter(new MenuBarAdapter(getActivity().getApplicationContext(), getListItem(Type.LATEST), Type.LATEST));

        initEvent();
    }

    private void initViewMenuTabArea(View view) {
        linear_layout_choose_disctrict_parent_menu = (LinearLayout) view.findViewById(R.id.linear_layout_choose_disctrict_parent_menu);
        linear_layout_choose_disctrict_item = (LinearLayout) view.findViewById(R.id.linear_layout_choose_disctrict_item);
        linear_layout_change_district = (LinearLayout) view.findViewById(R.id.linear_layout_change_district);

        text_view_parent_district = (TextView) view.findViewById(R.id.text_view_parent_district);

        list_view_city = (ListView) view.findViewById(R.id.list_view_city);
        text_view_close_change_district = (TextView) view.findViewById(R.id.text_view_close_change_district);

        //Default
        districtList = getDisttrictList(GlobalStaticData.getDefaultProvince().getIdProvince());
        chooseDistrictAdapter = new ChooseDistrictAdapter(context, districtList);

        list_view_city.setAdapter(chooseDistrictAdapter);
        list_view_city.setOnItemClickListener(this);
        text_view_close_change_district.setOnClickListener(this);
        text_view_parent_district.setOnClickListener(this);
        linear_layout_change_district.setOnClickListener(this);

    }

    private List<District> getDisttrictList(String idProvince) {
        List<District> items = new ArrayList<>();

        District item1 = new District("d1", "Quận 1", null);
        item1.setNumofStreet(10);

        District item2 = new District("d2", "Quận 2", null);
        item2.setNumofStreet(10);

        District item3 = new District("d3", "Quận 3", null);
        item3.setNumofStreet(10);

        District item4 = new District("d4", "Quận 4", null);
        item4.setNumofStreet(10);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        Log.d("DISTRICT", item1.getTittleDistrict());

        return items;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Type type;
        if (parent.getId() == R.id.list_view_what2do_tab_menu && menuBarAdapter != null) {
            if (menuBarAdapter.getType() == Type.LATEST && position != 0 && position != 2) {
                Toast.makeText(context, "You clicked " + menuBarAdapter.getItem(position).getTittle(), Toast.LENGTH_SHORT).show();
                return;
            }
            hideMenuItem();
            type = menuBarAdapter.getType();
            //reloadData();
        } else {
            //Item trong AREA được click
            hideStreetList();
            type = Type.AREA;
        }
        this.selectedPositionMenu.put(type, position);
        showBottomBar();
        updateTitleMenu(type);
    }

    private int getIndexMenu(Type type) {
        return selectedPositionMenu.get(type).intValue();
    }

    private void updateTitleMenu(Type type) {
        String title = "";
        int color = R.color.colorPrimary;
        //int id = menuBarAdapter.getItem(getIndexMenu(type)).getId();
        if (type == Type.LATEST) {
            title = menuBarAdapter.getItem(getIndexMenu(type)).getTittle();
            text_view_tab_menu_1.setText(title);
            text_view_tab_menu_1.setTextColor(ContextCompat.getColor(context, color));
            return;

        } else if (type == Type.CATEGORY) {
            title = menuBarAdapter.getItem(getIndexMenu(type)).getTittle();
            text_view_tab_menu_2.setText(title);
            text_view_tab_menu_2.setTextColor(ContextCompat.getColor(context, color));
            return;
        } else {
            //Type.AREA
            if (getIndexMenu(type) != -1) {
                title = chooseDistrictAdapter.getItem(getIndexMenu(type)).getTittleDistrict();
                text_view_tab_menu_3.setText(title);
                text_view_tab_menu_3.setTextColor(ContextCompat.getColor(context, color));
            } else {
                title = text_view_parent_district.getText().toString();
                text_view_tab_menu_3.setText(title);
                text_view_tab_menu_3.setTextColor(ContextCompat.getColor(context, R.color.home_new_filter_text));
            }

        }
    }

    private void initEvent() {
        linear_layout_tab_menu_1.setOnClickListener(this);
        linear_layout_tab_menu_2.setOnClickListener(this);
        linear_layout_tab_menu_3.setOnClickListener(this);
        text_view_tab_menu_cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_what2do_tab_menu_1:
                hideMenuItem();
                hideStreetList();
                showMenuItemBar(Type.LATEST);
                break;
            case R.id.linear_layout_what2do_tab_menu_2:
                hideMenuItem();
                hideStreetList();
                showMenuItemBar(Type.CATEGORY);
                break;
            case R.id.linear_layout_what2do_tab_menu_3:
                showStreetList();
                hideMenuItem();
                break;
            case R.id.text_view_what2do_tab_menu_cancel:
            case R.id.text_view_close_change_district:
                hideMenuItem();
                hideStreetList();
                showBottomBar();
                break;
            case R.id.text_view_parent_district:
                this.selectedPositionMenu.put(Type.AREA, -1);
                updateTitleMenu(Type.AREA);
                hideMenuItem();
                hideStreetList();
                showBottomBar();
                break;
            case R.id.linear_layout_change_district:
                GlobalStaticData.setCallFromFragment(GlobalStaticData.FROM_WHAT2DO);
                Intent intent=new Intent(this.getActivity(), ChooseProvinceActivity.class);
                getActivity().startActivity(intent);
                //Toast.makeText(context,"Đổi tỉnh thành",Toast.LENGTH_SHORT).show();
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

    private void hideStreetList() {
        if (chooseDistrictAdapter != null) {
            this.linear_layout_choose_disctrict_parent_menu.setVisibility(View.GONE);
        }
    }

    private void showStreetList() {
        if (chooseDistrictAdapter != null) {
            resetStateTabMenu();
            this.linear_layout_choose_disctrict_parent_menu.setVisibility(View.VISIBLE);
            showMenu(Type.AREA);
        }
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

}
