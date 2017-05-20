package com.fdsa.infamous.myfoody.ui.menu.activity.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.common.myinterface.IOnClickImage;
import com.fdsa.infamous.myfoody.config.AppConfig;

import java.util.ArrayList;

/**
 * Created by apple on 5/2/17.
 */

public class GalleryFileActivity extends AppCompatActivity implements View.OnClickListener, IOnClickImage {

    static String TAG;

    static {
        TAG = "GALLERYFILEACTIVITY";
    }

    TextView text_view_name_folder_image_item;
    LinearLayout back_button_gallery;
    TextView text_view_done;
    RecyclerView grid_view_file;
    int mode;
    ArrayList<ImageGalleryBean> data;
    GalleryFileAdapter adapterPhotoChooser;
    String namefolder;
    RecyclerView recycle_view_choose;
    TextView text_view_not_media;
    ArrayList<ImageGalleryBean> selectedImage = new ArrayList<>();
    GalleryFileAdapter adapterReviewPhoto;
    RelativeLayout relative_layout_review_photo;

    //Hàm khởi tạo
    public GalleryFileActivity() {
    }
    /**
     * Hàm xử lí sự kiện khi Activity được tạo (khởi tạo view)
     *
     * @return
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_image_layout);
        initViewChooseImage();
        relative_layout_review_photo = (RelativeLayout) findViewById(R.id.relative_layout_review_photo);
        if (mode == GalleryFolderActivity.MULTI_SELECT) {
            relative_layout_review_photo.setVisibility(View.VISIBLE);
            initViewSelectedImage();
        } else {
            relative_layout_review_photo.setVisibility(View.GONE);
        }
    }
    //Khởi tạo view
    private void initViewChooseImage() {
        text_view_name_folder_image_item = (TextView) findViewById(R.id.text_view_name_folder_image_item);
        back_button_gallery = (LinearLayout) findViewById(R.id.back_button_gallery);
        grid_view_file = (RecyclerView) findViewById(R.id.recycle_view);
        text_view_done = (TextView) findViewById(R.id.text_view_done);

        back_button_gallery.setOnClickListener(this);
        text_view_done.setOnClickListener(this);

        this.namefolder = getIntent().getStringExtra("namefolder");
        this.mode = getIntent().getIntExtra("mode", 0);

        this.data = getIntent().getParcelableArrayListExtra("data");
        this.selectedImage = getIntent().getParcelableArrayListExtra("images");
        text_view_name_folder_image_item.setText(namefolder);

        adapterPhotoChooser = new GalleryFileAdapter(getApplicationContext(), data, GalleryFileAdapter.CHOOSE_PHOTO);
        adapterPhotoChooser.setiOnClickImage(this);

        grid_view_file.setLayoutManager(new GridLayoutManager(this, 3));
        grid_view_file.setAdapter(adapterPhotoChooser);
    }
    //Khởi tạo view các ảnh đã chọn
    private void initViewSelectedImage() {
        recycle_view_choose = (RecyclerView) findViewById(R.id.recycle_view_choose);
        text_view_not_media = (TextView) findViewById(R.id.text_view_not_media);

        adapterReviewPhoto = new GalleryFileAdapter(this.getApplicationContext(), selectedImage, GalleryFileAdapter.REVIEW_PHOTO);
        adapterReviewPhoto.setiOnClickImage(this);

        recycle_view_choose.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycle_view_choose.setAdapter(adapterReviewPhoto);

        adapterPhotoChooser.removeAllSelectedSingleClick();

        for (int i = 0; i < selectedImage.size(); i++) {
            this.adapterPhotoChooser.addSelected(selectedImage.get(i));
        }

        if (selectedImage != null && selectedImage.size() > 0) {
            text_view_not_media.setVisibility(View.GONE);
        }

    }
    //Hàm trả về vị trí tương ứng khi click vào ảnh
    private int selectedPosition(ImageGalleryBean item, GalleryFileAdapter adapter) {
        for (int i = 0; i < adapter.imageSelected.size(); i++) {
            ImageGalleryBean im = adapter.imageSelected.get(i);
            if (item.getPath().equals(im.getPath())) {
                return i;
            }
        }
        return -1;
    }
    //Sự kiện khi click vào ảnh (Đánh dấu ảnh đã chọn, nhấn lần nữa để bỏ chọn)
    @Override
    public void onClickImage(View v, int index) {
        int selectedItemPosition = selectedPosition(this.data.get(index), adapterPhotoChooser);
        if (this.mode == GalleryFolderActivity.MULTI_SELECT) {
            if (selectedItemPosition != -1) {
                this.adapterReviewPhoto.removeSelectedPosition(this.adapterPhotoChooser.imageSelected.get(selectedItemPosition), selectedItemPosition);
                this.adapterPhotoChooser.removeSelectedPosition(selectedItemPosition, index);
            } else {

                this.adapterPhotoChooser.addSelected(this.data.get(index));
                this.adapterReviewPhoto.addSelected(this.data.get(index));

            }
            if (this.adapterPhotoChooser.imageSelected != null && this.adapterPhotoChooser.imageSelected.size() > 0)
                this.text_view_not_media.setVisibility(View.GONE);
            else
                this.text_view_not_media.setVisibility(View.VISIBLE);
        } else if (this.mode == GalleryFolderActivity.SINGLE_SELECT) {
            if (selectedItemPosition != -1) {
                this.adapterPhotoChooser.removeSelectedPosition(selectedItemPosition, index);
            } else {
                if (this.adapterPhotoChooser.imageSelected.size() > 0) {
                    this.adapterPhotoChooser.removeAllSelectedSingleClick();
                }
                this.adapterPhotoChooser.addSelected(this.data.get(index));
            }
        }
    }
    //Hàm trả về vị trí ảnh tương ứng với đường dẫn
    private int getImageFromPath(String path) {
        for (int i = 0; i < this.data.size(); i++) {
            if (path.equals(this.data.get(i).getPath()))
                return i;
        }
        return -1;
    }
    //Hàm sử lí sự kiện khi nhấn vào các ảnh đã được chọn (Xóa ảnh đã chọn)
    @Override
    public void onClickReviewImage(View v, int index) {
        ImageGalleryBean itemDelte = this.adapterReviewPhoto.data.get(index);

        this.adapterReviewPhoto.notifyItemRemoved(this.adapterReviewPhoto.data.indexOf(itemDelte));
        this.adapterReviewPhoto.data.remove(itemDelte);
        this.adapterReviewPhoto.imageSelected.remove(itemDelte);
        int indexInFolder = -1;
        if (this.data.indexOf(itemDelte) > 0) {
            indexInFolder = selectedPosition(this.data.get(this.data.indexOf(itemDelte)), adapterPhotoChooser);
        } else {
            indexInFolder = getImageFromPath(itemDelte.getPath());
        }

        this.adapterPhotoChooser.removeSelectedPosition(itemDelte, getImageFromPath(itemDelte.getPath()));

        if (this.adapterReviewPhoto.data != null && this.adapterReviewPhoto.data.size() > 0)
            this.text_view_not_media.setVisibility(View.GONE);
        else
            this.text_view_not_media.setVisibility(View.VISIBLE);
    }
    //Hàm gửi dữ liệu về Activity cần dùng
    private void sendData() {
        ArrayList<ImageGalleryBean> selectedImage = this.adapterPhotoChooser.imageSelected;
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("images", selectedImage);
        setResult(this.mode == GalleryFolderActivity.SINGLE_SELECT ? AppConfig.RESULT_CODE_SINGLESELECT : AppConfig.RESULT_CODE_MULTISELECT, intent);
        finish();
    }
    //hàm xử lí sự kiện Click
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_gallery:
                sendData();
                break;
            case R.id.text_view_done:
                sendData();
                break;
            default:
                break;
        }
    }
}