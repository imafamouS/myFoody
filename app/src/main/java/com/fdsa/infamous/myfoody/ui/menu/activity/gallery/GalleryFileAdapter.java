package com.fdsa.infamous.myfoody.ui.menu.activity.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.common.myinterface.IOnClickImage;
import com.fdsa.infamous.myfoody.ui.menu.activity.gallery.viewholder.BaseGalleryViewHolder;
import com.fdsa.infamous.myfoody.ui.menu.activity.gallery.viewholder.ChoosePhotoHolder;
import com.fdsa.infamous.myfoody.ui.menu.activity.gallery.viewholder.ReviewPhotoHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 5/2/17.
 */

public class GalleryFileAdapter extends RecyclerView.Adapter<BaseGalleryViewHolder> {

    public static final int CHOOSE_PHOTO = 0;
    public static final int REVIEW_PHOTO = 1;

    Context context;
    ArrayList<ImageGalleryBean> data = new ArrayList<>();
    ArrayList<ImageGalleryBean> imageSelected = new ArrayList<>();
    IOnClickImage iOnClickImage;
    int type;


    //Hàm khởi tạo
    public GalleryFileAdapter(Context context, ArrayList<ImageGalleryBean> data, int type) {
        super();
        this.context = context;
        this.data = data;
        this.type = type;
    }
    //Hàm gán sự kiện khi click vào ảnh
    public void setiOnClickImage(IOnClickImage iOnClickImage) {
        this.iOnClickImage = iOnClickImage;
    }

    /**
     * Hàm tạo ViewHolder tương ứng với từng loại
     * Choose_Photo:Chọn ảnh
     * Review_Photo:các ảnh đã chọn
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (type) {
            case CHOOSE_PHOTO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_image_item, parent, false);
                return new ChoosePhotoHolder(context, view, iOnClickImage);

            case REVIEW_PHOTO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_image_chooseitem, parent, false);
                return new ReviewPhotoHolder(context, view, iOnClickImage);
            default:
                return null;
        }
    }
    //Hàm ánh xạ dữ liệu lên ViewHolder
    @Override
    public void onBindViewHolder(BaseGalleryViewHolder holder, int position) {
        if (type == CHOOSE_PHOTO) {
            ImageGalleryBean item = (ImageGalleryBean) this.data.get(position);
            holder.renderData(item, isSelected(item), position);
        } else {
            ImageGalleryBean item = (ImageGalleryBean) this.data.get(position);
            holder.renderData(item, true, position);
        }

    }

   /*@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.gallery_image_item, parent, false),iOnClickImage);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        Glide.with(context).load("file://" + item.getPath())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image_view);

        if (isSelected(item)) {
            holder.image_view_check.setChecked(true);
        } else {
            holder.image_view_check.setChecked(false);
        }

        return;
    }*/
    //Hàm kiểm tra ảnh đã được chọn hay chưa (đánh dấu)
    private boolean isSelected(ImageGalleryBean image) {
        for (ImageGalleryBean selectedImage : this.imageSelected) {
            if (selectedImage.getPath().equals(image.getPath())) {
                return true;
            }
        }
        return false;
    }
    //hàm lấy ID item tại position
    public long getItemId(int position) {
        return position;
    }

    //Hàm đếm số lượng item trong adapter
    @Override
    public int getItemCount() {
        return data.size();
    }
    //Hàm gán data cho adapter
    public void setData(List<ImageGalleryBean> images) {
        this.data.clear();
        this.data.addAll(images);
    }
    //Hàm thêm dữ liệu vào data có sẵn của adapter
    public void addAll(List<ImageGalleryBean> images) {
        int startIndex = this.data.size();
        this.data.addAll(startIndex, images);
        notifyItemRangeInserted(startIndex, images.size());
    }
    //Hàm thêm ảnh được chọn
    public void addSelected(ImageGalleryBean image) {
        for (int i = 0; i < imageSelected.size(); i++) {
            ImageGalleryBean item = imageSelected.get(i);
            if (item.getPath().equals(image.getPath())) {
                return;
            }
        }
        this.imageSelected.add(image);
        if (type == CHOOSE_PHOTO) {
            notifyItemChanged(this.data.indexOf(image));
        } else {
            add2Data(image);
            notifyItemChanged(this.imageSelected.indexOf(image));
        }

    }
    //Hàm xóa ảnh được chọn
    public void removeSelectedImage(ImageGalleryBean image) {
        for (int i = 0; i < imageSelected.size(); i++) {
            ImageGalleryBean item = imageSelected.get(i);
            if (item.getPath().equals(image.getPath())) {
                this.imageSelected.remove(i);
                break;
            }
        }
        notifyItemChanged(this.data.indexOf(image));
    }
    //Hàm xóa ảnh được chọn tại vị trí click
    public void removeSelectedPosition(int position, int clickPosition) {
        if (type == CHOOSE_PHOTO) {
            this.imageSelected.remove(position);
            notifyItemChanged(clickPosition);
        } else if (type == REVIEW_PHOTO) {
            this.data.remove(position);
            this.imageSelected.remove(position);
            //  notifyItemChanged(clickPosition);

        }

    }
    //Hàm xóa ảnh được chọn tại vị trí click
    public void removeSelectedPosition(ImageGalleryBean item, int clickPosition) {
        if (type == CHOOSE_PHOTO) {
            this.imageSelected.remove(item);
            notifyItemChanged(clickPosition);
        } else {
            this.notifyItemRemoved(this.data.indexOf(item));
            this.data.remove(item);
            this.imageSelected.remove(item);
        }

    }
    //hàm xóa tất cả các ảnh được chọn
    public void removeAllSelectedSingleClick() {
        this.imageSelected.clear();
        notifyDataSetChanged();
    }

    public void add2Data(ImageGalleryBean image) {
        for (int i = 0; i < data.size(); i++) {
            ImageGalleryBean item = data.get(i);
            if (item.getPath().equals(image.getPath())) {
                return;
            }
        }
        this.data.add(image);

    }
}