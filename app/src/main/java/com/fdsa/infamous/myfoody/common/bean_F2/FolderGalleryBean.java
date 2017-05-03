package com.fdsa.infamous.myfoody.common.bean_F2;

import java.util.ArrayList;

/**
 * Created by apple on 5/2/17.
 */

public class FolderGalleryBean {
    String folder;
    ArrayList<ImageGalleryBean> imageInFolder;

    public FolderGalleryBean(){}

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public ArrayList<ImageGalleryBean> getImageInFolder() {
        return imageInFolder;
    }

    public void setImageInFolder(ArrayList<ImageGalleryBean> imageInFolder) {
        this.imageInFolder = imageInFolder;
    }
}

