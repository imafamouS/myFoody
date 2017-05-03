package com.fdsa.infamous.myfoody;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.fdsa.infamous.myfoody.ui.menu.fragment.collectiontab.ColectionTabFragment;
import com.fdsa.infamous.myfoody.ui.menu.fragment.hometab.TabHomeFragment;
import com.fdsa.infamous.myfoody.ui.menu.fragment.notifytab.NotifyTabFragment;
import com.fdsa.infamous.myfoody.ui.menu.fragment.searchtab.SearchTabFragment;
import com.fdsa.infamous.myfoody.ui.menu.fragment.usertab.UserTabFragment;
import com.fdsa.infamous.myfoody.ui.menu.views.BottomNavigationViewEx;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;


/**
 * Created by FDSA on 3/18/2017.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationViewEx bottomNavigationMenu;
    Menu menu;
    TabHomeFragment homeTab;
    ColectionTabFragment collectionTab;
    NotifyTabFragment notifyTab;
    SearchTabFragment searchTab;
    UserTabFragment userTab;
    FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    public MainActivity() {
        homeTab = new TabHomeFragment();
        collectionTab = new ColectionTabFragment();
        notifyTab = new NotifyTabFragment();
        searchTab = new SearchTabFragment();
        userTab = new UserTabFragment();


    }

    /***
     * Hàm bắt sự kiện Activity dược khởi tạo (Khởi tạo layout cho activity)
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalStaticData.setCurrentProvinceBean(GlobalStaticData.getDefaultProvince());
        setContentView(R.layout.home_layout);

        initFragments();
        initBottombar();

        GlobalStaticData.setMainActivity(this);
    }

    /***
     * Hàm khởi tạo đối tượng quản lí các fragment và hiện fragment Home lên đầu tiên
     */
    private void initFragments() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contentContainer, homeTab);
        fragmentTransaction.add(R.id.contentContainer, collectionTab);
        fragmentTransaction.add(R.id.contentContainer, searchTab);
        fragmentTransaction.add(R.id.contentContainer, notifyTab);
        fragmentTransaction.add(R.id.contentContainer, userTab);

        fragmentTransaction.hide(homeTab);
        fragmentTransaction.hide(collectionTab);
        fragmentTransaction.hide(searchTab);
        fragmentTransaction.hide(notifyTab);
        fragmentTransaction.hide(userTab);

        fragmentTransaction.show(homeTab);

        fragmentTransaction.commit();
    }


    /***
     * Hàm khởi tạo các thuộc tính cho bottombar
     */
    private void initBottombar() {
        bottomNavigationMenu = (BottomNavigationViewEx) findViewById(R.id.bottom_menu);

        bottomNavigationMenu.setTextVisibility(false);      //Ẩn chữ
        bottomNavigationMenu.enableAnimation(false);        //Ẩn đi hiệu ứng chuyện tab
        bottomNavigationMenu.enableShiftingMode(false);     //Ẩn đi hiệu hứng chuyển tab
        bottomNavigationMenu.enableItemShiftingMode(false); //Ẩn đi hiệu hứng chuyển tab


        menu = bottomNavigationMenu.getMenu();

        setTab(0);

        bottomNavigationMenu.setOnNavigationItemSelectedListener(this);
        this.onNavigationItemSelected(bottomNavigationMenu.getMenu().getItem(0));
    }

    /***
     * Hàm xử lí sự kiện khi nhấn menu trên bottombar
     * Để hiện ra Fragment tương ứng
     *
     * @param item: Menu được nhấn
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.hide(homeTab);
        transaction.hide(collectionTab);
        transaction.hide(searchTab);
        transaction.hide(notifyTab);
        transaction.hide(userTab);

        switch (item.getItemId()) {
            case R.id.menu_bottom_bar_home:
                //change the icon
                setTab(0);
                transaction.show(homeTab);
                break;
            case R.id.menu_bottom_bar_gallery:
                //change the icon
                setTab(1);
                transaction.show(collectionTab);
                break;
            case R.id.menu_bottom_bar_search:
                //change the icon
                setTab(2);
                transaction.show(searchTab);
                break;
            case R.id.menu_bottom_bar_notify:
                //change the icon
                setTab(3);
                transaction.show(notifyTab);
                break;
            case R.id.menu_bottom_bar_user:
                //change the icon
                setTab(4);
                transaction.show(userTab);
                userTab.onTabVisible();
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
        return true;
    }

    /***
     * Hàm thay đổi trạng thái icon trên bottombar
     *
     * @param currentTabindex: vị trí của menu được nhấn
     */
    public void setTab(int currentTabindex) {

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
    }
}
