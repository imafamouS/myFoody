package com.fdsa.infamous.myfoody.ui.menu.activity.gallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.FolderGalleryBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.config.AppConfig;

import java.util.ArrayList;


/**
 * Created by apple on 5/1/17.
 */

public class GalleryFolderActivity extends Activity implements View.OnClickListener, GridView.OnItemClickListener {
    public GalleryFolderActivity() {

    }

    static String TAG;

    public static final int SINGLE_SELECT = 0;
    public static final int MULTI_SELECT = 1;

    static {
        TAG = GalleryFolderActivity.class.getSimpleName();
    }

    LinearLayout back_button_gallery;
    TextView text_view_done;
    GridView grid_view_folder;
    GalleryFolderAdapter adapter;
    int mode;

    public ArrayList<FolderGalleryBean> imageGalleyList = new ArrayList<>();
    boolean isFile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_folder_layout);
        this.mode=getIntent().getIntExtra("mode",0);

        initView();
        initEvent();

        imageGalleyList = getData();
        adapter = new GalleryFolderAdapter(this.getApplicationContext(), imageGalleyList);
        grid_view_folder.setAdapter(adapter);
        grid_view_folder.setOnItemClickListener(this);
    }

    private void initView() {
        back_button_gallery = (LinearLayout) findViewById(R.id.back_button_gallery);
        text_view_done = (TextView) findViewById(R.id.text_view_done);
        grid_view_folder = (GridView) findViewById(R.id.grid_view_folder);
    }

    private void initEvent() {
        back_button_gallery.setOnClickListener(this);
        text_view_done.setOnClickListener(this);
    }


    public ArrayList<FolderGalleryBean> getData() {
        ArrayList<FolderGalleryBean> list = new ArrayList<>();
        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);


            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFolder().equals(cursor.getString(column_index_folder_name))) {
                    isFile = true;
                    int_position = i;
                    break;
                } else {
                    isFile = false;
                }
            }
            if (isFile) {

                ArrayList<ImageGalleryBean> al_path = new ArrayList<>();
                al_path.addAll(list.get(int_position).getImageInFolder());
                al_path.add(new ImageGalleryBean(absolutePathOfImage, false));
                list.get(int_position).setImageInFolder(al_path);

            } else {
                ArrayList<ImageGalleryBean> al_path = new ArrayList<>();
                al_path.add(new ImageGalleryBean(absolutePathOfImage, false));
                FolderGalleryBean obj_model = new FolderGalleryBean();
                obj_model.setFolder(cursor.getString(column_index_folder_name));
                obj_model.setImageInFolder(al_path);

                list.add(obj_model);
            }
        }

        return list;
    }
    ArrayList<ImageGalleryBean> imageGalleryBeen;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode== AppConfig.RESULT_CODE_MULTISELECT||
                resultCode== AppConfig.RESULT_CODE_SINGLESELECT){
            imageGalleryBeen=data.getParcelableArrayListExtra("images");
        }
    }
    public void sendData(){
        Intent intent=new Intent();
        intent.putParcelableArrayListExtra("images",imageGalleryBeen);
        setResult(AppConfig.RESULT_CODE_FROM_GALLERY_FOLDER,intent);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), GalleryFileActivity.class);
        intent.putExtra("namefolder", ((FolderGalleryBean) this.adapter.getItem(position)).getFolder());
        intent.putExtra("mode", this.mode);
        intent.putParcelableArrayListExtra("data", ((FolderGalleryBean) this.adapter.getItem(position)).getImageInFolder());
        startActivityForResult(intent,1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_done:
                sendData();
                break;
            case R.id.back_button_gallery:
                finish();
                break;
        }
    }
}
