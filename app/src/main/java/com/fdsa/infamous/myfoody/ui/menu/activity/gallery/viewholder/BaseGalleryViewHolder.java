package com.fdsa.infamous.myfoody.ui.menu.activity.gallery.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;

/**
 * Created by apple on 5/12/17.
 */


public abstract class BaseGalleryViewHolder extends RecyclerView.ViewHolder{
    public BaseGalleryViewHolder(View itemView) {
        super(itemView);
    }
    public abstract  void renderData(ImageGalleryBean data, boolean isItemSelect,int position);
}
