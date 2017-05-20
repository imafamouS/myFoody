package com.fdsa.infamous.myfoody.ui.menu.activity.gallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.FolderGalleryBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.common.myinterface.IOnClickImage;
import com.fdsa.infamous.myfoody.config.AppConfig;

import java.util.ArrayList;


/**
 * Created by apple on 5/1/17.
 */

public class GalleryFolderActivity extends Activity implements View.OnClickListener, GridView.OnItemClickListener, IOnClickImage {
    public static final int SINGLE_SELECT = 0; //Chọn 1 ảnh
    public static final int MULTI_SELECT = 1;   //Chọn nhiều ảnh
    static String TAG;

    static {
        TAG = GalleryFolderActivity.class.getSimpleName();
    }

    public ArrayList<FolderGalleryBean> imageGalleyList = new ArrayList<>();
    LinearLayout back_button_gallery;
    TextView text_view_done;
    GridView grid_view_folder;
    GalleryFolderAdapter adapter;
    int mode;
    boolean isFile;
    RelativeLayout relative_layout_review_photo;
    RecyclerView recycle_view_choose;
    TextView text_view_not_media;
    ArrayList<ImageGalleryBean> selectedImage = new ArrayList<>();
    GalleryFileAdapter adapterReviewPhoto;
    ArrayList<ImageGalleryBean> imageGalleryBeen = new ArrayList<>();

    //hàm khởi tạo
    public GalleryFolderActivity() {

    }
    //hàm xử lí sự kiện khi activity được khởi tạo
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_folder_layout);
        this.mode = getIntent().getIntExtra("mode", 0);

        initView();
        initEvent();

        imageGalleyList = getData();
        adapter = new GalleryFolderAdapter(this.getApplicationContext(), imageGalleyList);
        grid_view_folder.setAdapter(adapter);
        grid_view_folder.setOnItemClickListener(this);
    }
    //Khởi tạo view
    private void initView() {
        relative_layout_review_photo = (RelativeLayout) findViewById(R.id.relative_layout_review_photo);
        back_button_gallery = (LinearLayout) findViewById(R.id.back_button_gallery);
        text_view_done = (TextView) findViewById(R.id.text_view_done);
        grid_view_folder = (GridView) findViewById(R.id.grid_view_folder);

        if (mode == MULTI_SELECT) {
            relative_layout_review_photo.setVisibility(View.VISIBLE);
            initViewSelectedImage();
        } else {
            relative_layout_review_photo.setVisibility(View.GONE);
        }

    }
    //Khởi tạo các sự kiện cho view
    private void initEvent() {
        back_button_gallery.setOnClickListener(this);
        text_view_done.setOnClickListener(this);
    }
    //Khởi tạo các view của Layout review các ảnh đã chọn
    private void initViewSelectedImage() {
        recycle_view_choose = (RecyclerView) findViewById(R.id.recycle_view_choose);
        text_view_not_media = (TextView) findViewById(R.id.text_view_not_media);

        this.selectedImage = getIntent().getParcelableArrayListExtra("images");

        adapterReviewPhoto = new GalleryFileAdapter(this.getApplicationContext(), selectedImage, GalleryFileAdapter.REVIEW_PHOTO);
        adapterReviewPhoto.setiOnClickImage(this);

        recycle_view_choose.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycle_view_choose.setAdapter(adapterReviewPhoto);


        if (selectedImage != null && selectedImage.size() > 0) {
            text_view_not_media.setVisibility(View.GONE);
        }

    }
    //Hàm xử lí sự kiện khi click vào ảnh review (xóa ảnh)
    @Override
    public void onClickReviewImage(View v, int index) {
        this.adapterReviewPhoto.data.remove(index);
        this.adapterReviewPhoto.notifyDataSetChanged();

        if (this.adapterReviewPhoto.data != null && this.adapterReviewPhoto.data.size() > 0)
            this.text_view_not_media.setVisibility(View.GONE);
        else
            this.text_view_not_media.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickImage(View v, int index) {

    }
    //Hàm lấy danh sách folder và các file trong folder của Gallery
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

    /**
     *   Hàm xử lí sự kiện khi có 1 activity kết thúc (Từ Activity GalleryFileActivity)
     *   trả về các ảnh đã được chọn bến đấy
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == AppConfig.RESULT_CODE_MULTISELECT ||
                resultCode == AppConfig.RESULT_CODE_SINGLESELECT) {

            imageGalleryBeen = data.getParcelableArrayListExtra("images");
            if (resultCode == AppConfig.RESULT_CODE_MULTISELECT) {
                this.adapterReviewPhoto.removeAllSelectedSingleClick();
                this.adapterReviewPhoto.data = imageGalleryBeen;
                if (this.adapterReviewPhoto != null && this.adapterReviewPhoto.data.size() > 0) {
                    this.text_view_not_media.setVisibility(View.GONE);
                } else {
                    this.text_view_not_media.setVisibility(View.VISIBLE);
                }

            }
        }
    }
    //hàm gửi data
    public void sendData() {
        Intent intent = new Intent();
        if (mode == GalleryFolderActivity.MULTI_SELECT) {
            intent.putParcelableArrayListExtra("images", this.adapterReviewPhoto.data);
        } else if (mode == GalleryFolderActivity.SINGLE_SELECT) {
            intent.putParcelableArrayListExtra("images", this.imageGalleryBeen);
        }
        setResult(AppConfig.RESULT_CODE_FROM_GALLERY_FOLDER, intent);
        finish();
    }
    //Hàm xử lí sự kiện khi nhấn vào các folder ảnh (Khởi tạo Activity GalleryFileActiviity)

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), GalleryFileActivity.class);
        intent.putExtra("namefolder", ((FolderGalleryBean) this.adapter.getItem(position)).getFolder());
        intent.putExtra("mode", this.mode);
        intent.putParcelableArrayListExtra("data", ((FolderGalleryBean) this.adapter.getItem(position)).getImageInFolder());
        if (mode == MULTI_SELECT) {
            intent.putParcelableArrayListExtra("images", this.adapterReviewPhoto.data);
        }
        startActivityForResult(intent, 1);
    }
//Hàm xử lí sự kiện onClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_done:
                sendData();
                break;
            case R.id.back_button_gallery:
                sendData();
                break;
        }
    }
}