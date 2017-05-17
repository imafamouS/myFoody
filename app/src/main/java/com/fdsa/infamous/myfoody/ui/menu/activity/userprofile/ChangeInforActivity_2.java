package com.fdsa.infamous.myfoody.ui.menu.activity.userprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.fdsa.infamous.myfoody.ui.menu.activity.BaseSlideActivity;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 4/30/17.
 */

public class ChangeInforActivity_2 extends BaseSlideActivity implements View.OnClickListener{
    public ChangeInforActivity_2(){

    }
    LinearLayout back_button_change_infor_user_2;
    LinearLayout linear_layout_change_avatar_user;
    LinearLayout linear_layout_change_password;
    CircleImageView image_view_avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_user_layout_2);

        back_button_change_infor_user_2=(LinearLayout)findViewById(R.id.back_button_change_infor_user_2);

        linear_layout_change_avatar_user=(LinearLayout)findViewById(R.id.linear_layout_change_avatar_user);
        linear_layout_change_password=(LinearLayout)findViewById(R.id.linear_layout_change_password);
        image_view_avatar=(CircleImageView)findViewById(R.id.image_view_avatar);

        if(GlobalStaticData.getCurrentUser().getAvatar()!=null){
            Glide.with(this.getApplicationContext()).load(APIConfig.BASE_URL_IMAGE+ GlobalStaticData.getCurrentUser().getAvatar()).into(image_view_avatar);
        }else{
            Glide.with(this.getApplicationContext()).load(R.drawable.icon_user_avatar).into(image_view_avatar);
        }

        back_button_change_infor_user_2.setOnClickListener(this);
        linear_layout_change_avatar_user.setOnClickListener(this);
        linear_layout_change_password.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button_change_infor_user_2:
                finish();
                break;
            case R.id.linear_layout_change_avatar_user:
                startActivity(new Intent(this.getApplicationContext(),ChangeAvatarActivity.class));
                break;
            case R.id.linear_layout_change_password:
                startActivity(new Intent(this.getApplicationContext(),ChangePasswordActivity.class));
                break;

        }
    }

}
