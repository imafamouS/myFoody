package com.fdsa.infamous.myfoody.common.bean_F2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by apple on 5/2/17.
 */

public class ImageGalleryBean  implements Parcelable {
    String path;
    boolean isSelected;

    public ImageGalleryBean() {
    }

    public ImageGalleryBean(String path, boolean isSelected) {
        this.path = path;
        this.isSelected = isSelected;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ImageGalleryBean(Parcel dest){
        this.path=dest.readString();
        this.isSelected=(dest.readInt()==0)? false : true;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.path);
        dest.writeInt(isSelected ? 1 : 0);
    }
    public static final Parcelable.Creator<ImageGalleryBean> CREATOR = new Parcelable.Creator<ImageGalleryBean>()
    {
        public ImageGalleryBean createFromParcel(Parcel in)
        {
            return new ImageGalleryBean(in);
        }
        public ImageGalleryBean[] newArray(int size)
        {
            return new ImageGalleryBean[size];
        }
    };

    @Override
    public String toString() {
        return "ImageGalleryBean{" +
                "path='" + path + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
