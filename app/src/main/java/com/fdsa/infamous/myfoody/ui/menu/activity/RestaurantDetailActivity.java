package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.RestaurantBean;
import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.fdsa.infamous.myfoody.util.controller_F2.RestaurantController;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;

import java.util.concurrent.ExecutionException;

/**
 * Created by apple on 5/3/17.
 */

public class RestaurantDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image_view_main;
    TextView text_view_num_of_comment;
    TextView text_view_num_of_photo;
    TextView text_view_rating_res;
    TextView text_view_res_title;
    TextView text_view_name_res;
    TextView text_view_address_res;
    TextView text_view_category_res;
    RestaurantController restaurantController;
    LinearLayout back_button;
    TextView text_view_open_or_close_res;
    TextView text_view_time_open;
    TextView text_view_cash;
    String resID;
    //Hàm khởi tạo
    public RestaurantDetailActivity() {

    }
    //Hàm xử lí sự kiện Activity được khởi tạo
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_detail_layout);
        initView();
        initEvent();
        restaurantController = new RestaurantController(this.getApplicationContext());

        displayInfo();
    }
    //Iniit View
    private void initView() {
        back_button = (LinearLayout) findViewById(R.id.back_button);
        image_view_main = (ImageView) findViewById(R.id.image_view_main);
        text_view_num_of_comment = (TextView) findViewById(R.id.text_view_num_of_comment);
        text_view_res_title = (TextView) findViewById(R.id.text_view_res_title);
        text_view_name_res = (TextView) findViewById(R.id.text_view_name_res);
        text_view_num_of_photo = (TextView) findViewById(R.id.text_view_num_of_photo);
        text_view_rating_res = (TextView) findViewById(R.id.text_view_rating_res);
        text_view_address_res = (TextView) findViewById(R.id.text_view_address_res);
        text_view_category_res = (TextView) findViewById(R.id.text_view_category_res);
        text_view_open_or_close_res = (TextView) findViewById(R.id.text_view_open_or_close_res);
        text_view_time_open = (TextView) findViewById(R.id.text_view_time_open);
        text_view_cash = (TextView) findViewById(R.id.text_view_cash);

    }
    //Init event
    private void initEvent() {
        this.back_button.setOnClickListener(this);
    }
    //Hiện thông tin chi tiết của nhà hàng lên  view
    private void displayInfo() {

        try {
            Intent intent = getIntent();
            resID = intent.getStringExtra("resid");
            Log.d("TAG_RES_DETAIL", resID);
            RestaurantBean res = restaurantController.getRestaurantById(resID);
            if (res == null || res.getId() == null || res.getId().length() <= 0) {
                Toast.makeText(this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            text_view_res_title.setText(res.getTitle());
            text_view_name_res.setText(res.getTitle());

            if (res.getListComment() != null) {
                text_view_num_of_comment.setText(res.getListComment().size() + "");
            }
            if (res.getListImage() != null) {
                text_view_num_of_photo.setText(res.getListImage().size() + 1 + "");
            }
            Glide.with(this.getApplicationContext()).load(APIConfig.BASE_URL_IMAGE + res.getPhoto()).into(image_view_main);
            text_view_address_res.setText(res.getAddress());
            text_view_rating_res.setText(GlobalFunction.round(res.getAvg_rating(), 2) + "");
            text_view_category_res.setText(res.getWhere_type() + "-" + res.getRes_type());


            if (GlobalFunction.isRestaurantOpening(res.getOpenTime(), res.getCloseTime())) {
                text_view_open_or_close_res.setText(getString(R.string.RES_OPEN));
            } else {
                text_view_open_or_close_res.setText(getString(R.string.RES_CLOSE));
            }

            text_view_time_open.setText(res.getOpenTime() + " - " + res.getCloseTime());
            text_view_cash.setText(res.getMinCash() + " - " + res.getMaxCash() + "(" + "Đồng" + ")");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    //Sự kiện onClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                finish();
                break;
            default:
                break;
        }
    }
}
