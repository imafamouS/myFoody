package com.fdsa.infamous.myfoody.ui.menu.fragment.hometab;

/**
 * Created by FDSA on 3/24/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.ui.menu.activity.AddNewPlaceActivity;
import com.fdsa.infamous.myfoody.ui.menu.activity.userprofile.LoginChooserActivity;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

public class PlusActionView extends BottomSheetDialogFragment implements View.OnClickListener {

    View v;
    LinearLayout linear_layout_add_place_plus_menu;
    LinearLayout linear_layout_checkin_plus_menu;
    LinearLayout linear_layout_ecoupon_plus_menu;
    LinearLayout linear_layout_review_plus_menu;
    LinearLayout linear_layout_upload_image_plus_menu;

    /***
     * Hàm bắt sự kiện khi PLusActionView được gọi
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_plus_menu, container, false);
        /**Khởi tao các View**/
        linear_layout_add_place_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_add_place_plus_menu);
        linear_layout_checkin_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_checkin_plus_menu);
        linear_layout_ecoupon_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_ecoupon_plus_menu);
        linear_layout_review_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_review_plus_menu);
        linear_layout_upload_image_plus_menu = (LinearLayout) v.findViewById(R.id.linear_layout_upload_image_plus_menu);

        /**Khởi tao sự kiện click cho view**/
        linear_layout_add_place_plus_menu.setOnClickListener(this);
        linear_layout_checkin_plus_menu.setOnClickListener(this);
        linear_layout_ecoupon_plus_menu.setOnClickListener(this);
        linear_layout_review_plus_menu.setOnClickListener(this);
        linear_layout_upload_image_plus_menu.setOnClickListener(this);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConfig.REQUEST_CODE_LOGIN_TO_ADD_NEW_PLACE) {
            if (GlobalStaticData.isLogined()) {
                linear_layout_add_place_plus_menu.performClick();
                this.dismiss();
            }

        }
    }

    /***
     * Hàm bắt sự kiện khi click các menu của PlusActionView
     *
     * @param v: Layout được nhấn
     */
    @Override
    public void onClick(View v) {
        String str = "You clicked ";
        switch (v.getId()) {
            case R.id.linear_layout_add_place_plus_menu:
                if (GlobalStaticData.isLogined()) {
                    getActivity().startActivity(new Intent(this.getActivity(), AddNewPlaceActivity.class));
                } else {
                    startActivityForResult(new Intent(this.getActivity(), LoginChooserActivity.class), AppConfig.REQUEST_CODE_LOGIN_TO_ADD_NEW_PLACE);
                }
                break;
            case R.id.linear_layout_checkin_plus_menu:
                str = str + "Checkin";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_ecoupon_plus_menu:
                str = str + "Ecoupon";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_review_plus_menu:
                str = str + "Viết bình luận";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_upload_image_plus_menu:
                str = str + "Đăng ảnh";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}