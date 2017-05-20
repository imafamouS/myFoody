package com.fdsa.infamous.myfoody.common.bean_F2;

import java.util.ArrayList;

/**
 * Created by apple on 5/2/17.
 */

public class FolderGalleryBean {
    String folder;
    ArrayList<ImageGalleryBean> imageInFolder;
    //Hàm khởi tạo
    public FolderGalleryBean() {
    }
    //Hàm lấy tên Folder
    public String getFolder() {
        return folder;
    }
    //Hàm đặt tên Folder
    public void setFolder(String folder) {
        this.folder = folder;
    }
    //Hàm lấy danh sách ảnh trong folder
    public ArrayList<ImageGalleryBean> getImageInFolder() {
        return imageInFolder;
    }
    //Hàm gán danh sách ảnh
    public void setImageInFolder(ArrayList<ImageGalleryBean> imageInFolder) {
        this.imageInFolder = imageInFolder;
    }
}

