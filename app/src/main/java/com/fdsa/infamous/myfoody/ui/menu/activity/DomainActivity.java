package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;

/**
 * Created by FDSA on 3/23/2017.
 */

public class DomainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    LinearLayout linear_layout_domain_food;
    LinearLayout linear_layout_domain_travel;
    LinearLayout linear_layout_domain_wedding;
    LinearLayout linear_layout_domain_healthy;

    LinearLayout linear_layout_domain_relax;
    LinearLayout linear_layout_domain_shopping;
    LinearLayout linear_layout_domain_edu;
    LinearLayout linear_layout_domain_service;


    LinearLayout linear_layout_domain_delivery;
    LinearLayout linear_layout_domain_booking;

    /**
     * Hàm sử lí sự kiện khi activity được tạo (khởi tạo các view và event)
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.domain_layout);
        toolbar = (Toolbar) findViewById(R.id.domain_toolbar);

        linear_layout_domain_food = (LinearLayout) findViewById(R.id.linear_layout_domain_food);
        linear_layout_domain_travel = (LinearLayout) findViewById(R.id.linear_layout_domain_travel);
        linear_layout_domain_wedding = (LinearLayout) findViewById(R.id.linear_layout_domain_wedding);
        linear_layout_domain_healthy = (LinearLayout) findViewById(R.id.linear_layout_domain_healthy);

        linear_layout_domain_relax = (LinearLayout) findViewById(R.id.linear_layout_domain_relax);
        linear_layout_domain_shopping = (LinearLayout) findViewById(R.id.linear_layout_domain_shopping);
        linear_layout_domain_edu = (LinearLayout) findViewById(R.id.linear_layout_domain_edu);
        linear_layout_domain_service = (LinearLayout) findViewById(R.id.linear_layout_domain_service);

        linear_layout_domain_delivery = (LinearLayout) findViewById(R.id.linear_layout_domain_delivery);
        linear_layout_domain_booking = (LinearLayout) findViewById(R.id.linear_layout_domain_booking);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        linear_layout_domain_food.setOnClickListener(this);
        linear_layout_domain_travel.setOnClickListener(this);
        linear_layout_domain_wedding.setOnClickListener(this);
        linear_layout_domain_healthy.setOnClickListener(this);

        linear_layout_domain_relax.setOnClickListener(this);
        linear_layout_domain_shopping.setOnClickListener(this);
        linear_layout_domain_edu.setOnClickListener(this);
        linear_layout_domain_service.setOnClickListener(this);

        linear_layout_domain_delivery.setOnClickListener(this);
        linear_layout_domain_booking.setOnClickListener(this);
    }

    /**
     * Hàm sự lí sự kiện khi click vào các view
     *
     * @param v: view được click
     */
    @Override
    public void onClick(View v) {
        String strShow = "";
        switch (v.getId()) {
            case R.id.linear_layout_domain_food:
                strShow = "Ăn uống";
                break;
            case R.id.linear_layout_domain_travel:
                strShow = "Du lịch";
                break;
            case R.id.linear_layout_domain_wedding:
                strShow = "Cưới hỏi";
                break;
            case R.id.linear_layout_domain_healthy:
                strShow = "Đẹp khoẻ";
                break;
            case R.id.linear_layout_domain_relax:
                strShow = "Giải trí";
                break;
            case R.id.linear_layout_domain_shopping:
                strShow = "Mua sắm";
                break;
            case R.id.linear_layout_domain_edu:
                strShow = "Giáo dục";
                break;
            case R.id.linear_layout_domain_service:
                strShow = "Dịch vụ";
                break;

            case R.id.linear_layout_domain_delivery:
                strShow = "Giao hàng";
                break;
            case R.id.linear_layout_domain_booking:
                strShow = "Đặt chỗ";
                break;
        }

        Toast.makeText(getApplicationContext(), "You clicked on domain " + strShow, Toast.LENGTH_SHORT).show();

    }
}

