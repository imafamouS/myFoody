package com.fdsa.infamous.myfoody.Global;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;
import com.fdsa.infamous.myfoody.ui.util.bean.MoreItem;
import com.fdsa.infamous.myfoody.ui.util.bean.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 3/26/2017.
 */

public class GlobalStaticData {

    public static Province getDefaultProvince(){
        return new Province("0","TP.HCM",null);
    }

    public static List<MoreItem> getListMoreItem(int type) {
        List<MoreItem> list = new ArrayList<>();

        if(type == MoreItemView.ITEM_DEFAULT){
            list.add(new MoreItem("Gần tôi", MoreItem.MoreItemCode.NEARBY));
            list.add(new MoreItem("Coupon", MoreItem.MoreItemCode.COUPON));
            list.add(new MoreItem("Đặt chỗ ưu đãi", MoreItem.MoreItemCode.BOOK));
            list.add(new MoreItem("Đặt giao hàng", MoreItem.MoreItemCode.DELIVERY));
            list.add(new MoreItem("E-card", MoreItem.MoreItemCode.ECARD));

            list.add(new MoreItem("Game & Fun", MoreItem.MoreItemCode.GAME_FUN));
            list.add(new MoreItem("Bình luận", MoreItem.MoreItemCode.REVIEW));
            list.add(new MoreItem("Blogs", MoreItem.MoreItemCode.BLOGS));
            list.add(new MoreItem("Top thành viên", MoreItem.MoreItemCode.TOPMEMBER));
            list.add(new MoreItem("Video", MoreItem.MoreItemCode.VIDEO));
        }

        return list;
    }

    public static List<Integer> getDefaultImageSlideShow() {
        List<Integer> mResources = new ArrayList<>();

        mResources.add(R.drawable.icon_foody);
        mResources.add(R.drawable.icon_bottom_menu_user_selected);

        return mResources;
    }

