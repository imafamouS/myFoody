package com.fdsa.infamous.myfoody.ui.menu.fragment;

/**
 * Created by FDSA on 3/27/2017.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;
import com.fdsa.infamous.myfoody.ui.util.Type;
import com.fdsa.infamous.myfoody.ui.util.adapter.MenuBarAdapter;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class WhereToGoFragment extends Fragment implements View.OnClickListener {

    TopMenuBarFragment mTopMenuBarFragment;
    Context context;
    LinearLayout linear_layout_parent_tab_menu;
    LinearLayout linear_layout_tab_menu_1;
    LinearLayout linear_layout_tab_menu_2;
    LinearLayout linear_layout_tab_menu_3;

    ListView list_view_main_menu;
    MoreItemView moreItemView;
    View slideShowBanner;

    List<MenuBarItem> items;

    public WhereToGoFragment(){
        super();
    }

    @Override
    public void onClick(View v) {

    }

    public void setmTopMenuBarFragment(TopMenuBarFragment topMenuBarFragment) {
        this.mTopMenuBarFragment = topMenuBarFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_tab_menu_where_to_go, container, false);
        initView(view, inflater);
        return view;
    }

    private void initView(View view, LayoutInflater inflater) {
        this.context = getActivity().getApplicationContext();
        linear_layout_parent_tab_menu=(LinearLayout)view.findViewById(R.id.linear_layout_parent_where2go_tab_menu);
        linear_layout_tab_menu_1=(LinearLayout)view.findViewById(R.id.linear_layout_where2go_tab_menu_1);
        linear_layout_tab_menu_2=(LinearLayout)view.findViewById(R.id.linear_layout_where2go_tab_menu_2);
        linear_layout_tab_menu_3=(LinearLayout)view.findViewById(R.id.linear_layout_where2go_tab_menu_3);

        list_view_main_menu = (ListView) view.findViewById(R.id.list_view_main_menu);

        //moreItemView = new MoreItemView(context);
        slideShowBanner = inflater.inflate(R.layout.banner_image_fragment, list_view_main_menu, false);

        list_view_main_menu.addHeaderView(slideShowBanner);
        // list_view_main_menu.addHeaderView(moreItemView);

        list_view_main_menu.setAdapter(new MenuBarAdapter(getActivity().getApplicationContext(), getListItem(Type.LATEST), Type.LATEST));
    }

    private List<MenuBarItem> getListItem(Type type) {
        items = new ArrayList<>();
        if (type == Type.LATEST) {
            MenuBarItem item1 = new MenuBarItem(0, "Mới nhất", R.drawable.icon_tab_1_new, false);
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
