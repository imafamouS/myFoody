package com.fdsa.infamous.myfoody.ui.menu.activity.userprofile;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.MenuBarItemBean;
import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.util.controller_F2.UserController;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.google.gson.JsonObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by apple on 4/30/17.
 */

public class ChangeInforActivity_1 extends AppCompatActivity implements View.OnClickListener, OnDateSetListener {

    public ChangeInforActivity_1() {

    }

    LinearLayout back_button_change_infor_user_1;
    TextView text_view_user_name;
    TextView text_view_done_infor_user;

    EditText edit_text_login_name;
    EditText edit_text_last_name;
    EditText edit_text_first_name;
    EditText edit_text_email;

    RelativeLayout relative_layout_manage_phone;
    RelativeLayout relative_layout_manage_address;

    EditText edit_text_date_join;
    EditText edit_text_marry_status;
    EditText edit_text_sex;
    TextView text_view_date_of_birth;
    LinearLayout linear_layout_date_of_birth;

    UserBean currentUser;
    DatePickerDialog datePicker;

    /**
     * DATA
     **/
    List<MenuBarItemBean> listMarryStatus;
    List<MenuBarItemBean> listGender;
    Map<String, Integer> selectedPositionMenu;

    MarryAdapter marryAdapter;
    GenderAdapter genderAdapter;
    AlertDialog.Builder marryBuilder;
    AlertDialog.Builder genderBuilder;
    final String TYPE_MARRY = "type_marry";
    final String TYPE_GENDER = "type_gender";

    UserController userController;

