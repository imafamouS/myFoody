package com.fdsa.infamous.myfoody.ui.menu.activity.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.common.myinterface.IOnClickImage;

import java.util.ArrayList;

/**
 * Created by apple on 5/2/17.
 */

public class GalleryFileActivity extends AppCompatActivity implements View.OnClickListener, IOnClickImage {

    static String TAG;

    static {
        TAG = "GALLERYFILEACTIVITY";
    }

    public GalleryFileActivity() {
    }

    TextView text_view_name_folder_image_item;
    LinearLayout back_button_gallery;
    TextView text_view_done;
    RecyclerView grid_view_file;

    int mode;
    ArrayList<ImageGalleryBean> data;
    GalleryFileAdapter adapter;
    String namefolder;

    ImageView test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_image_layout);

        text_view_name_folder_image_item = (TextView) findViewById(R.id.text_view_name_folder_image_item);
        back_button_gallery = (LinearLayout) findViewById(R.id.back_button_gallery);
        grid_view_file = (RecyclerView) findViewById(R.id.recycle_view);
        text_view_done = (TextView) findViewById(R.id.text_view_done);

        back_button_gallery.setOnClickListener(this);
        text_view_done.setOnClickListener(this);

        this.namefolder = getIntent().getStringExtra("namefolder");
        this.mode = getIntent().getIntExtra("mode", 0);

        this.data = getIntent().getParcelableArrayListExtra("data");
        Log.d(TAG, this.mode + "");
        text_view_name_folder_image_item.setText(namefolder);

        adapter = new GalleryFileAdapter(getApplicationContext(), data);
        adapter.setiOnClickImage(this);

        grid_view_file.setLayoutManager(new GridLayoutManager(this, 3));
        grid_view_file.setAdapter(adapter);


    }

    int lastPosition = -1;
    private int selectedPosition(ImageGalleryBean item){
        for(int i=0;i<this.adapter.imageSelected.size();i++){
            ImageGalleryBean im=this.adapter.imageSelected.get(i);
            if(item.getPath().equals(im.getPath())){
                return i;
            }
        }
        return -1;
    }
    @Override
    public void onClickImage(View v, int index) {
        int selectedItemPosition=selectedPosition(this.data.get(index));
        if (this.mode == GalleryFolderActivity.MULTI_SELECT) {
            if(selectedItemPosition!=-1){
                this.adapter.removeSelectedPosition(selectedItemPosition,index);
            }else {
                this.adapter.addSelected(this.data.get(index));
            }
        }else if(this.mode==GalleryFolderActivity.SINGLE_SELECT){
            if(selectedItemPosition!=-1){
                this.adapter.removeSelectedPosition(selectedItemPosition,index);
            }else{
                if(this.adapter.imageSelected.size()>0){
                    this.adapter.removeAllSelectedSingleClick();
                }
                this.adapter.addSelected(this.data.get(index));

            }
        }
    }

    private void showData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_gallery:
                finish();
                break;
            case R.id.text_view_done:
                //
                break;
            default:
                break;
        }
    }
}
