package com.fdsa.infamous.myfoody.ui.menu.activity.userprofile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.util.controller_F2.UserController;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by apple on 4/25/17.
 */

public class LoginByEmailActivity extends AppCompatActivity implements View.OnClickListener {

    UserController userController;
    LinearLayout linear_layout_back_button_login_mail;
    EditText edit_text_username;
    EditText edit_text_password;
    TextView text_view_login;
    TextView text_view_forgot_pass;
    TextView text_view_register;
    ImageView image_view_background;
    //hàm khởi tạo
    public LoginByEmailActivity() {

    }
    //Hàm xự lí sự kiện khi Activity được khởi tạo (Khởi tạo view)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_with_email_layout);
        userController = new UserController(this.getApplicationContext());

        linear_layout_back_button_login_mail = (LinearLayout) findViewById(R.id.linear_layout_back_button_login_mail);

        edit_text_username = (EditText) findViewById(R.id.edit_text_username);
        edit_text_password = (EditText) findViewById(R.id.edit_text_password);

        text_view_login = (TextView) findViewById(R.id.text_view_login);
        text_view_forgot_pass = (TextView) findViewById(R.id.text_view_forgot_pass);
        text_view_register = (TextView) findViewById(R.id.text_view_register);

        image_view_background = (ImageView) findViewById(R.id.image_view_background);


        linear_layout_back_button_login_mail.setOnClickListener(this);

        text_view_login.setOnClickListener(this);
        text_view_forgot_pass.setOnClickListener(this);
        text_view_register.setOnClickListener(this);

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
    //Hàm thực hiện việc đăng nhập
    public synchronized void login() {
        String username = edit_text_username.getText().toString().trim();
        String password = edit_text_password.getText().toString().trim();

        String mess = validate(username, password);

        if (mess.equals("1")) {
            GlobalFunction.shakeView(getApplicationContext(), edit_text_username);
            return;
        }
        if (mess.equals("2")) {
            GlobalFunction.shakeView(getApplicationContext(), edit_text_password);
            return;
        }


        try {
            JsonObject jsonLogin = new JsonObject();

            jsonLogin.addProperty("userid", username);
            jsonLogin.addProperty("password", password);

            UserBean out = userController.checkLogin(jsonLogin);

            if (out != null) {
                GlobalStaticData.LOGINFLAG = true;
                GlobalStaticData.setCurrentUser(out);
                Intent intent = new Intent();
                intent.putExtra("result_login_success", true);
                setResult(AppConfig.RESULT_CODE_LOGIN, intent);
                finish();
            } else {
                GlobalStaticData.LOGINFLAG = false;
                GlobalStaticData.setCurrentUser(null);
                new Builder(this).setMessage("Đăng nhập thất bại vui lòng thử lại").show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Hàm kiểm tra tính hợp lệ của giá trị đầu vào
    private String validate(String username, String password) {
        if (username.equals("") || username.length() <= 0) {
            return "1";
        }
        if (password.equals("") || password.length() <= 0) {
            return "2";
        }
        return "0";

    }
    //Hàm xử lí sự kiện onClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_back_button_login_mail:
                finish();
                break;
            case R.id.text_view_login:
                login();
                break;
            case R.id.text_view_forgot_pass:
                //forgotpass
                break;
            case R.id.text_view_register:
                startActivityForResult(new Intent(this.getApplicationContext(), RegisterActivity.class), AppConfig.REQUEST_CODE_REGISTER);
                break;
        }
    }
}
