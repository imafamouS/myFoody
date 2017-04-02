package com.fdsa.infamous.myfoody.ui.menu.views;

/**
 * Created by FDSA on 3/24/2017.
 */

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    View v;
    LinearLayout linear_layout_add_place_plus_menu;
    LinearLayout linear_layout_checkin_plus_menu;
    LinearLayout linear_layout_ecoupon_plus_menu;
    LinearLayout linear_layout_review_plus_menu;
    LinearLayout linear_layout_upload_image_plus_menu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_plus_menu, container, false);

        linear_layout_add_place_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_add_place_plus_menu);
        linear_layout_checkin_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_checkin_plus_menu);
        linear_layout_ecoupon_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_ecoupon_plus_menu);
        linear_layout_review_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_review_plus_menu);
        linear_layout_upload_image_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_upload_image_plus_menu);

        linear_layout_add_place_plus_menu.setOnClickListener(this);
        linear_layout_checkin_plus_menu.setOnClickListener(this);
        linear_layout_ecoupon_plus_menu.setOnClickListener(this);
        linear_layout_review_plus_menu.setOnClickListener(this);
        linear_layout_upload_image_plus_menu.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        String str = "You clicked ";
        switch (v.getId()) {
            case R.id.linear_layout_add_place_plus_menu:
                str = str + "Thêm địa điểm";
                break;
            case R.id.linear_layout_checkin_plus_menu:
                str = str + "Checkin";
                break;
            case R.id.linear_layout_ecoupon_plus_menu:
                str = str + "Ecoupon";
                break;
            case R.id.linear_layout_review_plus_menu:
                str = str + "Viết bình luận";
                break;
            case R.id.linear_layout_upload_image_plus_menu:
                str = str + "Đăng ảnh";
                break;
            default:
                break;
        }
        Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}