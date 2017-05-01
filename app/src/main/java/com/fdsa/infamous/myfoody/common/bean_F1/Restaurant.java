package com.fdsa.infamous.myfoody.common.bean_F1;

import java.util.List;

/**
 * Created by FDSA on 4/7/2017.
 */

public class Restaurant {

    private List<byte[]> subImg;
    private String idRes;
    private String nameRes;
    private String addressRes;
    private double rating;
    private String phone;
    private byte[] img;
    private int totalView;
    private String resType;
    private String whereType;
    private List<Comment> comments;

    //Hàm khởi tạo Nhà Hàng
    public Restaurant(String idRes, String nameRes, String addressRes, double rating, byte[] img, String phone, int totalView, String resType, String whereType) {
        this.idRes = idRes;
        this.nameRes = nameRes;
        this.addressRes = addressRes;
        this.rating = rating;
        this.img = img;
        this.phone = phone;
        this.totalView = totalView;
        this.resType = resType;
        this.whereType = whereType;
    }

    //Hàm khởi tạo Nhà Hàng
    public Restaurant(String idRes, String nameRes, String addressRes, double rating, String phone, byte[] img, int totalView) {
        this.idRes = idRes;
        this.nameRes = nameRes;
        this.addressRes = addressRes;
        this.rating = rating;
        this.phone = phone;
        this.img = img;
        this.totalView = totalView;
    }

    //Hàm set ảnh của nhà hàng
    public Restaurant(byte[] img) {
        this.img = img;
    }

    //Hàm get ảnh  của nhà hàng
    public List<byte[]> getSubImg() {
        return subImg;
    }

    //Hàm set ảnh của nhà hàng
    public void setSubImg(List<byte[]> subImg) {
        this.subImg = subImg;
    }

    //Hàm get các comment  cho nhà hàng
    public List<Comment> getComments() {
        return comments;
    }

    //Hàm set các comment  cho nhà hàng
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    //Hàm get ID  của nhà hàng
    public String getIdRes() {
        return idRes;
    }

    //Hàm set ID  của nhà hàng
    public void setIdRes(String idRes) {
        this.idRes = idRes;
    }

    //Hàm get tên  của nhà hàng
    public String getNameRes() {
        return nameRes;
    }

    //Hàm set tên  của nhà hàng
    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    //Hàm get địa chỉ  của nhà hàng
    public String getAddressRes() {
        return addressRes;
    }

    //Hàm set địa chỉ  của nhà hàng
    public void setAddressRes(String addressRes) {
        this.addressRes = addressRes;
    }

    //Hàm get điểm  của nhà hàng
    public double getRating() {
        return rating;
    }

    //Hàm set điểm  của nhà hàng
    public void setRating(double rating) {
        this.rating = rating;
    }

    //Hàm get số điện thoại  của nhà hàng
    public String getPhone() {
        return phone;
    }

    //Hàm set số điện thoại  của nhà hàng
    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Hàm get ảnh chính của nhà hàng
    public byte[] getImg() {
        return img;
    }

    //Hàm set ảnh chính của nhà hàng
    public void setImg(byte[] img) {
        this.img = img;
    }

    //Hàm get tổng lượt xem của nhà hàng
    public int getTotalView() {
        return totalView;
    }

    //Hàm set tổng lượt xem của nhà hàng
    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    //Hàm get loại của nhà hàng (Tab Ở đâu)
    public String getResType() {
        return resType;
    }

    //Hàm set loại của nhà hàng (Tab Ở đâu)
    public void setResType(String resType) {
        this.resType = resType;
    }

    //Hàm get loại của nhà hàng (Tab Ăn gì)
    public String getWhereType() {
        return whereType;
    }

    //Hàm set loại của nhà hàng (Tab Ăn gì)
    public void setWhereType(String whereType) {
        this.whereType = whereType;
    }
}
