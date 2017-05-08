package com.fdsa.infamous.myfoody.ui.menu.activity.userprofile;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.util.controller_F2.UserController;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.google.gson.JsonObject;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.concurrent.ExecutionException;

/**
 * Created by apple on 4/25/17.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public RegisterActivity() {

    }

    LinearLayout linear_layout_back_button_register;
    TextView text_view_register;
    TextView text_view_login_register;

    EditText edit_text_email_register;
    EditText edit_text_password_register;
    EditText edit_text_pass_confirm_register;
    EditText edit_text_name_register;
    UserController userController;

  ImageView image_view_background;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        userController=new UserController(getApplicationContext());

        linear_layout_back_button_register = (LinearLayout) findViewById(R.id.linear_layout_back_button_register);
        text_view_register = (TextView) findViewById(R.id.text_view_register);
        text_view_login_register = (TextView) findViewById(R.id.text_view_login_register);

        edit_text_email_register = (EditText) findViewById(R.id.edit_text_email_register);
        edit_text_password_register = (EditText) findViewById(R.id.edit_text_password_register);
        edit_text_pass_confirm_register = (EditText) findViewById(R.id.edit_text_pass_confirm_register);
        edit_text_name_register = (EditText) findViewById(R.id.edit_text_name_register);

        image_view_background=(ImageView)findViewById(R.id.image_view_background);

        linear_layout_back_button_register.setOnClickListener(this);
        text_view_register.setOnClickListener(this);
        text_view_login_register.setOnClickListener(this);

    }

    private void register() {
        try {
            String email = edit_text_email_register.getText().toString().trim();
            String password = edit_text_password_register.getText().toString().trim();
            String confirmPass = edit_text_pass_confirm_register.getText().toString().trim();
            String name = edit_text_name_register.getText().toString().trim();

            String mess = validate(email, password, confirmPass);

            if (!mess.equals("") || mess.length() > 0) {
                showAlert(mess);
                if(mess.equals(getString(R.string.TEXT_ERR_VALID_EMAIL))){
                    GlobalFunction.shakeView(this.getApplicationContext(),edit_text_email_register);
                    return;
                }
                if(mess.equals(getString(R.string.TEXT_ERR_VALID_PASS_LENGHT))){
                    GlobalFunction.shakeView(this.getApplicationContext(),edit_text_password_register);
                    return;
                }
                if(mess.equals(getString(R.string.TEXT_ERR_VALID_PASS_CONFIRM))){
                    GlobalFunction.shakeView(this.getApplicationContext(),edit_text_pass_confirm_register);
                    return;
                }

            } else {
                JsonObject jsonRegister = new JsonObject();

                jsonRegister.addProperty("userid", email);
                jsonRegister.addProperty("password", password);
                jsonRegister.addProperty("name", name);

                if(userController.register(jsonRegister)){
                    showAlert(getString(R.string.TEXT_RES_SUCCESS));
                    //LOGIN PAGE
                    login(jsonRegister);
                }else{
                    showAlert(getString(R.string.TEXT_RES_FAIL));
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String validate(String email, String password, String confirmPass) {

        if (!EmailValidator.getInstance().isValid(email)) {
            return getResources().getString(R.string.TEXT_ERR_VALID_EMAIL);
        }

        if (password.length() < 4) {
            return getResources().getString(R.string.TEXT_ERR_VALID_PASS_LENGHT);
        } else {
            if (!password.equals(confirmPass)) {
                return getResources().getString(R.string.TEXT_ERR_VALID_PASS_CONFIRM);
            }
        }

        return "";
    }
    private void login(JsonObject jsonLogin){
        UserBean out = null;
        try {
            out = userController.checkLogin(jsonLogin);
            if (out != null) {
                GlobalStaticData.LOGINFLAG = true;
                GlobalStaticData.setCurrentUser(out);
                Intent intent = new Intent();
                intent.putExtra("result_login_success", true);
                setResult(AppConfig.RESULT_CODE_LOGIN, intent);
                finish();
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    private void showAlert(String mess) {
        Builder alertDialogBuilder = new AlertDialog.Builder(RegisterActivity.this);


        alertDialogBuilder
                .setMessage(mess)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialogBuilder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_back_button_register:
                finish();
                break;
            case R.id.text_view_register:
                register();
                break;
            case R.id.text_view_login_register:
                startActivity(new Intent(this.getApplicationContext(), LoginByEmailActivity.class));
                break;
            default:
                break;
        }
    }
}
