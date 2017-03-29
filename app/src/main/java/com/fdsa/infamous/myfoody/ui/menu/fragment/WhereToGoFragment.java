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

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.views.SlideShowBanner;
import com.fdsa.infamous.myfoody.ui.menu.views.TopMenuBar;

public class WhereToGoFragment extends Fragment implements View.OnClickListener {

    TopMenuBar mTopMenuBar;
    Context context;
    LinearLayout linear_layout_parent_tab_menu;
    LinearLayout linear_layout_tab_menu_1;
    LinearLayout linear_layout_tab_menu_2;
    LinearLayout linear_layout_tab_menu_3;
    SlideShowBanner slideShowBanner;

    public WhereToGoFragment(){
        super();
    }

   public void setContext(Context context){
       this.context=context;
   }

    @Override
    public void onClick(View v) {

    }

    public void setmTopMenuBar(TopMenuBar topMenuBar){
        this.mTopMenuBar=topMenuBar;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_tab_menu_where_to_go, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        linear_layout_parent_tab_menu=(LinearLayout)view.findViewById(R.id.linear_layout_parent_where2go_tab_menu);
        linear_layout_tab_menu_1=(LinearLayout)view.findViewById(R.id.linear_layout_where2go_tab_menu_1);
        linear_layout_tab_menu_2=(LinearLayout)view.findViewById(R.id.linear_layout_where2go_tab_menu_2);
        linear_layout_tab_menu_3=(LinearLayout)view.findViewById(R.id.linear_layout_where2go_tab_menu_3);
        //slideShowBanner=new SlideShowBanner(getActivity().getApplicationContext());
        //linear_layout_parent_tab_menu.addView(slideShowBanner);
    }
}
