package com.fdsa.infamous.myfoody.ui.menu.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.adapter.ChooseDistrictAdapter;
import com.fdsa.infamous.myfoody.ui.util.bean.District;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */

public class ChooseProvinceView extends LinearLayout implements View.OnClickListener {

    Context context;

    LinearLayout linear_layout_choose_disctrict_parent_menu;
    LinearLayout linear_layout_choose_disctrict_item;
    TextView text_view_parent_district;
    LinearLayout linear_layout_change_district;
    ListView list_view_city;
    TextView text_view_close_change_district;
    List<District> districtList;


    ChooseDistrictAdapter adapter;



    public ChooseProvinceView(Context context) {
        super(context);
        init(context);

        districtList = getDisttrictList(0);
        adapter = new ChooseDistrictAdapter(context, districtList);

        list_view_city.setAdapter(adapter);
    }

    public ChooseProvinceView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ChooseProvinceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        this.context = context;
        linear_layout_choose_disctrict_parent_menu = (LinearLayout) findViewById(R.id.linear_layout_choose_disctrict_parent_menu);
        linear_layout_choose_disctrict_item = (LinearLayout) findViewById(R.id.linear_layout_choose_disctrict_item);
        linear_layout_change_district = (LinearLayout) findViewById(R.id.linear_layout_change_district);

        text_view_parent_district = (TextView) findViewById(R.id.text_view_parent_district);

        list_view_city = (ListView) findViewById(R.id.list_view_city);
        text_view_close_change_district = (TextView) findViewById(R.id.text_view_close_change_district);

        initEvent();
    }

    private List<District> getDisttrictList(int idProvince) {
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

    private void initEvent() {
        linear_layout_change_district.setOnClickListener(this);
        text_view_close_change_district.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
