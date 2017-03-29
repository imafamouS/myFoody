package com.fdsa.infamous.myfoody.ui.util;

/**
 * Created by FDSA on 3/22/2017.
 */

public class MenuBarItem {
    private String tittle;
    private int id;
    private int image;
    private boolean isSelected;

    public MenuBarItem(int id, String tittle, int image, boolean isSelected) {
        this.tittle = tittle;
        this.image = image;
        this.isSelected = isSelected;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
