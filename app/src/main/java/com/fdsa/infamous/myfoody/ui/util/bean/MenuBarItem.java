package com.fdsa.infamous.myfoody.ui.util.bean;

/**
 * Created by FDSA on 3/22/2017.
 */

public class MenuBarItem {
    private String tittle;
    private String id;
    private String image;
    private boolean isSelected;

    public MenuBarItem(String id, String tittle, String image, boolean isSelected) {
        this.tittle = tittle;
        this.image = image;
        this.isSelected = isSelected;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
