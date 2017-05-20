package com.fdsa.infamous.myfoody.ui.menu.activity.userprofile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.fdsa.infamous.myfoody.ui.menu.activity.BaseSlideActivity;
import com.fdsa.infamous.myfoody.ui.menu.activity.gallery.GalleryFolderActivity;
import com.fdsa.infamous.myfoody.util.controller_F2.UserController;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.fdsa.infamous.myfoody.util.permission.PermissionUtil;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 5/1/17.
 */

public class ChangeAvatarActivity extends BaseSlideActivity implements View.OnClickListener {
    public static final int CHANGE_AVATAR = 0;
    public static final int CHANGE_COVER = 1;
    LinearLayout linear_layout_change_avatar;
    LinearLayout linear_layout_change_cover;
    TextView text_view_save_change;
    LinearLayout back_button_change_avatar;
    CircleImageView profile_image;
    ImageView cover_image;
    UserController userController;
    UserBean currentUser;

    //Sự kiện khi click trên popup
    PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.select_from_gallery:
                    if (PermissionUtil.isReadWritePermission_LowAPI(ChangeAvatarActivity.this)) {
                        Intent intent = new Intent(ChangeAvatarActivity.this, GalleryFolderActivity.class);
                        intent.putExtra("mode", GalleryFolderActivity.SINGLE_SELECT);
                        startActivityForResult(intent, 1);
                        return true;
                    }
                    PermissionUtil.isReadWritePermission_HighAPI(ChangeAvatarActivity.this);
                    return true;
                case R.id.select_from_camera:
                    break;
            }
            return false;
        }
    };
    ImageGalleryBean uploadAvatar = null;
    ImageGalleryBean uploadCover = null;
    boolean changeavatar = false;
    boolean changecover = false;
    public ChangeAvatarActivity() {

    }
    //hàm xử lí sự kiện khi activity được khởi tạo (khởi tạo view)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_avatar_user);

        back_button_change_avatar = (LinearLayout) findViewById(R.id.back_button_change_avatar);
        linear_layout_change_avatar = (LinearLayout) findViewById(R.id.linear_layout_change_avatar);
        linear_layout_change_cover = (LinearLayout) findViewById(R.id.linear_layout_change_cover);
        text_view_save_change = (TextView) findViewById(R.id.text_view_save_change);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        cover_image = (ImageView) findViewById(R.id.cover_image);

        linear_layout_change_avatar.setOnClickListener(this);
        linear_layout_change_cover.setOnClickListener(this);
        text_view_save_change.setOnClickListener(this);
        back_button_change_avatar.setOnClickListener(this);
        userController = new UserController(this.getApplicationContext());
        currentUser = GlobalStaticData.getCurrentUser();

        if (currentUser.getAvatar() != null) {
            Glide.with(this.getApplicationContext()).load(APIConfig.BASE_URL_IMAGE + currentUser.getAvatar()).into(profile_image);
        } else {

            Glide.with(this.getApplicationContext()).load(R.drawable.icon_user_avatar).into(profile_image);
        }

        if (currentUser.getCover() != null) {
            Glide.with(this.getApplicationContext()).load(APIConfig.BASE_URL_IMAGE + currentUser.getCover()).into(cover_image);
        } else {
            Glide.with(this.getApplicationContext()).load(R.drawable.icon_null).into(cover_image);
        }
    }
    //Hiền popup
    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.select_photo_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(onMenuItemClickListener);
        popup.show();
    }
    //Hàm xử lí sự kiện nhận kết quả trả về từ activity (Lấy Ảnh)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == AppConfig.RESULT_CODE_FROM_GALLERY_FOLDER) {
            ArrayList<ImageGalleryBean> dataReponse = new ArrayList<>();
            dataReponse = data.getParcelableArrayListExtra("images");
            if (dataReponse != null && dataReponse.size() > 0) {
                updateImageView(dataReponse.get(0));
            }

        }
    }
    //Hiện thông báo
    private void showAlert(String mess) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder
                .setMessage(mess)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.TEXT_ACTION_CLOSE), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertDialogBuilder.show();
    }
    //Hàm update lại các imageview sau khi chọn ảnh
    public void updateImageView(ImageGalleryBean image) {
        if (this.changeavatar) {
            this.changeavatar = false;
            this.uploadAvatar = image;
            Glide.with(this.getApplicationContext()).load("file://" + uploadAvatar.getPath()).into(profile_image);
        }
        if (this.changecover) {
            this.changecover = false;
            this.uploadCover = image;
            Glide.with(this.getApplicationContext()).load("file://" + uploadCover.getPath()).into(cover_image);
        }
    }
    //hàm thực hiện việc đưa dữ liệu lên server
    private void uploadImage() throws ExecutionException, InterruptedException {
        boolean isSuccess = false;
        JsonObject avatar = null;
        JsonObject cover = null;
        if (this.uploadAvatar != null) {
            avatar = GlobalFunction.createImageInputObject(this.uploadAvatar.getPath());
            avatar.addProperty("userid", GlobalStaticData.getCurrentUser().getUserid());
            isSuccess = userController.changeAvatar(avatar, 0, GlobalStaticData.getCurrentUser().getSecretcode());

        }
        if (this.uploadCover != null) {
            cover = GlobalFunction.createImageInputObject(this.uploadCover.getPath());
            cover.addProperty("userid", GlobalStaticData.getCurrentUser().getUserid());
            isSuccess = userController.changeAvatar(cover, 1, GlobalStaticData.getCurrentUser().getSecretcode());
        }
        if (isSuccess) {
            showAlert(getString(R.string.TEXT_UPDATE_INFO_SUCCESS_AVATAR));
            UserBean afterUpdateUser = userController.getuser(currentUser.getUserid(), currentUser.getSecretcode());
            GlobalStaticData.setCurrentUser(afterUpdateUser);
        }
    }
    //hàm xử lí sự kiện onClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_change_avatar:
                finish();
                break;
            case R.id.linear_layout_change_avatar:
                this.changeavatar = true;
                this.changecover = false;
                showPopup(v);
                break;
            case R.id.linear_layout_change_cover:
                this.changecover = true;
                this.changeavatar = false;
                showPopup(v);
                break;
            case R.id.text_view_save_change:
                try {
                    uploadImage();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
