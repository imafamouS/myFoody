package com.fdsa.infamous.myfoody.ui.menu.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.Global.GlobalStaticData;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.adapter.ChooseProvinceAdapter;
import com.fdsa.infamous.myfoody.ui.util.bean.Province;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by FDSA on 4/3/2017.
 */

public class ChooseProvinceActivity extends AppCompatActivity implements View.OnClickListener ,AdapterView.OnItemClickListener{


    RelativeLayout choose_provine_top_bar;
    LinearLayout back_button_choose_provine;
    TextView choose_provine_top_bar_title;
    TextView text_view_done_choose_provine;
    ListView list_view_choose_province;

    EditText edit_text_search_choose_province;
    ImageView image_view_delete_choose_province;
    LinearLayout linear_layout_auto_detect_location;
    LinearLayout linear_layout_action_change_country;

    int callFromFragment;
    Province currentProvince;
    ChooseProvinceAdapter adapter;

    public ChooseProvinceActivity() {
        this.callFromFragment = GlobalStaticData.getCallFromFragment();

        currentProvince = this.callFromFragment == 0 ?
                GlobalStaticData.getCurrentProvince_What2do() : GlobalStaticData.getCurrentProvince_Where2go();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_province_activity_layout);

        init();
    }

    private void init() {

        choose_provine_top_bar = (RelativeLayout) findViewById(R.id.choose_provine_top_bar);
        choose_provine_top_bar_title = (TextView) findViewById(R.id.choose_provine_top_bar_title);

        back_button_choose_provine = (LinearLayout) findViewById(R.id.back_button_choose_provine);
        text_view_done_choose_provine = (TextView) findViewById(R.id.text_view_done_choose_provine);

        list_view_choose_province = (ListView) findViewById(R.id.list_view_choose_province);

        View headerListView = LayoutInflater.from(this).inflate(R.layout.choose_province_header_activity_layout, null);
        list_view_choose_province.addHeaderView(headerListView);

        edit_text_search_choose_province = (EditText) headerListView.findViewById(R.id.edit_text_search_choose_province);
        image_view_delete_choose_province = (ImageView) headerListView.findViewById(R.id.image_view_delete_choose_province);
        linear_layout_auto_detect_location = (LinearLayout) headerListView.findViewById(R.id.linear_layout_auto_detect_location);
        linear_layout_action_change_country = (LinearLayout) headerListView.findViewById(R.id.linear_layout_action_change_country);

        adapter=new ChooseProvinceAdapter(getApplicationContext(),getProvinceList("VIETNAM"),currentProvince);

        list_view_choose_province.setAdapter(adapter);
        /*Event*/

        back_button_choose_provine.setOnClickListener(this);
        text_view_done_choose_provine.setOnClickListener(this);

        edit_text_search_choose_province.addTextChangedListener(this.searchWatcher);

        linear_layout_auto_detect_location.setOnClickListener(this);
        linear_layout_action_change_country.setOnClickListener(this);

        list_view_choose_province.setOnItemClickListener(this);
    }

    private List<Province> getProvinceList(String idCountry){
        List<Province> provinces=new ArrayList<>();

        Province item1=new Province("0","TP.HCM",null);
        Province item2=new Province("1","Hà Nội",null);
        Province item3=new Province("2","Đà Nẵng",null);
        Province item4=new Province("3","Hải Phòng",null);
        Province item5=new Province("4","TP.HCM",null);
        Province item6=new Province("5","Hà Nội",null);
        Province item7=new Province("6","Đà Nẵng",null);
        Province item8=new Province("7","Hải Phòng",null);
        Province item9=new Province("8","TP.HCM",null);
        Province item10=new Province("9","Hà Nội",null);
        Province item11=new Province("10","Đà Nẵng",null);
        Province item12=new Province("11","Hải Phòng",null);

        Province item13=new Province("12","TP.HCM",null);
        Province item14=new Province("13","Hà Nội",null);
        Province item15=new Province("14","Đà Nẵng",null);
        Province item16=new Province("15","Hải Phòng",null);

        provinces.add(item1);
        provinces.add(item2);
        provinces.add(item3);
        provinces.add(item4);
        provinces.add(item5);
        provinces.add(item6);
        provinces.add(item7);
        provinces.add(item8);
        provinces.add(item9);
        provinces.add(item10);
        provinces.add(item11);
        provinces.add(item12);
        provinces.add(item13);
        provinces.add(item14);
        provinces.add(item15);
        provinces.add(item16);

        return provinces;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public TextWatcher searchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_choose_provine:
                break;
            case R.id.text_view_done_choose_provine:
                break;
        }
    }
}