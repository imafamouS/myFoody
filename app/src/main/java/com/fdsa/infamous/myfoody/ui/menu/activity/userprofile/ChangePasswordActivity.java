package com.fdsa.infamous.myfoody.ui.menu.activity.userprofile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;
import com.fdsa.infamous.myfoody.util.controller_F2.UserController;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;


/**
 * Created by apple on 5/1/17.
 */

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    public ChangePasswordActivity() {

    }

    LinearLayout back_button_change_password;
    TextView text_view_login_email;
    EditText edit_text_current_password;
    EditText edit_text_new_password;
    EditText edit_text_confirm_new_password;
    TextView text_view_save_change;

    UserBean currentUser;
    UserController userController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_user);

        init();
        userController = new UserController(this.getApplicationContext());
        currentUser = GlobalStaticData.getCurrentUser();
        if (currentUser != null) {
            text_view_login_email.setText(getString(R.string.TEXT_EMAIL_REGISTER) + currentUser.getUserid());
        }

    }

    private void init() {
        back_button_change_password = (LinearLayout) findViewById(R.id.back_button_change_password);
        text_view_login_email = (TextView) findViewById(R.id.text_view_login_email);
        edit_text_current_password = (EditText) findViewById(R.id.edit_text_current_password);
        edit_text_new_password = (EditText) findViewById(R.id.edit_text_new_password);
        edit_text_confirm_new_password = (EditText) findViewById(R.id.edit_text_confirm_new_password);
        text_view_save_change = (TextView) findViewById(R.id.text_view_save_change);

        back_button_change_password.setOnClickListener(this);
        text_view_save_change.setOnClickListener(this);
    }

    private void updatePassword() {
        String currentpass = edit_text_current_password.getText().toString().trim();
        String newpass = edit_text_new_password.getText().toString().trim();
        String confirmpass = edit_text_confirm_new_password.getText().toString().trim();

        String message = validate(newpass, confirmpass);

        if (!message.equals("") && message.length() > 0) {

            Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            if (message.equals(getString(R.string.TEXT_ERR_VALID_PASS_LENGHT))) {
                GlobalFunction.shakeView(getApplicationContext(), edit_text_new_password);
                return;
            }
            if (message.equals(getString(R.string.TEXT_ERR_VALID_PASS_CONFIRM))) {
                GlobalFunction.shakeView(getApplicationContext(), edit_text_confirm_new_password);
                return;
            }
        }

        JsonObject input = new JsonObject();
        input.addProperty("userid", currentUser.getUserid());
        input.addProperty("currentpass", currentpass);
        input.addProperty("newpass", newpass);

        try {
            if (userController.changePass(input)) {
                showAlert(getString(R.string.TEXT_UPDATE_INFO_SUCCESS));
            } else {
                showAlert(getString(R.string.TEXT_UPDATE_INFO_FAIL));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private String validate(String password, String confirmPass) {

        if (password.length() < 4 || confirmPass.length() < 4) {
            return getString(R.string.TEXT_ERR_VALID_PASS_LENGHT);
        }
        if (!password.equals(confirmPass)) {
            return getString(R.string.TEXT_ERR_VALID_PASS_CONFIRM);
        }
        return "";
    }

    private void showAlert(String mess) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ChangePasswordActivity.this);

        alertDialogBuilder
                .setMessage(mess)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.TEXT_ACTION_CLOSE), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ChangePasswordActivity.this.finish();
                    }
                });
        alertDialogBuilder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_change_password:
                finish();
                break;
            case R.id.text_view_save_change:
                updatePassword();
                break;
        }
    }
}