    int year;
    int month;
    int dayofmonth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_user_layout_1);
        userController = new UserController(this.getApplicationContext());
        initDefaultData();
        initView();
        initEvent();

        if (GlobalStaticData.isLogined()) {
            currentUser = GlobalStaticData.getCurrentUser();
            displayInfo();
        }

    }

    private void initView() {
        back_button_change_infor_user_1 = (LinearLayout) findViewById(R.id.back_button_change_infor_user_1);
        text_view_user_name = (TextView) findViewById(R.id.text_view_user_name);
        text_view_done_infor_user = (TextView) findViewById(R.id.text_view_done_infor_user);

        edit_text_login_name = (EditText) findViewById(R.id.edit_text_login_name);
        edit_text_last_name = (EditText) findViewById(R.id.edit_text_last_name);
        edit_text_first_name = (EditText) findViewById(R.id.edit_text_first_name);
        edit_text_email = (EditText) findViewById(R.id.edit_text_email);

        relative_layout_manage_phone = (RelativeLayout) findViewById(R.id.relative_layout_manage_phone);
        relative_layout_manage_address = (RelativeLayout) findViewById(R.id.relative_layout_manage_address);

        edit_text_date_join = (EditText) findViewById(R.id.edit_text_date_join);
        edit_text_marry_status = (EditText) findViewById(R.id.edit_text_marry_status);
        edit_text_sex = (EditText) findViewById(R.id.edit_text_sex);
        text_view_date_of_birth = (TextView) findViewById(R.id.text_view_date_of_birth);
        linear_layout_date_of_birth = (LinearLayout) findViewById(R.id.linear_layout_date_of_birth);
    }

    private void initEvent() {
        back_button_change_infor_user_1.setOnClickListener(this);
        text_view_done_infor_user.setOnClickListener(this);
        relative_layout_manage_phone.setOnClickListener(this);
        relative_layout_manage_address.setOnClickListener(this);

        edit_text_marry_status.setOnClickListener(this);
        edit_text_sex.setOnClickListener(this);

        linear_layout_date_of_birth.setOnClickListener(this);
    }

    private void initDefaultData() {
        initDefaultPostionMenu();
        initMarryStatusData();
        initGenderData();
    }

    private void displayInfo() {
        if (currentUser == null)
            return;

        text_view_user_name.setText(currentUser.getName());
        edit_text_login_name.setText(currentUser.getUserid());
        edit_text_last_name.setText(currentUser.getName());
        edit_text_first_name.setText(currentUser.getFirstname());
        edit_text_email.setText(currentUser.getUserid());

        edit_text_date_join.setText(currentUser.getDatejoin());
        edit_text_marry_status.setText(currentUser.getMarry());
        edit_text_sex.setText(currentUser.getSex());
        text_view_date_of_birth.setText(currentUser.getAge());

        Calendar calendar = Calendar.getInstance();

        if (currentUser.getAge() == null || currentUser.getAge().length() <= 0) {

            this.year = calendar.get(Calendar.YEAR);
            this.month = calendar.get(Calendar.MONTH) + 1;
            this.dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        } else {

            String[] birth = currentUser.getAge().trim().split("/");

            this.year = Integer.parseInt(birth[0]);
            this.month = Integer.parseInt(birth[1]);
            this.dayofmonth = Integer.parseInt(birth[2]);
        }

        datePicker = new DatePickerDialog(this, this, this.year, this.month, this.dayofmonth);
    }

    private void updateInfo() {
        String userid = edit_text_login_name.getText().toString().trim();
        String firstname = edit_text_first_name.getText().toString().trim();
        String lastname = edit_text_last_name.getText().toString().trim();
        String marry = edit_text_marry_status.getText().toString().trim();
        String gender = edit_text_sex.getText().toString().trim();
        String age = text_view_date_of_birth.getText().toString().trim();

        JsonObject userJsonObject = new JsonObject();

        userJsonObject.addProperty("userid", userid);
        userJsonObject.addProperty("firstname", firstname);
        userJsonObject.addProperty("lastname", lastname);
        userJsonObject.addProperty("marry", marry);
        userJsonObject.addProperty("gender", gender);
        userJsonObject.addProperty("age", age);

        try {
            if (userController.update(userJsonObject)) {
                UserBean afterUpdateUser = userController.getuser(currentUser.getUserid(), currentUser.getSecretcode());
                GlobalStaticData.setCurrentUser(afterUpdateUser);
                GlobalStaticData.LOGINFLAG = true;
                showAlert("Cập nhật thông tin tài khoản thành công");
                setResult(AppConfig.RESULT_CODE_CHANGE_INFO_1);
                finish();

            } else {

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    private void showAlert(String mess) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder
                .setMessage(mess)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertDialogBuilder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_change_infor_user_1:
                finish();
                break;
            case R.id.text_view_done_infor_user:
                updateInfo();
                break;
            case R.id.relative_layout_manage_address:
                Toast.makeText(this, getString(R.string.TEXT_MANAGE_ADDRESS), Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_layout_manage_phone:
                Toast.makeText(this, getString(R.string.TEXT_MANAGE_PHONE), Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_text_marry_status:
                marryAdapter.notifyDataSetChanged();
                marryBuilder.show();
                break;
            case R.id.edit_text_sex:
                genderAdapter.notifyDataSetChanged();
                genderBuilder.show();
                break;
            case R.id.linear_layout_date_of_birth:
                this.datePicker.updateDate(this.year, this.month - 1, this.dayofmonth);
                this.datePicker.show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (checkDateValid(year,Calendar.YEAR) == 1) {
            this.year = year;
            this.month = month + 1;
            this.dayofmonth = dayOfMonth;
            updateBirthDay();
        } else {
            Toast.makeText(this, getString(R.string.TEXT_ERR_VALID_DATE), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBirthDay() {
        this.text_view_date_of_birth.setText(String.format("%d/%d/%d",
                new Object[]{Integer.valueOf(this.dayofmonth), Integer.valueOf(this.month), Integer.valueOf(this.year)}));
    }

    private int checkDateValid(int value, int type) {
        if (value > Calendar.getInstance().get(type)) {
            return -1;
        }
        return 1;
    }

    private void initDefaultPostionMenu() {
        selectedPositionMenu = new HashMap<>();
        selectedPositionMenu.put(TYPE_MARRY, 0);
        selectedPositionMenu.put(TYPE_GENDER, 0);
    }

    private void initMarryStatusData() {
        listMarryStatus = GlobalStaticData.initMarryStatusData();

        marryAdapter = new MarryAdapter(getApplicationContext(), R.layout.list_item_marry_status);
        for (int i = 0; i < listMarryStatus.size(); i++) {
            marryAdapter.add(listMarryStatus.get(i).getTittle());
        }

        marryBuilder = new AlertDialog.Builder(this);
        marryBuilder.setPositiveButton(getString(R.string.TEXT_ACTION_CLOSE), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        marryBuilder.setAdapter(marryAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ChangeInforActivity_1.this.selectedPositionMenu.put(TYPE_MARRY, which);
                edit_text_marry_status.setText(listMarryStatus.get(which).getTittle());
                dialog.dismiss();
            }
        });
    }

    private void initGenderData() {
        listGender = GlobalStaticData.initGenderData();
        genderAdapter = new GenderAdapter(getApplicationContext(), R.layout.list_item_gender);
        for (int i = 0; i < listGender.size(); i++) {
            genderAdapter.add(listGender.get(i).getTittle());
        }

        genderBuilder = new AlertDialog.Builder(this);
        genderBuilder.setPositiveButton(getString(R.string.TEXT_ACTION_CLOSE), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        genderBuilder.setAdapter(genderAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ChangeInforActivity_1.this.selectedPositionMenu.put(TYPE_GENDER, which);
                edit_text_sex.setText(listGender.get(which).getTittle());
                dialog.dismiss();
            }
        });
    }

    class MarryAdapter extends ArrayAdapter<String> {
        public MarryAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            if (ChangeInforActivity_1.this.selectedPositionMenu.get(TYPE_MARRY) == position) {
                v.setBackgroundColor(Color.parseColor("#D0D0D0"));
            } else {
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            return v;
        }
    }

    class GenderAdapter extends ArrayAdapter<String> {
        public GenderAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            if (ChangeInforActivity_1.this.selectedPositionMenu.get(TYPE_GENDER) == position) {
                v.setBackgroundColor(Color.parseColor("#D0D0D0"));
            } else {
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            return v;
        }
    }
}
