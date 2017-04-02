package com.fdsa.infamous.myfoody;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.ui.menu.views.BottomNavigationViewEx;
import com.fdsa.infamous.myfoody.ui.util.Type;
import com.fdsa.infamous.myfoody.ui.util.adapter.MenuBarAdapter;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by FDSA on 3/18/2017.
 */

public class MainActivity extends AppCompatActivity {
    BottomNavigationViewEx bottomNavigationMenu;
    Menu menu;
    LinearLayout linear_layout_top_menu_change_domain;
    LinearLayout linear_layout_show_item_tab_menu;
    LinearLayout linear_layout_tab_menu_2;
    ListView listView;
    MenuBarAdapter menuBarAdapter;
    List<MenuBarItem> items;
    ImageView image_view_top_menu_plus_menu;

    TabLayout tab_layout_top_menu;
    TextView text_view_where2go_top_menu;
    TextView text_view_what2do_top_menu;


    RelativeLayout relative_main_layout;
    ListView test_list_view;
    ViewGroup slideShowBanner;
    List<Integer> mResources;
    LinearLayout test_aa;

    public void intitImage() {
        this.mResources = new ArrayList<>();

        mResources.add(R.drawable.icon_foody);
        mResources.add(R.drawable.icon_bottom_menu_user_selected);
    }

