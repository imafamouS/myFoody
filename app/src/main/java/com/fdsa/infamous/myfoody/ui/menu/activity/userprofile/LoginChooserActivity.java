package com.fdsa.infamous.myfoody.ui.menu.activity.userprofile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

/**
 * Created by apple on 4/25/17.
 */

public class LoginChooserActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout frame_layout_parent_choose_type_login;
    LinearLayout linear_layout_back_button_login_chooser;
    LinearLayout linear_layout_button_login_facebook;
    LinearLayout linear_layout_button_login_google;
    LinearLayout linear_layout_button_login_yahoo;
    LinearLayout linear_layout_button_login_twitter;
    TextView text_view_button_login_phone;
    TextView text_view_button_login_email;
    TextView text_view_button_register;
    ImageView image_view_background;

    //Hàm khởi tạo
    public LoginChooserActivity() {

    }
    //Hàm xử lí sự kiện khi Activity được khởi tạo (khởi tạo View)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_type_login_layout);

        frame_layout_parent_choose_type_login = (FrameLayout) findViewById(R.id.frame_layout_parent_choose_type_login);

        linear_layout_back_button_login_chooser = (LinearLayout) findViewById(R.id.linear_layout_back_button_login_chooser);

        linear_layout_button_login_facebook = (LinearLayout) findViewById(R.id.linear_layout_button_login_facebook);
        linear_layout_button_login_google = (LinearLayout) findViewById(R.id.linear_layout_button_login_google);
        linear_layout_button_login_yahoo = (LinearLayout) findViewById(R.id.linear_layout_button_login_yahoo);
        linear_layout_button_login_twitter = (LinearLayout) findViewById(R.id.linear_layout_button_login_twitter);

        text_view_button_login_phone = (TextView) findViewById(R.id.text_view_button_login_phone);
        text_view_button_login_email = (TextView) findViewById(R.id.text_view_button_login_email);

        text_view_button_register = (TextView) findViewById(R.id.text_view_button_register);
        image_view_background = (ImageView) findViewById(R.id.image_view_background);

        /**Event**/

        linear_layout_back_button_login_chooser.setOnClickListener(this);

        linear_layout_button_login_facebook.setOnClickListener(this);
        linear_layout_button_login_google.setOnClickListener(this);
        linear_layout_button_login_yahoo.setOnClickListener(this);
        linear_layout_button_login_twitter.setOnClickListener(this);

        text_view_button_login_phone.setOnClickListener(this);
        text_view_button_login_email.setOnClickListener(this);

        text_view_button_register.setOnClickListener(this);

        Glide.with(this.getApplicationContext()).load(R.drawable.img_backgroud_login_1).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    image_view_background.setBackground(drawable);
                }
            }
        });
    }
    //Hàm xử lí sự kiện nhận dữ liệu trả về từ activty (Kiểm tra có đăng nhập thành công hay không)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConfig.REQUEST_CODE_LOGIN && resultCode == AppConfig.RESULT_CODE_LOGIN) {
            if (data.getBooleanExtra("result_login_success", false) == true) {
                setResult(resultCode);
                finish();
            } else {
                GlobalStaticData.LOGINFLAG = false;
                setResult(resultCode);
                finish();
            }

        }
    }
    //Hàm xự lí sự kiện onclick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_back_button_login_chooser:
                finish();
                break;
            case R.id.linear_layout_button_login_facebook:
                Toast.makeText(getApplicationContext(), "Đăng nhập bằng Facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_button_login_google:
                Toast.makeText(getApplicationContext(), "Đăng nhập bằng Google", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_button_login_yahoo:
                Toast.makeText(getApplicationContext(), "Đăng nhập bằng Yahoo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_button_login_twitter:
                Toast.makeText(getApplicationContext(), "Đăng nhập bằng Twitter", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_view_button_login_phone:
                Toast.makeText(getApplicationContext(), "Đăng nhập bằng số điện thoại", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_view_button_login_email:
                startActivityForResult(new Intent(this.getApplicationContext(), LoginByEmailActivity.class), AppConfig.REQUEST_CODE_LOGIN);
                break;
            case R.id.text_view_button_register:
                startActivityForResult(new Intent(this.getApplicationContext(), RegisterActivity.class), AppConfig.REQUEST_CODE_REGISTER);
                break;
        }
    }
}
