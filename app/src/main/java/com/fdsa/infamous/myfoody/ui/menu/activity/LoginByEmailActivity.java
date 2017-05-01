package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    public LoginByEmailActivity() {

    }

    LinearLayout linear_layout_back_button_login_mail;
    EditText edit_text_username;
    EditText edit_text_password;

    TextView text_view_login;

    TextView text_view_forgot_pass;

    TextView text_view_register;

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


        linear_layout_back_button_login_mail.setOnClickListener(this);

        text_view_login.setOnClickListener(this);
        text_view_forgot_pass.setOnClickListener(this);
        text_view_register.setOnClickListener(this);
    }

    public synchronized void login() {
        String username = edit_text_username.getText().toString().trim();
        String password = edit_text_password.getText().toString().trim();

        String mess=validate(username,password);

        if(mess.equals("1")){
            GlobalFunction.shakeView(getApplicationContext(),edit_text_username);
            return;
        }
        if(mess.equals("2")){
            GlobalFunction.shakeView(getApplicationContext(),edit_text_password);
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
                Log.d("CURRENT USER",out.toString());
                Intent intent = new Intent();
                intent.putExtra("result_login_success", true);
                setResult(AppConfig.RESULT_CODE_LOGIN, intent);
                finish();
            } else {
                GlobalStaticData.LOGINFLAG = true;
                GlobalStaticData.setCurrentUser(null);
                new Builder(this).setMessage("Đăng nhập thất bại vui lòng thử lại").show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private String validate(String username,String password){
        if(username.equals("")||username.length()<=0){
            return "1";
        }
        if(password.equals("")||password.length()<=0){
            return "2";
        }
        return "0";

    }

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
