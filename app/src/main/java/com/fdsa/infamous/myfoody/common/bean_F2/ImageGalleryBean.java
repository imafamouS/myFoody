package com.fdsa.infamous.myfoody.common.bean_F2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by apple on 5/2/17.
 */

public class ImageGalleryBean implements Parcelable {

    String path;
    boolean isSelected;

    //Hàm xây dưng ImageGalleryBean từ Parceable
    public static final Parcelable.Creator<ImageGalleryBean> CREATOR = new Parcelable.Creator<ImageGalleryBean>() {
        public ImageGalleryBean createFromParcel(Parcel in) {
            return new ImageGalleryBean(in);
        }

        public ImageGalleryBean[] newArray(int size) {
            return new ImageGalleryBean[size];
        }
    };

    //Hàm khởi tạo
    public ImageGalleryBean() {
    }
    //Hàm khởi tạo
    public ImageGalleryBean(String path, boolean isSelected) {
        this.path = path;
        this.isSelected = isSelected;
    }
    //Hàm khởi tạo
    public ImageGalleryBean(Parcel dest) {
        this.path = dest.readString();
        this.isSelected = (dest.readInt() == 0) ? false : true;
    }
    //Hàm lấy đường dẫn ảnh
    public String getPath() {
        return path;
    }
    //Hàm gán đường dẫn ảnh
    public void setPath(String path) {
        this.path = path;
    }
    //hàm kiểm tra xem ảnh có đươc chọn
    public boolean isSelected() {
        return isSelected;
    }
    //Hàm gán ảnh có được chọn hay không
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    //Hàm chuyển ImageGalleryBean sang Parcelble
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeInt(isSelected ? 1 : 0);
    }

    @Override
    public String toString() {
        return "ImageGalleryBean{" +
                "path='" + path + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
