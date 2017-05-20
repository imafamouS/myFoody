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

    //Hàm khởi tạo
    public RestaurantBean() {
    }
    //Hàm khởi tạo
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
    //Hàm khởi tạo
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
    //Hàm lấy giá tiền thấp nhất
    public double getMinCash() {
        return minCash;
    }
    //Hàm gán giá tiền thấp nhất
    public void setMinCash(double minCash) {
        this.minCash = minCash;
    }
    //Hàm lấy giá tiền cao nhất
    public double getMaxCash() {
        return maxCash;
    }
    //Hàm  giá tiền gán cao nhất
    public void setMaxCash(double maxCash) {
        this.maxCash = maxCash;
    }
    //Hàm lấy id nhà hàng
    public String getId() {
        return id;
    }
    //hàm gán id nhà hàng
    public void setId(String id) {
        this.id = id;
    }
    //Hàm gán tên nhà hàng
    public String getTitle() {
        return title;
    }
    //Hàm lấy tên nhà hàng
    public void setTitle(String title) {
        this.title = title;
    }
    //hàm lấy địa chỉ nàh hàng
    public String getAddress() {
        return address;
    }
    //hàm gán địa chỉ nhà hàng
    public void setAddress(String address) {
        this.address = address;
    }
    //Hàm lấy điểm đánh giá
    public double getAvg_rating() {
        return avg_rating;
    }
    //Hàm gán điểm đánh giá
    public void setAvg_rating(double avg_rating) {
        this.avg_rating = avg_rating;
    }
    //Hàm lấy SDT
    public String getPhone() {
        return phone;
    }
    //Hàm gán SDT
    public void setPhone(String phone) {
        this.phone = phone;
    }
    //Hàm lấy tổng lượt xem
    public int getTotal_review() {
        return total_review;
    }
    //Hàm gán tổng lượt xem
    public void setTotal_review(int total_review) {
        this.total_review = total_review;
    }
    //Hàm lấy loại địa điểm
    public String getWhere_type() {
        return where_type;
    }
    //Hàm gán loại địa điểm
    public void setWhere_type(String where_type) {
        this.where_type = where_type;
    }
    //Hàm lấy loại nhà hàng
    public String getRes_type() {
        return res_type;
    }
    //Hàm lấy loại nhà hàng
    public void setRes_type(String res_type) {
        this.res_type = res_type;
    }
    //Hàm lấy ảnh chính
    public String getPhoto() {
        return photo;
    }
    //Hàm gán ảnh chính
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    //Hàm lấy id tỉnh
    public String getId_province() {
        return id_province;
    }
    //Hàm gán id tỉnh
    public void setId_province(String id_province) {
        this.id_province = id_province;
    }
    //Hàm lấy id huyện
    public String getId_district() {
        return id_district;
    }
    //Hàm gán id huyện
    public void setId_district(String id_district) {
        this.id_district = id_district;
    }
    //Hàm lấy id đường
    public String getId_street() {
        return id_street;
    }
    //hàm gán id đường
    public void setId_street(String id_street) {
        this.id_street = id_street;
    }
    //Hàm lấy danh sách bình luận
    public List<CommentResBean> getListComment() {
        return listComment;
    }
    //hàm gán danh sách bình luận
    public void setListComment(List<CommentResBean> listComment) {
        this.listComment = listComment;
    }
    //Hàm lấy danh sách ảnh thêm của nhà hàng
    public List<MoreImageRestaurantBean> getListImage() {
        return listImage;
    }

    //Hàm gán danh sách ảnh thêm của nhà hàng
    public void setListImage(List<MoreImageRestaurantBean> listImage) {
        this.listImage = listImage;
    }

    //Hàm lấy địa điểm
    public PositionBean getPosition() {
        return position;
    }

    //Hàm gán  địa điểm
    public void setPosition(PositionBean position) {
        this.position = position;
    }

    //Hàm lấy thời gian mờ cửa
    public String getOpenTime() {
        return openTime;
    }

    //Hàm gán thời gian mở cửa
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    //Hàm lấy thời gian đóng cửa
    public String getCloseTime() {
        return closeTime;
    }

    //Hàm gán thời gian đóng cửa
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