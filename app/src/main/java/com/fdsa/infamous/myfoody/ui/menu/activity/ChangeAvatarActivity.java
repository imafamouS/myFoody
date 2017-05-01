package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;

/**
 * Created by apple on 5/1/17.
 */

public class ChangeAvatarActivity extends AppCompatActivity implements View.OnClickListener{
    public ChangeAvatarActivity() {

    }

    LinearLayout linear_layout_change_avatar;
    LinearLayout linear_layout_change_cover;
    TextView text_view_save_change;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_avatar_user);

        linear_layout_change_avatar=(LinearLayout)findViewById(R.id.linear_layout_change_avatar);
        linear_layout_change_cover=(LinearLayout)findViewById(R.id.linear_layout_change_cover);
        text_view_save_change=(TextView)findViewById(R.id.text_view_save_change);

        linear_layout_change_avatar.setOnClickListener(this);
        linear_layout_change_cover.setOnClickListener(this);
        text_view_save_change.setOnClickListener(this);
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.select_photo_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(onMenuItemClickListener);
        popup.show();
    }

    PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.select_from_gallery:
                    break;
                case R.id.select_from_camera:
                    break;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_layout_change_avatar:
                showPopup(v);
                break;
            case R.id.linear_layout_change_cover:
                showPopup(v);
                break;
            case R.id.text_view_save_change:
                break;
        }
    }
}
