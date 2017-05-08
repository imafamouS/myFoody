package com.fdsa.infamous.myfoody.common.bean_F2;

import java.util.List;

/**
 * Created by FDSA on 4/23/2017.
 */

public class RestaurantBean {
    private String id;
    private String title;
    private String address;
    private double avg_rating;
    private String phone;
    private int total_review;
    private String id_province;
    private String id_district;
    private String id_street;
    private String where_type;
    private String res_type;
    private String photo;
    private List<CommentResBean> listComment;
    private List<MoreImageRestaurantBean> listImage;
    private PositionBean position;
    private String openTime;
    private String closeTime;
    private double minCash;
    private double maxCash;

    public RestaurantBean(){}
    public RestaurantBean(String id, String title, String address,
                          double avg_rating, String phone, int total_review,
                          String id_province, String id_district, String id_street,
                          String where_type, String res_type, String photo, PositionBean position,
                          String openTime, String closeTime, double minCash, double maxCash) {

        this.id = id;
        this.title = title;
        this.address = address;
        this.avg_rating = avg_rating;
        this.phone = phone;
        this.total_review = total_review;
        this.where_type = where_type;
        this.res_type = res_type;
        this.id_province = id_province;
        this.id_district = id_district;
        this.id_street = id_street;
        this.photo = photo;
        this.position = position;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minCash = minCash;
        this.maxCash = maxCash;
    }

    public RestaurantBean(String id, String title, String address, double avg_rating, String phone, int total_review,
                          String photo, PositionBean position,
                          String openTime, String closeTime, double minCash, double maxCash) {
        super();
        this.id = id;
        this.title = title;
        this.address = address;
        this.avg_rating = avg_rating;
        this.phone = phone;
        this.total_review = total_review;
        this.photo = photo;
        this.position = position;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minCash = minCash;
        this.maxCash = maxCash;
    }

    public double getMinCash() {
        return minCash;
    }

    public void setMinCash(double minCash) {
        this.minCash = minCash;
    }

    public double getMaxCash() {
        return maxCash;
    }

    public void setMaxCash(double maxCash) {
        this.maxCash = maxCash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(double avg_rating) {
        this.avg_rating = avg_rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotal_review() {
        return total_review;
    }

    public void setTotal_review(int total_review) {
        this.total_review = total_review;
    }

    public String getWhere_type() {
        return where_type;
    }

    public void setWhere_type(String where_type) {
        this.where_type = where_type;
    }

    public String getRes_type() {
        return res_type;
    }

    public void setRes_type(String res_type) {
        this.res_type = res_type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId_province() {
        return id_province;
    }

    public void setId_province(String id_province) {
        this.id_province = id_province;
    }

    public String getId_district() {
        return id_district;
    }

    public void setId_district(String id_district) {
        this.id_district = id_district;
    }

    public String getId_street() {
        return id_street;
    }

    public List<CommentResBean> getListComment() {
        return listComment;
    }

    public void setListComment(List<CommentResBean> listComment) {
        this.listComment = listComment;
    }

    public void setId_street(String id_street) {
        this.id_street = id_street;
    }

    public List<MoreImageRestaurantBean> getListImage() {
        return listImage;
    }


    public void setListImage(List<MoreImageRestaurantBean> listImage) {
        this.listImage = listImage;
    }


    public PositionBean getPosition() {
        return position;
    }


    public void setPosition(PositionBean position) {
        this.position = position;
    }


    public String getOpenTime() {
        return openTime;
    }


    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }


    public String getCloseTime() {
        return closeTime;
    }


    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "RestaurantBean [id=" + id + ", title=" + title + ", address=" + address + ", avg_rating=" + avg_rating
                + ", phone=" + phone + ", total_review=" + total_review + ", id_province=" + id_province
                + ", id_district=" + id_district + ", id_street=" + id_street + ", where_type=" + where_type
                + ", res_type=" + res_type + ", photo=" + photo + ", listComment=" + listComment + ", listImage="
                + listImage + ", position=" + position + ", openTime=" + openTime + ", closeTime=" + closeTime
                + ", minCash=" + minCash + ", maxCash=" + maxCash + "]";
    }

}