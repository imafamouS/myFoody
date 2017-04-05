package com.fdsa.infamous.myfoody.ui.menu.fragment;

/**
 * Created by FDSA on 3/27/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.Global.AppConfig;
import com.fdsa.infamous.myfoody.Global.GlobalStaticData;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.Activity.ChooseProvinceActivity;
import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;
import com.fdsa.infamous.myfoody.ui.util.Type;
import com.fdsa.infamous.myfoody.ui.util.adapter.ChooseDistrictAdapter;
import com.fdsa.infamous.myfoody.ui.util.adapter.MenuBarAdapter;
import com.fdsa.infamous.myfoody.ui.util.bean.District;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;
import com.fdsa.infamous.myfoody.ui.util.bean.Province;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WhatToDoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    Context context;

    LinearLayout linear_layout_tab_menu_1;
    LinearLayout linear_layout_tab_menu_2;
    LinearLayout linear_layout_tab_menu_3;

    TextView text_view_tab_menu_1;
    TextView text_view_tab_menu_2;
    TextView text_view_tab_menu_3;

    TextView text_view_tab_menu_cancel;

    SwipeRefreshLayout swipe_refresh_layout;
    ListView list_view_main_menu;
    MoreItemView moreItemView;
    View slideShowBanner;

    MenuBarAdapter menuBarAdapter;
    Map<Type, List<?>> mapMenuBarItems;
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

    public void setCurrentProvince(Province currentProvince) {
        this.currentProvince = currentProvince;
    }

    Province currentProvince;

    public WhatToDoFragment() {
        super();
        mapMenuBarItems = new HashMap<>();
        selectedPositionMenu = new HashMap<>();
        initDefaultPostionMenu();
        currentProvince=GlobalStaticData.getCurrentProvince();
    }
    //Hàm set vị trí ban đầu của menu item
    private void initDefaultPostionMenu() {
        selectedPositionMenu.put(Type.LATEST, 0);
        selectedPositionMenu.put(Type.CATEGORY, 0);
        selectedPositionMenu.put(Type.AREA, -1);
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


        swipe_refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_what2do);
        list_view_main_menu = (ListView) view.findViewById(R.id.list_view_main_menu);


        //moreItemView = new MoreItemView(context);
        slideShowBanner = inflater.inflate(R.layout.banner_image_fragment, list_view_main_menu, false);

        list_view_main_menu.addHeaderView(slideShowBanner);
        //list_view_main_menu.addHeaderView(moreItemView);

        list_view_main_menu.setAdapter(new MenuBarAdapter(getActivity().getApplicationContext(), GlobalStaticData.initLastestData_Where2go(), Type.LATEST));


        linear_layout_tab_menu_1.setOnClickListener(this);
        linear_layout_tab_menu_2.setOnClickListener(this);
        linear_layout_tab_menu_3.setOnClickListener(this);
        text_view_tab_menu_cancel.setOnClickListener(this);
        list_view_what2do_tab_menu.setOnItemClickListener(this);
        swipe_refresh_layout.setOnRefreshListener(this);
    }
    //Hàm thực hiện khi tỉnh thành được thay đổi
    public void onChangeProvince(){
        this.setCurrentProvince(GlobalStaticData.getCurrentProvince());
        updateTitleMenu(Type.AREA,true);
    }

    private void initViewMenuTabArea(View view) {
        linear_layout_choose_disctrict_parent_menu = (LinearLayout) view.findViewById(R.id.linear_layout_choose_disctrict_parent_menu);
        linear_layout_choose_disctrict_item = (LinearLayout) view.findViewById(R.id.linear_layout_choose_disctrict_item);
        linear_layout_change_district = (LinearLayout) view.findViewById(R.id.linear_layout_change_district);

        text_view_parent_district = (TextView) view.findViewById(R.id.text_view_parent_district);

        text_view_tab_menu_3.setText(currentProvince.getTitleProvince());

        list_view_city = (ListView) view.findViewById(R.id.list_view_city);
        text_view_close_change_district = (TextView) view.findViewById(R.id.text_view_close_change_district);

        //Default

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

    //Hàm thực hiện reload lại data khi được swipe refresh
    @Override
    public void onRefresh() {

        //loadRestaurent();
        if (swipe_refresh_layout.isRefreshing()) {
            swipe_refresh_layout.setRefreshing(false);
        }
    }

    //Hàm nhận kết quả khi có sự thay đổi tỉnh thành
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppConfig.RESULT_CODE_CHANGE_PROVINCE) {

            if (data.getBooleanExtra("changed_province", false) == true) {
                currentProvince=GlobalStaticData.getCurrentProvince();
                reloadData(Type.AREA, true);

            }

        }
    }

    //Hàm thực hiện việc click item trên tab menu
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Type type;
        if (parent.getId() == R.id.list_view_what2do_tab_menu && menuBarAdapter != null) {
            if (menuBarAdapter.getType() == Type.LATEST && position != 0 && position != 2) {
                Toast.makeText(context, "You clicked " + menuBarAdapter.getItem(position).getTittle(), Toast.LENGTH_SHORT).show();
                return;
            }
            type = menuBarAdapter.getType();
            //reloadData();
        } else {
            //Item trong AREA được click
            type = Type.AREA;
        }

        resetStateTabMenu();
        hideMenuItem();
        hideStreetList();
        this.selectedPositionMenu.put(type, position);
        showBottomBar();
        reloadData(type, false);
    }

    //Hàm thực hiện sự kiển onCLick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_what2do_tab_menu_1:
                //Nhấn vào Tab 1
                hideStreetList();
                showMenuItemBar(Type.LATEST);
                break;
            case R.id.linear_layout_what2do_tab_menu_2:
                //Nhấn vào Tab 2
                hideStreetList();
                showMenuItemBar(Type.CATEGORY);
                break;
            case R.id.linear_layout_what2do_tab_menu_3:
                //Nhấn vào Tab 3
                hideMenuItem();
                showMenuItemBar(Type.AREA);
                break;
            case R.id.text_view_what2do_tab_menu_cancel:
            case R.id.text_view_close_change_district:
                //Nhấn vào nút Huỷ
                resetStateTabMenu();
                hideMenuItem();
                hideStreetList();
                showBottomBar();
                break;
            case R.id.text_view_parent_district:
                //Nhấn vào Tên tỉnh
                this.selectedPositionMenu.put(Type.AREA, -1);
                setTextColorProvince(true);
                updateTitleMenu(Type.AREA, false);
                resetStateTabMenu();
                hideMenuItem();
                hideStreetList();
                showBottomBar();
                break;
            case R.id.linear_layout_change_district:
                //Nhấn vào nút Đổi tỉnh thành
                resetStateTabMenu();
                hideMenuItem();
                hideStreetList();
                showBottomBar();
                GlobalStaticData.setCallFromFragment(GlobalStaticData.FROM_WHAT2DO);
                Intent intent = new Intent(this.getActivity(), ChooseProvinceActivity.class);
                startActivityForResult(intent, AppConfig.REQUEST_CODE_CHANGE_PROVINCE);
                break;
            default:
                break;
        }
    }

    //Hàm tải lại các tiệm ăn
    private void reloadData(Type type, boolean isChangeProvince) {
        updateTitleMenu(type, isChangeProvince);
        //loadRestaurent();
    }

    //Hàm set color cho text_view tỉnh hiện tại
    private void setTextColorProvince(boolean isSelected) {
        if (isSelected) {
            text_view_parent_district.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            text_view_parent_district.setTextColor(Color.BLACK);
        }
    }

    //Hàm lấy vị trí hiện tại của các item tab menu
    private int getIndexMenu(Type type) {
        return selectedPositionMenu.get(type).intValue();
    }

    //Hàm cập nhật text và textcolor cho các tab menu
    private void updateTitleMenu(Type type, boolean isChangeProvince) {
        if (isChangeProvince == false) {
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
        } else {

            //Khi nhấn vào tên tỉnh hiện tại
            this.selectedPositionMenu.put(Type.AREA, -1);

            if(text_view_tab_menu_3==null||text_view_tab_menu_3.getText().toString().trim().equals(currentProvince.getTitleProvince().trim())){
                return;
            }

            text_view_tab_menu_3.setText(currentProvince.getTitleProvince());
            text_view_tab_menu_3.setTextColor(ContextCompat.getColor(context, R.color.home_new_filter_text));

        }

    }

    //Hàm ẩn bottom bar
    private void hideBottomBar() {
        getActivity().findViewById(R.id.bottom_menu).setVisibility(View.GONE);
    }

    //Hàm hiện bottom bar
    private void showBottomBar() {
        getActivity().findViewById(R.id.bottom_menu).setVisibility(View.VISIBLE);
    }

    //Hàm ẩn layout các huyện, đường
    private void hideStreetList() {
        if (chooseDistrictAdapter != null) {
            this.linear_layout_choose_disctrict_parent_menu.setVisibility(View.GONE);

        }
    }

    //Hàm hiện layout các huyện, đường
    private void showStreetList() {
        if (chooseDistrictAdapter != null) {
            this.linear_layout_choose_disctrict_parent_menu.setVisibility(View.VISIBLE);
        }
    }

    //Hàm hiện layout menu item
    private void hideMenuItem() {
        if (this.menuBarAdapter != null) {
            this.linear_layout_what2do_show_item_tab_menu.setVisibility(View.GONE);
        }
    }


    //Hàm hiện dữ liệu các tab menu
    private void showMenuItemBar(Type type) {
        resetStateTabMenu();

        if (isNeedClose(type)) {
            if (type != Type.AREA) {
                hideMenuItem();
            } else {
                hideStreetList();
            }
            showBottomBar();
            return;
        }
        showMenu(type);

        if (type == Type.LATEST || type == Type.CATEGORY) {
            //Load dữ liệu thuộc loại mới nhất và danh mục
            this.mapMenuBarItems.put(type, (List<MenuBarItem>) getListMenuData(type));
            this.menuBarAdapter = new MenuBarAdapter(getActivity(), (List) this.mapMenuBarItems.get(type), type);
            this.menuBarAdapter.notifyDataSetChanged();
            this.list_view_what2do_tab_menu.setAdapter(this.menuBarAdapter);
        } else {
            //Load dữ liệu thuộc loại khu vực
            this.mapMenuBarItems.put(type, (List<District>) getListMenuData(type));
            this.chooseDistrictAdapter = new ChooseDistrictAdapter(context, (List) this.mapMenuBarItems.get(type));
            this.text_view_parent_district.setText(currentProvince.getTitleProvince());
            this.chooseDistrictAdapter.notifyDataSetChanged();
            this.list_view_city.setAdapter(chooseDistrictAdapter);
            showStreetList();
        }


    }

    //Hàm trả về background ban đầu của các tab menu
    private void resetStateTabMenu() {
        this.linear_layout_tab_menu_1.setBackgroundResource(R.drawable.border);
        this.linear_layout_tab_menu_2.setBackgroundResource(R.drawable.border);
        this.linear_layout_tab_menu_3.setBackgroundResource(R.drawable.border);
    }

    //Hàm kiểm tra xem có cần đóng layout hiện item của các tabmenu
    private boolean isNeedClose(Type type) {
        if (type != Type.AREA) {
            return this.menuBarAdapter != null &&
                    this.menuBarAdapter.getType() == type &&
                    this.linear_layout_what2do_show_item_tab_menu.getVisibility() == View.VISIBLE;
        } else {
            return this.chooseDistrictAdapter != null &&
                    this.linear_layout_choose_disctrict_parent_menu.getVisibility() == View.VISIBLE;
        }

    }

    //Hàm hiện layout tab menu item
    private void showMenu(Type type) {
        if (type != Type.AREA) {
            this.linear_layout_what2do_show_item_tab_menu.setVisibility(View.VISIBLE);
        } else {
            this.linear_layout_choose_disctrict_parent_menu.setVisibility(View.VISIBLE);
        }

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

    //Hàm lấy dữ liệu các tab menu
    private List<?> getListMenuData(Type type) {
        if (type == Type.LATEST) {
            return initLatestData();
        }
        if (type == Type.CATEGORY) {
            return initCategoryData();
        }
        return initAreaData();
    }

    //Hàm khởi tạo giá trị của tab Mới nhất
    private List<MenuBarItem> initLatestData() {
        List<MenuBarItem> menuBarItems = new ArrayList<>();

        menuBarItems = GlobalStaticData.initLastestData_What2do();

        int posSelected = this.selectedPositionMenu.get(Type.LATEST).intValue();
        if (posSelected < menuBarItems.size()) {
            menuBarItems.get(posSelected).setSelected(true);
        }

        return menuBarItems;
    }

    //Hàm khởi tạo giá trị của tab Danh mucc5
    private List<MenuBarItem> initCategoryData() {
        return initLatestData();
    }

    //Hàm khởi tạo giá trị của tab Khu vực
    private List<District> initAreaData() {
        List<District> list = new ArrayList<>();

        list = getDisttrictList("TH.HCM");

        int posSelected = this.selectedPositionMenu.get(Type.AREA).intValue();
        if (posSelected < list.size() && posSelected != -1) {
            list.get(posSelected).setSelected(true);
            setTextColorProvince(false);
        } else {
            setTextColorProvince(true);
        }

        return list;

    }

}
