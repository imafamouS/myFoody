package com.fdsa.infamous.myfoody.ui.menu.activity.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.common.myenum.Type;
import com.fdsa.infamous.myfoody.common.myinterface.IOnClickImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 5/11/17.
 */

/**
 * Adaper dùng trong AddNewPlaceAcitivty
 */
public class GallerySelectedFileAdapter extends RecyclerView.Adapter<GallerySelectedFileAdapter.ViewHolder> {

    public ArrayList<ImageGalleryBean> imageSelected = new ArrayList<>();
    Context context;
    IOnClickImage iOnClickImage;
    Type type;


    //Hàm khỏi  tạo adapter
    public GallerySelectedFileAdapter(Context context, ArrayList<ImageGalleryBean> imageSelected, Type type) {
        this.context = context;
        this.imageSelected = imageSelected;
        this.type = type;
    }
    //hàm gán sự kiện click vào ảnh
    public void setiOnClickImage(IOnClickImage iOnClickImage) {
        this.iOnClickImage = iOnClickImage;
    }
    //hàm tạo ViewHolder cho adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.gallery_image_item, parent, false), iOnClickImage, type);
    }
    //Hàm ảnh xạ dữ liệu lên viewhodler
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageGalleryBean item = (ImageGalleryBean) this.imageSelected.get(position);

        Glide.with(context).load("file://" + item.getPath())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image_view);

        if (isSelected(item)) {
            holder.image_view_check.setChecked(true);
        } else {
            holder.image_view_check.setChecked(false);
        }
        if (type == Type.IMAGESELECTED_INADDPLACE)
            holder.image_view_check.setCheckMarkDrawable(R.drawable.icon_remove2);
        return;
    }
    //Hàm kiểm tra ảnh đã được chọn
    private boolean isSelected(ImageGalleryBean image) {
        for (ImageGalleryBean selectedImage : this.imageSelected) {
            if (selectedImage.getPath().equals(image.getPath())) {
                return true;
            }
        }
        return false;
    }
    //Hàm lấy ID của item tại vị trí position
    public long getItemId(int position) {
        return position;
    }

    //Hàm trả về số lượng item trong adapter
    @Override
    public int getItemCount() {
        return imageSelected.size();
    }
    //Hàm gán data cho adapter
    public void setData(List<ImageGalleryBean> images) {
        this.imageSelected.clear();
        this.imageSelected.addAll(images);
    }
    //Hàm thêm dữ lệu vào data có sẵn của adapter
    public void addAll(List<ImageGalleryBean> images) {
        int startIndex = this.imageSelected.size();
        this.imageSelected.addAll(startIndex, images);
        notifyItemRangeInserted(startIndex, images.size());
    }
    //Hàm xóa ảnh đã chọn tại vị trí position;
    public void removeSelectedImage(int position) {
        this.imageSelected.remove(position);
        notifyItemRemoved(position);
    }

    //hàm xóa tất cả các ảnh đã chọn
    public void removeAllSelectedSingleClick() {
        this.imageSelected.clear();
        notifyDataSetChanged();
    }
    //ViewHodler
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int position;
        View v;
        ImageView image_view;
        CheckedTextView image_view_check;
        IOnClickImage iOnClickImage;
        Type type;
        //Hàm khởi tạo
        public ViewHolder(View v, IOnClickImage iOnClickImage, Type type) {
            super(v);
            this.v = v;
            this.type = type;
            image_view = (ImageView) v.findViewById(R.id.image_view_file);
            image_view_check = (CheckedTextView) v.findViewById(R.id.check_text_view);
            image_view_check.bringToFront();
            this.iOnClickImage = iOnClickImage;
            this.image_view_check.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iOnClickImage.onClickImage(v, getAdapterPosition());
        }
    }
}
