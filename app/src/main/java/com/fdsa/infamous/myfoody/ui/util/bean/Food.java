package com.fdsa.infamous.myfoody.ui.util.bean;

/**
 * Created by FDSA on 4/9/2017.
 */

public class Food {

    private String foodId;
    private String foodName;
    private String resId;
    private String nameRes;
    private String addressRes;
    private byte[] photo;
    private Comment comment;

    //Hàm khởi tạo món ăn
    public Food(String foodId, String foodName, String resId, String nameRes, String addressRes, byte[] photo, Comment comment) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.resId = resId;
        this.nameRes = nameRes;
        this.addressRes = addressRes;
        this.photo = photo;
        this.comment = comment;
    }

    //Hàm khởi tạo món ăn
    public Food(String foodId, String foodName, String resId, String nameRes, String addressRes, byte[] photo) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.resId = resId;
        this.nameRes = nameRes;
        this.addressRes = addressRes;
        this.photo = photo;

    }

    //Hàm get tên món ăn
    public String getFoodName() {
        return foodName;
    }

    //Hàm đặt tên món ăn
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    //Hàm get ID  món ăn
    public String getFoodId() {
        return foodId;
    }

    //Hàm set ID  món ăn
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    //Hàm get ID nhà hàng
    public String getResId() {
        return resId;
    }

    //Hàm set ID nhà hàng
    public void setResId(String resId) {
        this.resId = resId;
    }

    //Hàm get tên nhà hàng
    public String getNameRes() {
        return nameRes;
    }

    //Hàm set tên nhà hàng
    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    //Hàm get địa chỉ nhà hàng
    public String getAddressRes() {
        return addressRes;
    }

    //Hàm set địa chỉ nhà hàng
    public void setAddressRes(String addressRes) {
        this.addressRes = addressRes;
    }

    //Hàm get ảnh của món
    public byte[] getPhoto() {
        return photo;
    }

    //Hàm set ảnh của món
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    //Hàm get bình luận của món
    public Comment getComment() {
        return comment;
    }

    //Hàm set bình luận của món
    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
