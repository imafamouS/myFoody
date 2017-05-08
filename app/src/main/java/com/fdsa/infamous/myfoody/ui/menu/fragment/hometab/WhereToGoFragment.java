package com.fdsa.infamous.myfoody.ui.menu.fragment.hometab;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.DistrictBean;
import com.fdsa.infamous.myfoody.common.bean_F2.MenuBarItemBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ProvinceBean;
import com.fdsa.infamous.myfoody.common.bean_F2.RestaurantBean;
import com.fdsa.infamous.myfoody.common.myenum.Type;
import com.fdsa.infamous.myfoody.common.myinterface.IChooseDistrict;
import com.fdsa.infamous.myfoody.common.myinterface.IRestaurantItemClick;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.config.api.APIAction;
import com.fdsa.infamous.myfoody.ui.menu.activity.ChooseProvinceActivity;
import com.fdsa.infamous.myfoody.ui.menu.activity.RestaurantDetailActivity;
import com.fdsa.infamous.myfoody.ui.menu.activity.userprofile.LoginChooserActivity;
import com.fdsa.infamous.myfoody.ui.menu.adapter.ChooseDistrictAdapter;
import com.fdsa.infamous.myfoody.ui.menu.adapter.HomeWhereToGoAdapter;
import com.fdsa.infamous.myfoody.ui.menu.adapter.MenuBarAdapter;
import com.fdsa.infamous.myfoody.ui.menu.adapter.NodataAdapter;
import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;
import com.fdsa.infamous.myfoody.util.controller_F2.DistrictController;
import com.fdsa.infamous.myfoody.util.controller_F2.MenuBarItemController;
import com.fdsa.infamous.myfoody.util.controller_F2.RestaurantController;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class WhereToGoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, IRestaurantItemClick, IChooseDistrict {

    Context context;
    WhatToDoFragment whatToDoFragment;
    LayoutInflater inflater;

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

    View bottom_menu;


    /*AreaTabMenu*/
    LinearLayout linear_layout_choose_disctrict_parent_menu;
    LinearLayout linear_layout_choose_disctrict_item;
    TextView text_view_parent_district;
    LinearLayout linear_layout_change_district;
    ExpandableListView list_view_city;
    TextView text_view_close_change_district;
    ChooseDistrictAdapter chooseDistrictAdapter;
    ProvinceBean currentProvinceBean;

    MenuBarAdapter menuBarAdapter;
    Map<Type, List<?>> mapMenuBarItems;
    Map<Type, Integer> selectedPositionMenu;
    LinearLayout linear_layout_where2go_show_item_tab_menu;
    ListView list_view_where2go_tab_menu;

    boolean isNeedLoadCategory = true;
    boolean isNeedLoadArea = true;
    List<MenuBarItemBean> cacheListMenuCategory;
    List<DistrictBean> cacheListArea;

    /**
     * V2
     **/
    DistrictController districtController;
    MenuBarItemController menuBarItemController;

    HomeWhereToGoAdapter adapter;
    List<RestaurantBean> restaurantList;
    RestaurantController restaurantController;
    NodataAdapter nodataAdapter;

    //Hàm khởi tạo
    public WhereToGoFragment() {
        super();
        mapMenuBarItems = new HashMap<>();
        selectedPositionMenu = new HashMap<>();
        initDefaultPostionMenu();

    }

    //Hàm set tỉnh hiện tại
    public void setCurrentProvinceBean(ProvinceBean currentProvinceBean) {
        this.currentProvinceBean = currentProvinceBean;
    }

    //Hàm set fragment Ăn gì
    public void setWhatToDoFragment(WhatToDoFragment whereToGoFragment) {
        this.whatToDoFragment = whereToGoFragment;
    }

    //Hàm set vị trí ban đầu của menu item
    private void initDefaultPostionMenu() {
        selectedPositionMenu.put(Type.LATEST, 0);
        selectedPositionMenu.put(Type.CATEGORY, 0);
        selectedPositionMenu.put(Type.DISTRICT, -1);
        selectedPositionMenu.put(Type.STREET, 0);
    }

    //Hàm xử lí sự kiện khi fragment được tạo
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_tab_menu_where_to_go, container, false);

        initView(view, inflater);

        return view;
    }

    //Hàm khởi tạo view
    private void initView(View view, LayoutInflater inflater) {
        context = getActivity().getApplicationContext();
        menuBarItemController = new MenuBarItemController(context, APIAction.GET_CATEGORY_WHERE2DO);

        restaurantController = new RestaurantController(context);

        currentProvinceBean = GlobalStaticData.getCurrentProvinceBean();
        districtController = new DistrictController(context);
        nodataAdapter = new NodataAdapter(context);

        this.inflater = inflater;

        linear_layout_tab_menu_1 = (LinearLayout) view.findViewById(R.id.linear_layout_where2go_tab_menu_1);
        linear_layout_tab_menu_2 = (LinearLayout) view.findViewById(R.id.linear_layout_where2go_tab_menu_2);
        linear_layout_tab_menu_3 = (LinearLayout) view.findViewById(R.id.linear_layout_where2go_tab_menu_3);

        text_view_tab_menu_1 = (TextView) view.findViewById(R.id.text_view_where2go_tab_menu_1);
        text_view_tab_menu_2 = (TextView) view.findViewById(R.id.text_view_where2go_tab_menu_2);
        text_view_tab_menu_3 = (TextView) view.findViewById(R.id.text_view_where2go_tab_menu_3);

        linear_layout_where2go_show_item_tab_menu = (LinearLayout) view.findViewById(R.id.linear_layout_where2go_show_item_tab_menu);
        list_view_where2go_tab_menu = (ListView) view.findViewById(R.id.list_view_where2go_tab_menu);
        text_view_tab_menu_cancel = (TextView) view.findViewById(R.id.text_view_where2go_tab_menu_cancel);

        bottom_menu = getActivity().findViewById(R.id.bottom_menu);

        initViewMenuTabArea(view);

        swipe_refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_where2go);
        list_view_main_menu = (ListView) view.findViewById(R.id.list_view_main_menu);


        moreItemView = new MoreItemView(context);
        slideShowBanner = inflater.inflate(R.layout.banner_image_fragment, list_view_main_menu, false);

        list_view_main_menu.addHeaderView(slideShowBanner);
        list_view_main_menu.addHeaderView(moreItemView);


        try {
            restaurantList=restaurantController.getListRestaurant(currentProvinceBean.getId(),null,null,null,null);
            if(restaurantList!=null){
                adapter=new HomeWhereToGoAdapter(context,restaurantList);
                list_view_main_menu.setAdapter(adapter);
                adapter.setRestaurantItemClick(this);
            }else{
                list_view_main_menu.setAdapter(nodataAdapter);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        list_view_main_menu.setOnItemClickListener(this);



        linear_layout_tab_menu_1.setOnClickListener(this);
        linear_layout_tab_menu_2.setOnClickListener(this);
        linear_layout_tab_menu_3.setOnClickListener(this);
        text_view_tab_menu_cancel.setOnClickListener(this);
        list_view_where2go_tab_menu.setOnItemClickListener(this);
        swipe_refresh_layout.setOnRefreshListener(this);
    }

    //Hàm khởi tạo view của tab chọn quận huyện
    private void initViewMenuTabArea(View view) {
        linear_layout_choose_disctrict_parent_menu = (LinearLayout) view.findViewById(R.id.linear_layout_choose_disctrict_parent_menu);
        linear_layout_choose_disctrict_item = (LinearLayout) view.findViewById(R.id.linear_layout_choose_disctrict_item);
        linear_layout_change_district = (LinearLayout) view.findViewById(R.id.linear_layout_change_district);

        text_view_parent_district = (TextView) view.findViewById(R.id.text_view_parent_district);

        text_view_tab_menu_3.setText(currentProvinceBean.gettitle());

        list_view_city = (ExpandableListView) view.findViewById(R.id.list_view_city);
        text_view_close_change_district = (TextView) view.findViewById(R.id.text_view_close_change_district);


        text_view_close_change_district.setOnClickListener(this);
        text_view_parent_district.setOnClickListener(this);
        linear_layout_change_district.setOnClickListener(this);

    }
    int groupPosition;
    boolean isStreetShow=false;
    @Override
    public void onExpand(int groupPosition) {
        this.groupPosition=groupPosition;
        if (this.list_view_city.isGroupExpanded(groupPosition)) {
            isStreetShow=false;
            this.list_view_city.collapseGroup(groupPosition);
        } else {
            isStreetShow=true;
            if(groupPosition!=-1)
                this.list_view_city.expandGroup(groupPosition);
        }
    }

    @Override
    public void onSelectDistrict(int groupPosition) {
        resetStateTabMenu();
        hideMenuItem();
        hideStreetList();

        isStreetShow=false;
        this.selectedPositionMenu.put(Type.DISTRICT, groupPosition);
        this.selectedPositionMenu.put(Type.STREET, -1);

        reloadData(Type.DISTRICT,false);
        showBottomBar();
    }

    @Override
    public void onSelectStreet(int groupPosition, int chlidPosion) {
        resetStateTabMenu();
        hideMenuItem();
        hideStreetList();

        isStreetShow=true;
        this.selectedPositionMenu.put(Type.DISTRICT, groupPosition);
        this.selectedPositionMenu.put(Type.STREET, chlidPosion);

        reloadData(Type.STREET,false);
        showBottomBar();
    }

    //Hàm thực hiện reload lại data khi được swipe refresh
    @Override
    public void onRefresh() {

        loadRestaurant();
        if (swipe_refresh_layout.isRefreshing()) {
            swipe_refresh_layout.setRefreshing(false);
            if(restaurantList!=null && restaurantList.size()>0){
                Toast.makeText(context, "Đã load thành công các cửa hàng", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Đã có lỗi xảy ra, vui lòng xem lại kết nối :)", Toast.LENGTH_SHORT).show();
            }

        }
    }

    //Hàm nhận kết quả khi có sự thay đổi tỉnh thành
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppConfig.RESULT_CODE_CHANGE_PROVINCE) {
            if (data.getBooleanExtra("changed_province", false) == true) {
                currentProvinceBean = GlobalStaticData.getCurrentProvinceBean();

                whatToDoFragment.onChangeProvince();
                isNeedLoadArea = true;

                this.selectedPositionMenu.put(Type.DISTRICT,-1);
                this.selectedPositionMenu.put(Type.STREET,-1);
                this.groupPosition=-1;
                this.isStreetShow=false;

                reloadData(Type.DISTRICT, true);
            }
        }
    }

    //Hàm thực hiện khi tỉnh thành được thay đổi
    public void onChangeProvince() {
        isNeedLoadArea = true;
        isStreetShow=false;
        resetStateTabMenu();
        hideMenuItem();
        hideStreetList();
        this.selectedPositionMenu.put(Type.DISTRICT,-1);
        this.selectedPositionMenu.put(Type.STREET,-1);
        this.setCurrentProvinceBean(GlobalStaticData.getCurrentProvinceBean());

        reloadData(Type.DISTRICT,true);
    }

    @Override
    public void RestaurantItemCLick(int position) {
        if(!GlobalStaticData.isLogined()){
            Intent intent = new Intent(this.getActivity(), LoginChooserActivity.class);
            startActivityForResult(intent, AppConfig.REQUEST_CODE_LOGIN);
        }else{
            Intent intent=new Intent(this.getActivity(), RestaurantDetailActivity.class);


            intent.putExtra("resid",((RestaurantBean)this.adapter.getItem(position)).getId());
            getActivity().startActivity(intent);

        }
    }

    //Hàm thực hiện việc click item trên tab menu
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Type type = null;
        if (parent.getId() == R.id.list_view_where2go_tab_menu && menuBarAdapter != null) {
            if (menuBarAdapter.getType() == Type.LATEST && position != 0 && position != 2) {
                Toast.makeText(context, "You clicked " + menuBarAdapter.getItem(position).getTittle(), Toast.LENGTH_SHORT).show();
                return;
            }
            type = menuBarAdapter.getType();
            //reloadData();
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
            case R.id.linear_layout_where2go_tab_menu_1:
                //Nhấn vào Tab 1
                hideStreetList();
                showMenuItemBar(Type.LATEST);
                break;
            case R.id.linear_layout_where2go_tab_menu_2:
                //Nhấn vào Tab 2
                hideStreetList();
                showMenuItemBar(Type.CATEGORY);
                break;
            case R.id.linear_layout_where2go_tab_menu_3:
                //Nhấn vào Tab 3
                hideMenuItem();
                showMenuItemBar(Type.DISTRICT);
                break;
            case R.id.text_view_where2go_tab_menu_cancel:
            case R.id.text_view_close_change_district:
                //Nhấn vào nút Huỷ
                resetStateTabMenu();
                hideMenuItem();
                hideStreetList();
                showBottomBar();
                break;
            case R.id.text_view_parent_district:
                //Nhấn vào Tên tỉnh
                this.selectedPositionMenu.put(Type.DISTRICT, -1);
                this.selectedPositionMenu.put(Type.STREET, -1);
                setTextColorProvince(true);
                updateTitleMenu(Type.DISTRICT, false);
                resetStateTabMenu();
                hideMenuItem();
                hideStreetList();
                showBottomBar();
                loadRestaurant();
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
        loadRestaurant();
    }

    //Hàm lấy danh sách các nhà hàng từ CSDL
    public void loadRestaurant() {
        try {
            String id_province = currentProvinceBean.getId();

            String id_district = getIdTabSelected(Type.DISTRICT);

            String id_street=getIdTabSelected(Type.STREET);

            String id_wheretype = getIdTabSelected(Type.CATEGORY);

            String id_newest = getIdTabSelected(Type.LATEST);

            restaurantList = restaurantController.getListRestaurant(id_province, id_district, id_street, id_wheretype, id_newest);

            if (restaurantList == null || restaurantList.size() <= 0) {
                list_view_main_menu.setAdapter(nodataAdapter);
            } else {
                if (adapter == null && restaurantList!=null) {
                    adapter = new HomeWhereToGoAdapter(context, restaurantList);
                    adapter.setRestaurantItemClick(this);
                }

                adapter.setRestaurantList(restaurantList);
                adapter.notifyDataSetChanged();
                list_view_main_menu.setAdapter(adapter);

                list_view_main_menu.smoothScrollToPosition(0);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    //Hàm lấy id của các tab menu hiện tại để thực hiện việc lấy dữ liệu
    private String getIdTabSelected(Type type) {
        String id = "";
        int index = getIndexMenu(type);

        if (index == -1 && (type == Type.DISTRICT ||type==Type.STREET)) {
            return "";
        }
        List<?> list = (List) this.mapMenuBarItems.get(type);
        if(type!=Type.STREET){
            if (list == null || list.size() <= index || list.get(index) == null) {

                if (type == Type.LATEST) {
                    id = "moinhat";
                } else if (type == Type.CATEGORY) {
                    id = "l0";
                } else if(type!=Type.DISTRICT) {
                    id = currentProvinceBean.getId();
                }
                return id;
            }
            if (type != Type.DISTRICT) {
                id = ((MenuBarItemBean) list.get(index)).getId();
            } else if(type==Type.DISTRICT) {
                if(chooseDistrictAdapter!=null) {
                    int indexDistrict=this.selectedPositionMenu.get(Type.DISTRICT);
                    id=this.chooseDistrictAdapter.getGroup(indexDistrict).getId();
                }
            }
            return id;
        }else{
            if(chooseDistrictAdapter!=null){
                int indexDistrict=this.selectedPositionMenu.get(Type.DISTRICT);
                int indexStreet=this.selectedPositionMenu.get(Type.STREET);
                if(chooseDistrictAdapter!=null){
                    id=chooseDistrictAdapter.getChild(indexDistrict,indexStreet).getId();
                }
            }

            return id;
        }

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
            } else if (type == Type.DISTRICT) {
                //Type.DISTRICT
                if (getIndexMenu(type) != -1) {
                    //Chọn Quận
                    title = chooseDistrictAdapter.getGroup(getIndexMenu(type)).getTitle();
                    text_view_tab_menu_3.setText(title);
                    text_view_tab_menu_3.setTextColor(ContextCompat.getColor(context, color));
                }
                 else {
                    title = text_view_parent_district.getText().toString();
                    text_view_tab_menu_3.setText(title);
                    text_view_tab_menu_3.setTextColor(ContextCompat.getColor(context, R.color.home_new_filter_text));
                }
            } else if(type == Type.STREET){
                int indexDistrict=this.selectedPositionMenu.get(Type.DISTRICT);
                int indexStreet=this.selectedPositionMenu.get(Type.STREET);
                title = chooseDistrictAdapter.getChild(indexDistrict,indexStreet).getTitle();
                text_view_tab_menu_3.setText(title);
                text_view_tab_menu_3.setTextColor(ContextCompat.getColor(context, color));
            }
        } else {

            //Khi nhấn vào tên tỉnh hiện tại
            if (text_view_tab_menu_3 == null || text_view_tab_menu_3.getText().toString().trim().equals(currentProvinceBean.gettitle().trim())) {
                return;
            }

            text_view_tab_menu_3.setText(currentProvinceBean.gettitle());
            text_view_tab_menu_3.setTextColor(ContextCompat.getColor(context, R.color.home_new_filter_text));

        }

    }

    //Hàm ẩn bottom bar
    private void hideBottomBar() {
        if (bottom_menu == null)
            return;
        bottom_menu.setVisibility(View.GONE);
    }

    //Hàm hiện bottom bar
    private void showBottomBar() {
        if (bottom_menu == null)
            return;
        bottom_menu.setVisibility(View.VISIBLE);
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
            this.linear_layout_where2go_show_item_tab_menu.setVisibility(View.GONE);
        }
    }


    //Hàm hiện dữ liệu các tab menu
    private void showMenuItemBar(Type type) {
        resetStateTabMenu();

        if (isNeedClose(type)) {
            if (type != Type.DISTRICT) {
                hideMenuItem();
            } else {
                hideStreetList();
            }
            showBottomBar();
            return;
        }
        showLayoutMenuItem(type);
        try {
            if (type == Type.LATEST || type == Type.CATEGORY) {
                //Load dữ liệu thuộc loại mới nhất và danh mục

                this.mapMenuBarItems.put(type, (List<MenuBarItemBean>) getListMenuData(type));
                this.menuBarAdapter = new MenuBarAdapter(getActivity(), (List) this.mapMenuBarItems.get(type), type);
                this.menuBarAdapter.notifyDataSetChanged();
                this.list_view_where2go_tab_menu.setAdapter(this.menuBarAdapter);
            } else {
                //Load dữ liệu thuộc loại khu vực
                this.mapMenuBarItems.put(type, (List<DistrictBean>) getListMenuData(type));
                this.chooseDistrictAdapter = new ChooseDistrictAdapter(context, (List) this.mapMenuBarItems.get(type));
                this.chooseDistrictAdapter.setiChooseDistrict(this);
                this.text_view_parent_district.setText(currentProvinceBean.gettitle());
                this.chooseDistrictAdapter.notifyDataSetChanged();
                this.list_view_city.setAdapter(chooseDistrictAdapter);
                showStreetList();
                if(isStreetShow){
                    onExpand(this.groupPosition);
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        if (type != Type.DISTRICT) {
            return this.menuBarAdapter != null &&
                    this.menuBarAdapter.getType() == type &&
                    this.linear_layout_where2go_show_item_tab_menu.getVisibility() == View.VISIBLE;
        } else {
            return this.chooseDistrictAdapter != null &&
                    this.linear_layout_choose_disctrict_parent_menu.getVisibility() == View.VISIBLE;
        }

    }

    //Hàm hiện layout tab menu item
    private void showLayoutMenuItem(Type type) {
        if (type != Type.DISTRICT) {
            this.linear_layout_where2go_show_item_tab_menu.setVisibility(View.VISIBLE);
        } else {
            this.linear_layout_choose_disctrict_parent_menu.setVisibility(View.VISIBLE);
        }

        if (type == Type.LATEST) {
            linear_layout_tab_menu_1
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.home_menu_background_color_pressed));
        } else if (type == Type.CATEGORY) {
            linear_layout_tab_menu_2
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.home_menu_background_color_pressed));
        } else if (type == Type.DISTRICT) {
            linear_layout_tab_menu_3
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.home_menu_background_color_pressed));
        }

        hideBottomBar();
    }

    //Hàm lấy dữ liệu các tab menu
    private List<?> getListMenuData(Type type) throws ExecutionException, InterruptedException {
        if (type == Type.LATEST) {
            return initLatestData();
        }
        if (type == Type.CATEGORY) {
            return initCategoryData();
        }
        return initAreaData();
    }

    //Hàm khởi tạo giá trị của tab Mới nhất
    private List<MenuBarItemBean> initLatestData() {
        List<MenuBarItemBean> menuBarItemBeen;

        menuBarItemBeen = GlobalStaticData.initLastestData_Where2go();

        int posSelected = this.selectedPositionMenu.get(Type.LATEST).intValue();
        if (posSelected < menuBarItemBeen.size()) {
            menuBarItemBeen.get(posSelected).setSelected(true);
        }

        return menuBarItemBeen;
    }

    //Hàm khởi tạo giá trị của tab Danh mucc5
    private List<MenuBarItemBean> initCategoryData() {
        List<MenuBarItemBean> list = new ArrayList<>();

        try {
            if (isNeedLoadCategory) {
                cacheListMenuCategory = (List<MenuBarItemBean>) menuBarItemController.getListMenuBar_Category();
                list = cacheListMenuCategory;
                isNeedLoadCategory = false;
            } else {
                list = cacheListMenuCategory;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelected(false);
            }
            int posSelected = this.selectedPositionMenu.get(Type.CATEGORY).intValue();
            if (list != null && posSelected < list.size()) {
                list.get(posSelected).setSelected(true);
            }
        }
        return list;
    }


    //Hàm khởi tạo giá trị của tab Khu vực
    private List<DistrictBean> initAreaData() {
        List<DistrictBean> list = new ArrayList<>();

        try {
            if (isNeedLoadArea) {
                cacheListArea = (List<DistrictBean>) districtController.getListDistrict(currentProvinceBean.getId());
                list = cacheListArea;
                isNeedLoadArea = false;
            } else {
                list = cacheListArea;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //provinceController.executeSelect(AppConfig.REQUEST_CODE_LIST_AREA,currentProvinceBean.getIdProvince());
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelected(false);
                for (int j = 0; j < list.get(i).getListStreet().size(); j++) {
                    list.get(i).getListStreet().get(j).setSelected(false);
                }
            }
            int posSelected_District = this.selectedPositionMenu.get(Type.DISTRICT).intValue();
            int posSelected_Street = this.selectedPositionMenu.get(Type.STREET).intValue();

            if (posSelected_District < list.size() && posSelected_District != -1 && posSelected_Street == -1) {
                //Chọn Quận
                list.get(posSelected_District).setSelected(true);
                setTextColorProvince(false);
            } else if (posSelected_District < list.size() && posSelected_District != -1 && posSelected_Street != -1) {
                //Chọn Đường
                list.get(posSelected_District).getListStreet().get(posSelected_Street).setSelected(true);
                setTextColorProvince(false);
            } else if (posSelected_District < list.size() && posSelected_District == -1 && posSelected_Street == -1) {
                setTextColorProvince(true);
            }
        }
        return list;
    }

    //Hàm xử lí khi WhereToGoFragment xuất hiện
    public void onVisible() {
        if (restaurantList == null || restaurantList.size() <= 0) {
            loadRestaurant();
        }

        showBottomBar();
        if (this.linear_layout_choose_disctrict_parent_menu.getVisibility() == View.VISIBLE
                || this.linear_layout_where2go_show_item_tab_menu.getVisibility() == View.VISIBLE) {
            hideBottomBar();
        }
    }

}


   /* private void changeHeaderViewMain(){
        list_view_main_menu.removeHeaderView(slideShowBanner);
        list_view_main_menu.removeHeaderView(moreItemView);
        if (currentProvinceBean.getIdProvince().equals("vn1") //TPHCM
                || currentProvinceBean.getIdProvince().equals("vn2")
                || currentProvinceBean.getIdProvince().equals("vn3")
                ) {
            GlobalStaticData.TYPE_MOREITEM = MoreItemView.ITEM_DEFAULT;
            GlobalStaticData.TYPE_SLIDESHOW= SlideShowBannerFragment.TYPE_HAVE_ADS;
        } else {
            GlobalStaticData.TYPE_MOREITEM = MoreItemView.ITEM_TYPE_1;
            GlobalStaticData.TYPE_SLIDESHOW= SlideShowBannerFragment.TYPE_NO_ADS;
        }
        moreItemView = new MoreItemView(context);
        if( GlobalStaticData.TYPE_SLIDESHOW==SlideShowBannerFragment.TYPE_HAVE_ADS){
            slideShowBanner=null;
            slideShowBanner=inflater.inflate(R.layout.banner_image_fragment, list_view_main_menu, false);
            list_view_main_menu.addHeaderView(slideShowBanner);
        }
        list_view_main_menu.addHeaderView(moreItemView);
    }*/
