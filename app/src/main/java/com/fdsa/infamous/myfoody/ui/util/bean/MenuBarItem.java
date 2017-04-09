package com.fdsa.infamous.myfoody.ui.util.bean;

/**
 * Created by FDSA on 3/22/2017.
 */

public class MenuBarItem {
    private String tittle;
    private String id;
    private String image;
    private boolean isSelected;

    //Hàm khởi tạo item cho TabMenu
    public MenuBarItem(String id, String tittle, String image, boolean isSelected) {
        this.tittle = tittle;
        this.image = image;
        this.isSelected = isSelected;
        this.id = id;
    }

    //Hàm get ID
    public String getId() {
        return id;
    }

    //Hàm set ID
    public void setId(String id) {
        this.id = id;
    }

    //Hàm get tiêu đề
    public String getTittle() {
        return tittle;
    }

    //Hàm set tiêu đề
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    //Hàm get ảnh (trả về id trong Drawable)
    public String getImage() {
        return image;
    }

    //Hàm set ảnh (trả về id trong Drawable)
    public void setImage(String image) {
        this.image = image;
    }

    //Hàm kiểm tra  xem item có được chọn không
    public boolean isSelected() {
        return isSelected;
    }

    //Hàm đặt trạng thái được chọn cho item
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
