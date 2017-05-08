package com.fdsa.infamous.myfoody.ui.menu.fragment.usertab;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.ui.menu.activity.userprofile.ChangeInforActivity_1;
import com.fdsa.infamous.myfoody.ui.menu.activity.userprofile.ChangeInforActivity_2;
import com.fdsa.infamous.myfoody.ui.menu.activity.userprofile.LoginChooserActivity;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

/**
 * Created by FDSA on 4/9/2017.
 */

public class UserTabFragment extends Fragment implements View.OnClickListener {
    //Hàm khởi tạo
    public UserTabFragment() {
        super();
    }

    TextView textView7;
    UserBean user;

    LinearLayout linear_layout_login;
    RelativeLayout relative_layout_infor_contact;
    RelativeLayout relative_layout_setting_account;

    LinearLayout linear_layout_logout;

    //Hàm xử lí sự kiện khi fragment được tạo (khởi tạo view)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.user_tab_fragment, container, false);

        textView7 = (TextView) v.findViewById(R.id.textView7);
        linear_layout_login = (LinearLayout) v.findViewById(R.id.linear_layout_login);

        relative_layout_infor_contact = (RelativeLayout) v.findViewById(R.id.relative_layout_infor_contact);
        relative_layout_setting_account = (RelativeLayout) v.findViewById(R.id.relative_layout_setting_account);
        linear_layout_logout = (LinearLayout) v.findViewById(R.id.linear_layout_logout);

        relative_layout_infor_contact.setOnClickListener(this);
        relative_layout_setting_account.setOnClickListener(this);
        linear_layout_login.setOnClickListener(this);
        linear_layout_logout.setOnClickListener(this);

        onTabVisible();

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==AppConfig.REQUEST_CODE_LOGIN ||resultCode==AppConfig.RESULT_CODE_CHANGE_INFO_1){
            reload();
            this.onTabVisible();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_login:
                if (!GlobalStaticData.isLogined()) {
                    startActivityForResult(new Intent(this.getActivity(), LoginChooserActivity.class), AppConfig.REQUEST_CODE_LOGIN);
                }
                break;

            case R.id.relative_layout_infor_contact:
                if (GlobalStaticData.isLogined()) {
                    startActivityForResult(new Intent(this.getActivity(), ChangeInforActivity_1.class), AppConfig.REQUEST_CODE_CHANGE_INFO_1);
                } else {
                    startActivityForResult(new Intent(this.getActivity(), LoginChooserActivity.class), AppConfig.REQUEST_CODE_CHANGE_INFO_1);
                }

                break;
            case R.id.relative_layout_setting_account:
                if (GlobalStaticData.isLogined()) {
                    startActivityForResult(new Intent(this.getActivity(), ChangeInforActivity_2.class), AppConfig.REQUEST_CODE_CHANGE_INFO_1);
                } else {
                    startActivityForResult(new Intent(this.getActivity(), LoginChooserActivity.class), AppConfig.REQUEST_CODE_CHANGE_INFO_1);
                }
                break;
            case R.id.linear_layout_logout:
                logout();
                break;
        }
    }

    private void logout() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.TEXT_LOG_OUT))
                .setMessage(getString(R.string.TEXT_LOG_OUT_MESS))
                .setPositiveButton(getString(R.string.TEXT_LOG_OUT), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                signout();
                            }
                        }

                )
                .setNegativeButton(getString(R.string.TEXT_ACTION_CANCEL), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }



    private void reload() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    private void signout() {
        GlobalStaticData.setCurrentUser(null);
        GlobalStaticData.LOGINFLAG = false;

        reload();
    }

    public void onTabVisible() {
        if (GlobalStaticData.getCurrentUser() != null) {
            linear_layout_logout.setVisibility(View.VISIBLE);
            user = GlobalStaticData.getCurrentUser();
            textView7.setText(user.getName());
            return;
        }
        linear_layout_logout.setVisibility(View.GONE);
    }
}