    LinearLayout linear_layout_what2do_show_item_tab_menu;
    ListView list_view_what2do_tab_menu;
    LinearLayout linear_layout_tab_menu_1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.home_layout);
        setContentView(R.layout.home_layout);
       /* linear_layout_what2do_show_item_tab_menu=(LinearLayout)findViewById(R.id.linear_layout_what2do_show_item_tab_menu);
        list_view_what2do_tab_menu=(ListView) findViewById(R.id.list_view_what2do_tab_menu);
       // linear_layout_show_item_tab_menu=(LinearLayout)findViewById(R.id.linear_layout_what2do_show_item_tab_menu);
        linear_layout_tab_menu_1=(LinearLayout)findViewById(R.id.linear_layout_what2do_tab_menu_1);
        linear_layout_tab_menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuBarAdapter = new MenuBarAdapter(getApplicationContext(), getListItem(Type.LASTEST),Type.LASTEST);
                list_view_what2do_tab_menu.setAdapter(menuBarAdapter);
                linear_layout_tab_menu_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.home_menu_background_color_pressed));
                linear_layout_what2do_show_item_tab_menu.setVisibility(View.VISIBLE);
            }
        });*/
        /*relative_main_layout=(RelativeLayout)findViewById(R.id.relative_main_layout);
        test_list_view=(ListView) findViewById(R.id.test_list_view);

        View fragment=(ViewGroup)getLayoutInflater().inflate(R.layout.test2,test_list_view,false);
        test_aa=new LinearLayout(getApplicationContext());
        //slideShowBanner.initView();
        //slideShowBanner.view_pager_slide_show.setAdapter(new MySlideShowBannerAdapter(getApplicationContext(),mResources));

        test_aa.addView(fragment);
        test_list_view.addHeaderView(test_aa);
        test_list_view.setAdapter(new MenuBarAdapter(getApplicationContext(),getListItem(Type.LASTEST),Type.LASTEST));*/

        //  tab_layout_top_menu.addTab(tab_layout_top_menu.newTab());
        // tab_layout_top_menu.addTab(tab_layout_top_menu.newTab());

      /* createTabView(getApplicationContext());

        tab_layout_top_menu.getTabAt(0).setCustomView(text_view_where2go_top_menu);
        tab_layout_top_menu.getTabAt(1).setCustomView(text_view_what2do_top_menu);*/


        // bottomSheetBehavior= BottomSheetBehavior.from(findViewById(R.id.layout_parent_plus_menu));

       /* image_view_top_menu_plus_menu = (ImageView) findViewById(R.id.image_view_top_menu_plus_menu);

        image_view_top_menu_plus_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");
            }
        });



        linear_layout_show_item_tab_menu = (LinearLayout) findViewById(R.id.linear_layout_show_item_tab_menu);
        listView = (ListView) findViewById(R.id.list_view_what2do_tab_menu);
*/

        /*linear_layout_tab_menu_1=(LinearLayout)findViewById(R.id.linear_layout_what2do_tab_menu_1);
        linear_layout_tab_menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuBarAdapter = new MenuBarAdapter(getApplicationContext(), getListItem(Type.LASTEST),Type.LASTEST);
                listView.setAdapter(menuBarAdapter);
                linear_layout_tab_menu_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.home_menu_background_color_pressed));
                linear_layout_show_item_tab_menu.setVisibility(View.VISIBLE);
                bottomNavigationMenu.setVisibility(View.GONE);
            }
        });

        linear_layout_tab_menu_2=(LinearLayout)findViewById(R.id.linear_layout_what2do_tab_menu_2);
        linear_layout_tab_menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuBarAdapter = new MenuBarAdapter(getApplicationContext(), getListItem(Type.CATEGORY),Type.CATEGORY);
                listView.setAdapter(menuBarAdapter);
                linear_layout_tab_menu_1.setBackgroundColor(0);
                linear_layout_tab_menu_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.home_menu_background_color_pressed));
                linear_layout_show_item_tab_menu.setVisibility(View.VISIBLE);
                bottomNavigationMenu.setVisibility(View.GONE);
            }
        });*/


        /*bottomNavigationMenu = (BottomNavigationViewEx) findViewById(R.id.bottom_menu);

        bottomNavigationMenu.setTextVisibility(false);
        bottomNavigationMenu.enableAnimation(false);
        bottomNavigationMenu.enableShiftingMode(false);
        bottomNavigationMenu.enableItemShiftingMode(false);
        bottomNavigationMenu.setCurrentItem(0);

        menu = bottomNavigationMenu.getMenu();
        setTab(0);
        bottomNavigationMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_bottom_bar_home:
                                //change the icon
                                setTab(0);

                                break;
                            case R.id.menu_bottom_bar_gallery:
                                //change the icon
                                setTab(1);
                                break;
                            case R.id.menu_bottom_bar_search:
                                //change the icon
                                setTab(2);
                                break;
                            case R.id.menu_bottom_bar_notify:
                                //change the icon
                                setTab(3);
                                break;
                            case R.id.menu_bottom_bar_user:
                                //change the icon
                                setTab(4);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });*/

        /*text_view_what_to_do = (TextView) findViewById(R.id.text_view_what2do_top_menu);
        text_text_where_to_go = (TextView) findViewById(R.id.text_view_where2go_top_menu);
        linear_layout_top_menu_change_domain = (LinearLayout) findViewById(R.id.linear_layout_top_menu_change_domain);

        linear_layout_top_menu_change_domain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DomainActivity.class);
                startActivity(intent);
            }
        });
        text_text_where_to_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set(0);
            }
        });

        text_view_what_to_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set(1);
            }
        });*/
    }

   /* TextView text_view_what_to_do;
    TextView text_text_where_to_go;

    private void set(int currentPageIndex) {
        if (currentPageIndex == 0) {
            this.text_view_what_to_do.setBackgroundResource(R.drawable.home_item_left);
            this.text_view_what_to_do.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));

            this.text_text_where_to_go.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.dark_grey));
            this.text_text_where_to_go.setBackgroundColor(0);
            return;
        }
        this.text_text_where_to_go.setBackgroundResource(R.drawable.home_item_right);
        this.text_text_where_to_go.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
        this.text_view_what_to_do.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.dark_grey));
        this.text_view_what_to_do.setBackgroundColor(0);
    }*/

    /*private void setTab(int currentTabindex) {

        switch (currentTabindex) {
            case 0:
                menu.getItem(0).setIcon(R.drawable.icon_bottom_menu_home_selected);
                menu.getItem(1).setIcon(R.drawable.icon_bottom_menu_gallery);
                menu.getItem(2).setIcon(R.drawable.icon_bottom_menu_search);
                menu.getItem(3).setIcon(R.drawable.icon_bottom_menu_notify);
                menu.getItem(4).setIcon(R.drawable.icon_bottom_menu_user);
                break;
            case 1:
                menu.getItem(0).setIcon(R.drawable.icon_bottom_menu_home);
                menu.getItem(1).setIcon(R.drawable.icon_bottom_menu_gallery_selected);
                menu.getItem(2).setIcon(R.drawable.icon_bottom_menu_search);
                menu.getItem(3).setIcon(R.drawable.icon_bottom_menu_notify);
                menu.getItem(4).setIcon(R.drawable.icon_bottom_menu_user);
                break;
            case 2:
                menu.getItem(0).setIcon(R.drawable.icon_bottom_menu_home);
                menu.getItem(1).setIcon(R.drawable.icon_bottom_menu_gallery);
                menu.getItem(2).setIcon(R.drawable.icon_bottom_menu_search_selected);
                menu.getItem(3).setIcon(R.drawable.icon_bottom_menu_notify);
                menu.getItem(4).setIcon(R.drawable.icon_bottom_menu_user);
                break;
            case 3:
                menu.getItem(0).setIcon(R.drawable.icon_bottom_menu_home);
                menu.getItem(1).setIcon(R.drawable.icon_bottom_menu_gallery);
                menu.getItem(2).setIcon(R.drawable.icon_bottom_menu_search);
                menu.getItem(3).setIcon(R.drawable.icon_bottom_menu_notify_selected);
                menu.getItem(4).setIcon(R.drawable.icon_bottom_menu_user);
                break;
            case 4:
                menu.getItem(0).setIcon(R.drawable.icon_bottom_menu_home);
                menu.getItem(1).setIcon(R.drawable.icon_bottom_menu_gallery);
                menu.getItem(2).setIcon(R.drawable.icon_bottom_menu_search);
                menu.getItem(3).setIcon(R.drawable.icon_bottom_menu_notify);
                menu.getItem(4).setIcon(R.drawable.icon_bottom_menu_user_selected);
                break;
            default:
                break;

        }
    }*/
    int currentSelectedPosition=0;
    private List<MenuBarItem> getListItem(Type type) {
        items = new ArrayList<>();
        if (type == Type.LATEST) {
            MenuBarItem item1 = new MenuBarItem(0,"Mới nhất", R.drawable.icon_tab_1_new, false);
            MenuBarItem item2 = new MenuBarItem(1,"Gần tôi", R.drawable.icon_tab_1_near, false);
            MenuBarItem item3 = new MenuBarItem(2,"Phổ biến", R.drawable.icon_tab_1_popular, false);
            MenuBarItem item4 = new MenuBarItem(3,"Du khách", R.drawable.icon_tab_1_tourist, false);
            MenuBarItem item5 = new MenuBarItem(4,"Ưu đãi E-card", R.drawable.icon_tab_1_ecard, false);
            MenuBarItem item6 = new MenuBarItem(5,"Đặt chỗ", R.drawable.icon_tab_1_book, false);
            MenuBarItem item7 = new MenuBarItem(6,"Ưu đãi thẻ", R.drawable.icon_tab_1_promote, false);
            MenuBarItem item8 = new MenuBarItem(7,"Đặt giao hàng", R.drawable.icon_tab_1_book, false);

            items.add(item1);
            items.add(item2);
            items.add(item3);
            items.add(item4);
            items.add(item5);
            items.add(item6);
            items.add(item7);
            items.add(item8);

            if(currentSelectedPosition<items.size()){
                items.get(currentSelectedPosition).setSelected(true);
            }
        }else if(type==Type.CATEGORY){
            MenuBarItem item1=new MenuBarItem(0,"Danh mục",-1,true);
            MenuBarItem item2 = new MenuBarItem(1,"Gần tôi", R.drawable.icon_tab_1_near, false);
            MenuBarItem item3 = new MenuBarItem(2,"Phổ biến", R.drawable.icon_tab_1_popular, false);
            MenuBarItem item4 = new MenuBarItem(3,"Du khách", R.drawable.icon_tab_1_tourist, false);
            MenuBarItem item5 = new MenuBarItem(4,"Ưu đãi E-card", R.drawable.icon_tab_1_ecard, false);
            MenuBarItem item6 = new MenuBarItem(5,"Đặt chỗ", R.drawable.icon_tab_1_book, false);
            MenuBarItem item7 = new MenuBarItem(6,"Ưu đãi thẻ", R.drawable.icon_tab_1_promote, false);
            MenuBarItem item8 = new MenuBarItem(7,"Đặt giao hàng", R.drawable.icon_tab_1_book, false);

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
