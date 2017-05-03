package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.content.Intent;
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

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.ProvinceBean;
import com.fdsa.infamous.myfoody.common.myinterface.IOnSetDefaultProvince;
import com.fdsa.infamous.myfoody.ui.menu.adapter.ChooseProvinceAdapter;
import com.fdsa.infamous.myfoody.util.controller_F2.ProvinceController;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/3/2017.
 */

public class ChooseProvinceActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, IOnSetDefaultProvince {


    private RelativeLayout choose_provine_top_bar;
    private LinearLayout back_button_choose_provine;
    private TextView choose_provine_top_bar_title;
    private TextView text_view_done_choose_provine;
    private ListView list_view_choose_province;

    private EditText edit_text_search_choose_province;
    private ImageView image_view_delete_choose_province;
    /**
     * TextWatcher khi nhập vào ô tìm kiếm
     */
    public TextWatcher searchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (edit_text_search_choose_province.getText().toString().length() == 0) {
                image_view_delete_choose_province.setVisibility(View.GONE);
                return;
            }

            image_view_delete_choose_province.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private LinearLayout linear_layout_auto_detect_location;
    private LinearLayout linear_layout_action_change_country;
    private int callFromFragment;
    private ProvinceBean currentProvinceBean;
    private ChooseProvinceAdapter adapter;
    private List<ProvinceBean> provinceBeanList;
    private ProvinceController provinceController;


    /**
     * Hàm khởi tạo
     */
    public ChooseProvinceActivity() {
        this.callFromFragment = GlobalStaticData.getCallFromFragment();

        currentProvinceBean = GlobalStaticData.getCurrentProvinceBean();

    }

    /**
     * Sự kiện khi activi được tạo
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_province_activity_layout);

        try {
            init();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Khởi tạo các view và sự kiện các view
     */
    private void init() throws ExecutionException, InterruptedException {

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


        provinceController=new ProvinceController(getApplicationContext(),"vietnam",false);
        provinceBeanList =provinceController.getListProvince();

        adapter=new ChooseProvinceAdapter(getApplicationContext(), provinceBeanList, currentProvinceBean,this);

        list_view_choose_province.setAdapter(adapter);
        /*Event*/

        back_button_choose_provine.setOnClickListener(this);
        text_view_done_choose_provine.setOnClickListener(this);

        edit_text_search_choose_province.addTextChangedListener(this.searchWatcher);
        image_view_delete_choose_province.setOnClickListener(this);

        linear_layout_auto_detect_location.setOnClickListener(this);
        linear_layout_action_change_country.setOnClickListener(this);

        list_view_choose_province.setOnItemClickListener(this);
        list_view_choose_province.setTextFilterEnabled(true);
    }

    /**
     * Hàm sử lí khi nhấn nút mặc định trên adapter
     */
    @Override
    public void onSetDefaultProvince() {
        int indexSelected = this.adapter.indexSelected;
        ProvinceBean provinceBean2SetDefault;
        if(indexSelected!=-1){
            provinceBean2SetDefault = provinceBeanList.get(indexSelected);
        }else{
            provinceBean2SetDefault=GlobalStaticData.getCurrentProvinceBean();
        }


        GlobalStaticData.setCurrentProvinceBean(provinceBean2SetDefault);

        // Toast.makeText(getApplicationContext(),GlobalStaticData.getCurrentProvince_What2do().getTitleProvince(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("changed_province", true);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Hàm xử lí sự kiện khi chọn 1 đối tượng trên listview
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.adapter.indexSelected = position - 1;
        this.list_view_choose_province.setSelection(adapter.indexSelected);
        this.list_view_choose_province.smoothScrollToPositionFromTop(adapter.indexSelected, 0);
        this.adapter.notifyDataSetChanged();
    }

    /**
     * Hàm lấy danh sách các tỉnh trong CSDL
     * @param idCountry: ID quốc gia
     * @return
     */
   /* private List<ProvinceBean> getProvinceList(String idCountry) {
        return (List<ProvinceBean>) provinceController.executeSelect(AppConfig.REQUEST_CODE_LIST_PROVINCE);

    }*/

    /**
     * Hàm sủ lí sự kiện khi click vào các view trên activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_choose_provine:
                //Nút back
                finish();
                break;
            case R.id.text_view_done_choose_provine:
                //Nút xong
                onSetDefaultProvince();
                break;
            case R.id.image_view_delete_choose_province:
                //Nút xoá nội dung tìm kiếm
                edit_text_search_choose_province.setText("");
                break;
        }
    }
}
