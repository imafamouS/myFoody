package com.fdsa.infamous.myfoody.ui.menu.views;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.DomainActivity;
import com.fdsa.infamous.myfoody.R;

/**
 * Created by FDSA on 3/26/2017.
 */

public class TopMenuBar extends LinearLayout implements View.OnClickListener {
    Context context;
    FragmentManager fragmentManager;
    LinearLayout linear_layout_parent_top_menu;

  /*  TextView text_view_where2go_top_menu;
    TextView text_view_what2do_top_menu;*/
    TabLayout tab_layout_top_menu;
    TextView text_view_where2go_top_menu;
    TextView text_view_what2do_top_menu;

    ImageView image_view_top_menu_plus_menu;

    LinearLayout linear_layout_top_menu_change_domain;
    ImageView image_view_top_menu_change_domain;

    ViewPager viewpage;
    View view;

    IOnTopMenuBarChange onPageChange;
    public interface IOnTopMenuBarChange{
        void OnTopMenuBarChange(int index);
    }
    public void setOnPageChange(IOnTopMenuBarChange onPageChange){
        this.onPageChange=onPageChange;
    }

    public TopMenuBar(Context context, FragmentManager fm) {
        super(context);
        this.context = context;
        this.fragmentManager = fm;
        initView();
        initEvent();
    }

    private void initView() {
        this.view = inflate(getContext(), R.layout.home_top_menu, this);
        viewpage = new ViewPager(context);

        //linear_layout_parent_top_menu = (LinearLayout) findViewById(R.id.linear_layout_parent_top_menu);
        tab_layout_top_menu=(TabLayout)findViewById(R.id.tab_layout_top_menu);

       /* text_view_where2go_top_menu = (TextView) findViewById(R.id.text_view_where2go_top_menu);
        text_view_what2do_top_menu = (TextView) findViewById(R.id.text_view_what2do_top_menu);*/

        tab_layout_top_menu.addTab(tab_layout_top_menu.newTab());
        tab_layout_top_menu.addTab(tab_layout_top_menu.newTab());

        tab_layout_top_menu.getTabAt(0).setCustomView((TextView)findViewById(R.id.text_view_where2go_top_menu));
        tab_layout_top_menu.getTabAt(1).setCustomView((TextView)findViewById(R.id.text_view_what2do_top_menu));

        image_view_top_menu_plus_menu = (ImageView) findViewById(R.id.image_view_top_menu_plus_menu);

        linear_layout_top_menu_change_domain = (LinearLayout) findViewById(R.id.linear_layout_top_menu_change_domain);
        image_view_top_menu_change_domain = (ImageView) findViewById(R.id.image_view_top_menu_change_domain);

    }

    private void initEvent() {
        text_view_where2go_top_menu.setOnClickListener(this);
        text_view_what2do_top_menu.setOnClickListener(this);

        image_view_top_menu_plus_menu.setOnClickListener(this);

        linear_layout_top_menu_change_domain.setOnClickListener(this);
    }

    public void setNotify(int currentPageIndex) {
        if (currentPageIndex == 0) {
            this.text_view_what2do_top_menu.setBackgroundResource(R.drawable.home_item_left);

            this.text_view_what2do_top_menu.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

            this.text_view_where2go_top_menu.setTextColor(ContextCompat.getColor(context, R.color.dark_grey));
            this.text_view_where2go_top_menu.setBackgroundColor(0);
            return;
        }
        this.text_view_where2go_top_menu.setBackgroundResource(R.drawable.home_item_right);
        this.text_view_where2go_top_menu.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        this.text_view_what2do_top_menu.setTextColor(ContextCompat.getColor(context, R.color.dark_grey));
        this.text_view_what2do_top_menu.setBackgroundColor(0);
    }

    @Override
    public void onClick(View v) {
        int viewID = v.getId();

        switch (viewID) {
            case R.id.text_view_where2go_top_menu:
                onWhere2Go();
                break;
            case R.id.text_view_what2do_top_menu:
                onWhat2Do();
                break;
            case R.id.image_view_top_menu_plus_menu:
                onPlusMenu();
                break;
            case R.id.linear_layout_top_menu_change_domain:
                onChangeDomain();
                break;
            default:
                break;
        }
    }

    private void onWhere2Go() {
        onPageChange.OnTopMenuBarChange(0);
    }

    private void onWhat2Do() {
        onPageChange.OnTopMenuBarChange(1);
    }

    private void onPlusMenu() {
        new CustomBottomSheetDialogFragment().show(fragmentManager, "Dialog");
    }

    private void onChangeDomain() {
        Intent intent = new Intent(context, DomainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
