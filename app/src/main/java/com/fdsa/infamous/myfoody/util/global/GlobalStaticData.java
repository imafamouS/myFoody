package com.fdsa.infamous.myfoody.util.global;

import android.graphics.Bitmap;

import com.fdsa.infamous.myfoody.MainActivity;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F1.MoreItem;
import com.fdsa.infamous.myfoody.common.bean_F2.MenuBarItemBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ProvinceBean;
import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;
import com.fdsa.infamous.myfoody.common.myenum.MoreItemCode;
import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 3/26/2017.
 */

public class GlobalStaticData {

    public final static int FROM_WHAT2DO = 0;

    public final static int FROM_WHERE2GO = 1;

    public static int TYPE_MOREITEM = MoreItemView.ITEM_DEFAULT;

    public static ProvinceBean currentProvinceBean;

    public static int callFromFragment;

    public static  boolean LOGINFLAG=false;

    public static UserBean currentUser;

    public static boolean isLogined(){
        return LOGINFLAG;
    }

    public static MainActivity mainActivity;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        GlobalStaticData.mainActivity = mainActivity;
    }

    public static UserBean getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserBean currentUser) {
        GlobalStaticData.currentUser = currentUser;
    }

    //Hàm get tỉnh hiện tại
    public static ProvinceBean getCurrentProvinceBean() {
        return currentProvinceBean;
    }

    //Hàm set tỉnh hiện tại
    public static void setCurrentProvinceBean(ProvinceBean currentProvinceBean) {
        GlobalStaticData.currentProvinceBean = currentProvinceBean;
    }

    //Hàm get giá trị để xem fragment được gọi từ frament nào
    public static int getCallFromFragment() {
        return callFromFragment;
    }

    //Hàm set giá trị để xem fragment được gọi từ frament nào
    public static void setCallFromFragment(int callFromFragment) {
        GlobalStaticData.callFromFragment = callFromFragment;
    }

    //Hàm get tỉnh mặc định (TPHCM)
    public static ProvinceBean getDefaultProvince(){
        return new ProvinceBean("vn1","TP.HCM",null);
    }

    //Hàm get các item của MoreItemView
    public static List<MoreItem> getListMoreItem(int type) {
        List<MoreItem> list = new ArrayList<>();

        if(type == MoreItemView.ITEM_DEFAULT){
            list.add(new MoreItem("Gần tôi", MoreItemCode.NEARBY));
            list.add(new MoreItem("Coupon", MoreItemCode.COUPON));
            list.add(new MoreItem("Đặt chỗ ưu đãi", MoreItemCode.BOOK));
            list.add(new MoreItem("Đặt giao hàng", MoreItemCode.DELIVERY));
            list.add(new MoreItem("E-card", MoreItemCode.ECARD));

            list.add(new MoreItem("Game & Fun", MoreItemCode.GAME_FUN));
            list.add(new MoreItem("Bình luận", MoreItemCode.REVIEW));
            list.add(new MoreItem("Blogs", MoreItemCode.BLOGS));
            list.add(new MoreItem("Top thành viên", MoreItemCode.TOPMEMBER));
            list.add(new MoreItem("Video", MoreItemCode.VIDEO));
        } else {
            list.add(new MoreItem("Gần tôi", MoreItemCode.NEARBY));
            list.add(new MoreItem("Bình luận", MoreItemCode.REVIEW));
            list.add(new MoreItem("Blogs", MoreItemCode.BLOGS));
            list.add(new MoreItem("Top thành viên", MoreItemCode.TOPMEMBER));
        }

        return list;
    }

    //Hàm get các ảnh của SlideShowBanner
    public static List<Integer> getImageSlideShow(int type) {
        List<Integer> mResources = new ArrayList<>();

        mResources.add(R.drawable.img_slider_1);
        mResources.add(R.drawable.img_slider_2);

        return mResources;
    }

    //Hàm khởi tạo danh sách của tab mới nhất của fragment ở đâu
    public static List<MenuBarItemBean> initLastestData_Where2go() {
        List<MenuBarItemBean> items = new ArrayList<>();

        MenuBarItemBean item1 = new MenuBarItemBean("moinhat", "Mới nhất", "icon_tab_1_new", false);
        MenuBarItemBean item2 = new MenuBarItemBean("gantoi", "Gần tôi", "icon_tab_1_near", false);
        MenuBarItemBean item3 = new MenuBarItemBean("phobien", "Phổ biến", "icon_tab_1_popular", false);
        MenuBarItemBean item4 = new MenuBarItemBean("dukhach", "Du khách", "icon_tab_1_tourist", false);
        MenuBarItemBean item5 = new MenuBarItemBean("ecard", "Ưu đãi E-card", "icon_tab_1_ecard", false);
        MenuBarItemBean item6 = new MenuBarItemBean("datcho", "Đặt chỗ", "icon_tab_1_book", false);
        MenuBarItemBean item7 = new MenuBarItemBean("uudaithe", "Ưu đãi thẻ", "icon_tab_1_promote", false);
        MenuBarItemBean item8 = new MenuBarItemBean("giaohang", "Đặt giao hàng", "icon_tab_1_delivery", false);


        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);

        return items;
    }

    //Hàm khởi tạo danh sách của tab mới nhất của fragment ăn gì
    public static List<MenuBarItemBean> initLastestData_What2do() {
        List<MenuBarItemBean> items = new ArrayList<>();


        MenuBarItemBean item1 = new MenuBarItemBean("moinhat", "Mới nhất", "icon_tab_1_new", false);
        MenuBarItemBean item2 = new MenuBarItemBean("gantoi", "Gần tôi", "icon_tab_1_near", false);
        MenuBarItemBean item3 = new MenuBarItemBean("phobien", "Xem nhiều", "icon_tab_1_popular", false);
        MenuBarItemBean item4 = new MenuBarItemBean("dukhach", "Du khách", "icon_tab_1_tourist", false);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        return items;


    }

    public static List<MenuBarItemBean> initMarryStatusData(){
        List<MenuBarItemBean> items = new ArrayList<>();


        MenuBarItemBean item1 = new MenuBarItemBean("1", "Độc thân", null, false);
        MenuBarItemBean item2 = new MenuBarItemBean("2", "Đã cưới", null, false);
        MenuBarItemBean item3 = new MenuBarItemBean("3", "Phức tạp", null, false);
        MenuBarItemBean item4 = new MenuBarItemBean("4", "Đang hẹn hò", null, false);
        MenuBarItemBean item5 = new MenuBarItemBean("5", "Đã đính hôn", null, false);
        MenuBarItemBean item6 = new MenuBarItemBean("6", "Quan hệ mở", null, false);
        MenuBarItemBean item7 = new MenuBarItemBean("7", "Goá", null, false);
        MenuBarItemBean item8 = new MenuBarItemBean("8", "Ly dị", null, false);
        MenuBarItemBean item9 = new MenuBarItemBean("9", "Ly thân", null, false);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        items.add(item9);

        return items;
    }
    public static List<MenuBarItemBean> initGenderData(){
        List<MenuBarItemBean> items = new ArrayList<>();


        MenuBarItemBean item1 = new MenuBarItemBean("1", "Nam", null, false);
        MenuBarItemBean item2 = new MenuBarItemBean("2", "Nữ", null, false);

        items.add(item1);
        items.add(item2);

        return items;
    }
    public static Bitmap BACKGROUND_LOGIN;
}