    public static List<MenuBarItem> initLastestData_Where2go() {
        List<MenuBarItem> items = new ArrayList<>();

        MenuBarItem item1 = new MenuBarItem(0, "Mới nhất", R.drawable.icon_tab_1_new, false);
        MenuBarItem item2 = new MenuBarItem(1, "Gần tôi", R.drawable.icon_tab_1_near, false);
        MenuBarItem item3 = new MenuBarItem(2, "Phổ biến", R.drawable.icon_tab_1_popular, true);
        MenuBarItem item4 = new MenuBarItem(3, "Du khách", R.drawable.icon_tab_1_tourist, false);
        MenuBarItem item5 = new MenuBarItem(4, "Ưu đãi E-card", R.drawable.icon_tab_1_ecard, false);
        MenuBarItem item6 = new MenuBarItem(5, "Đặt chỗ", R.drawable.icon_tab_1_book, false);
        MenuBarItem item7 = new MenuBarItem(6, "Ưu đãi thẻ", R.drawable.icon_tab_1_promote, false);
        MenuBarItem item8 = new MenuBarItem(7, "Đặt giao hàng", R.drawable.icon_tab_1_delivery, false);

        MenuBarItem item9 = new MenuBarItem(8, "Mới nhất", R.drawable.icon_tab_1_new, false);
        MenuBarItem item10 = new MenuBarItem(9, "Gần tôi", R.drawable.icon_tab_1_near, false);
        MenuBarItem item11 = new MenuBarItem(10, "Phổ biến", R.drawable.icon_tab_1_popular, false);
        MenuBarItem item12 = new MenuBarItem(11, "Du khách", R.drawable.icon_tab_1_tourist, false);
        MenuBarItem item13 = new MenuBarItem(12, "Ưu đãi E-card", R.drawable.icon_tab_1_ecard, false);
        MenuBarItem item14 = new MenuBarItem(13, "Đặt chỗ", R.drawable.icon_tab_1_book, false);
        MenuBarItem item15 = new MenuBarItem(14, "Ưu đãi thẻ", R.drawable.icon_tab_1_promote, false);
        MenuBarItem item16 = new MenuBarItem(15, "Đặt giao hàng", R.drawable.icon_tab_1_delivery, false);

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
       /* List<MenuBarItem> items = new ArrayList<>();

        MenuBarItem item1 = new MenuBarItem(0, "Mới nhất", R.drawable.icon_tab_1_new, false);
        MenuBarItem item2 = new MenuBarItem(1, "Gần tôi", R.drawable.icon_tab_1_near, false);
        MenuBarItem item3 = new MenuBarItem(2, "Xem nhiều", R.drawable.icon_tab_1_popular, false);
        MenuBarItem item4 = new MenuBarItem(3, "Du khách", R.drawable.icon_tab_1_tourist, false);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        return items;*/

        List<MenuBarItem> items = new ArrayList<>();

        MenuBarItem item1 = new MenuBarItem(0, "Mới nhất", R.drawable.icon_tab_1_new, false);
        MenuBarItem item2 = new MenuBarItem(1, "Gần tôi", R.drawable.icon_tab_1_near, false);
        MenuBarItem item3 = new MenuBarItem(2, "Phổ biến", R.drawable.icon_tab_1_popular, false);
        MenuBarItem item4 = new MenuBarItem(3, "Du khách", R.drawable.icon_tab_1_tourist, false);
        MenuBarItem item5 = new MenuBarItem(4, "Ưu đãi E-card", R.drawable.icon_tab_1_ecard, false);
        MenuBarItem item6 = new MenuBarItem(5, "Đặt chỗ", R.drawable.icon_tab_1_book, false);
        MenuBarItem item7 = new MenuBarItem(6, "Ưu đãi thẻ", R.drawable.icon_tab_1_promote, false);
        MenuBarItem item8 = new MenuBarItem(7, "Đặt giao hàng", R.drawable.icon_tab_1_delivery, false);


        MenuBarItem item9 = new MenuBarItem(8, "Mới nhất", R.drawable.icon_tab_1_new, false);
        MenuBarItem item10 = new MenuBarItem(9, "Gần tôi", R.drawable.icon_tab_1_near, false);
        MenuBarItem item11 = new MenuBarItem(10, "Phổ biến", R.drawable.icon_tab_1_popular, false);
        MenuBarItem item12 = new MenuBarItem(11, "Du khách", R.drawable.icon_tab_1_tourist, false);
        MenuBarItem item13 = new MenuBarItem(12, "Ưu đãi E-card", R.drawable.icon_tab_1_ecard, false);
        MenuBarItem item14 = new MenuBarItem(13, "Đặt chỗ", R.drawable.icon_tab_1_book, false);
        MenuBarItem item15 = new MenuBarItem(14, "Ưu đãi thẻ", R.drawable.icon_tab_1_promote, false);
        MenuBarItem item16 = new MenuBarItem(15, "Đặt giao hàng", R.drawable.icon_tab_1_delivery, false);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);

        items.add(item9);
        items.add(item10);
        items.add(item11);
        items.add(item12);
        items.add(item13);
        items.add(item14);
        items.add(item15);
        items.add(item16);

        return items;
    }

    static Province currentProvince_What2do;
    static Province currentProvince_Where2go;

    public static Province getCurrentProvince_Where2go() {
        return currentProvince_Where2go;
    }

    public static void setCurrentProvince_Where2go(Province currentProvince_Where2go) {
        GlobalStaticData.currentProvince_Where2go = currentProvince_Where2go;
    }

    public static Province getCurrentProvince_What2do() {
        return currentProvince_What2do;
    }

    public static void setCurrentProvince_What2do(Province currentProvince_What2do) {
        GlobalStaticData.currentProvince_What2do = currentProvince_What2do;
    }

    static int callFromFragment;

    public final static int FROM_WHAT2DO = 0;
    public final static int FROM_WHERE2GO = 1;

    public static int getCallFromFragment() {
        return callFromFragment;
    }

    public static void setCallFromFragment(int callFromFragment) {
        GlobalStaticData.callFromFragment = callFromFragment;
    }
}
