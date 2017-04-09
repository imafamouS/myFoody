package com.fdsa.infamous.myfoody.global;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;
import com.fdsa.infamous.myfoody.ui.util.bean.MoreItem;
import com.fdsa.infamous.myfoody.ui.util.bean.Province;
import com.fdsa.infamous.myfoody.ui.util.myenum.MoreItemCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 3/26/2017.
 */

public class GlobalStaticData {

    public final static int FROM_WHAT2DO = 0;
    public final static int FROM_WHERE2GO = 1;
    public static int TYPE_MOREITEM = MoreItemView.ITEM_DEFAULT;
    static Province currentProvince;
    static int callFromFragment;

    public static Province getDefaultProvince(){
        return new Province("vn1","TP.HCM",null);
    }

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

    public static List<Integer> getImageSlideShow(int type) {
        List<Integer> mResources = new ArrayList<>();

        mResources.add(R.drawable.img_slider_1);
        mResources.add(R.drawable.img_slider_2);

        return mResources;
    }

    public static List<MenuBarItem> initLastestData_Where2go() {
        List<MenuBarItem> items = new ArrayList<>();

        MenuBarItem item1 = new MenuBarItem("moinhat", "Mới nhất", "icon_tab_1_new", false);
        MenuBarItem item2 = new MenuBarItem("gantoi", "Gần tôi", "icon_tab_1_near", false);
        MenuBarItem item3 = new MenuBarItem("phobien", "Phổ biến", "icon_tab_1_popular", false);
        MenuBarItem item4 = new MenuBarItem("dukhach", "Du khách", "icon_tab_1_tourist", false);
        MenuBarItem item5 = new MenuBarItem("ecard", "Ưu đãi E-card", "icon_tab_1_ecard", false);
        MenuBarItem item6 = new MenuBarItem("datcho", "Đặt chỗ", "icon_tab_1_book", false);
        MenuBarItem item7 = new MenuBarItem("uudaithe", "Ưu đãi thẻ", "icon_tab_1_promote", false);
        MenuBarItem item8 = new MenuBarItem("giaohang", "Đặt giao hàng", "icon_tab_1_delivery", false);


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

    public static List<MenuBarItem> initLastestData_What2do() {
        List<MenuBarItem> items = new ArrayList<>();


        MenuBarItem item1 = new MenuBarItem("moinhat", "Mới nhất", "icon_tab_1_new", false);
        MenuBarItem item2 = new MenuBarItem("gantoi", "Gần tôi", "icon_tab_1_near", false);
        MenuBarItem item3 = new MenuBarItem("phobien", "Xem nhiều", "icon_tab_1_popular", false);
        MenuBarItem item4 = new MenuBarItem("dukhach", "Du khách", "icon_tab_1_tourist", false);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        return items;


    }

    public static Province getCurrentProvince() {
        return currentProvince;
    }

    public static void setCurrentProvince(Province currentProvince) {
        GlobalStaticData.currentProvince = currentProvince;
    }

    public static int getCallFromFragment() {
        return callFromFragment;
    }

    public static void setCallFromFragment(int callFromFragment) {
        GlobalStaticData.callFromFragment = callFromFragment;
    }
}
