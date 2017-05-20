package com.fdsa.infamous.myfoody.common.bean_F2;

public class UserBean {

    private String userid;
    private String name;
    private String phone;
    private String address;
    private String age;
    private String avatar;
    private String sex;
    private String marry;
    private String cover;
    private String firstname;
    private String datejoin;
    private String secretcode;
    //Hàm khởi tạo
    public UserBean(String userid, String name, String phone, String address, String age, String avatar, String sex, String marry, String cover, String firstname) {
        this.userid = userid;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.avatar = avatar;
        this.name = name;
        this.sex = sex;
        this.marry = marry;
        this.cover = cover;
        this.firstname = firstname;
    }
    //Hàm khởi tạo
    public UserBean(String userid, String name, String phone, String address, String age, String sex, String marry, String firstname) {
        this.userid = userid;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.marry = marry;
        this.firstname = firstname;
    }
    //hàm khởi tạo
    public UserBean() {

    }
    //Hàm khởi tạo
    public UserBean(String userid, String name) {
        this.userid = userid;
        this.name = name;
    }
    //Hàm lấy khóa bí mật
    public String getSecretcode() {
        return secretcode;
    }
    //Hàm gán mã bí mật
    public void setSecretcode(String secretcode) {
        this.secretcode = secretcode;
    }
    //Hàm lấy ngày tham gia
    public String getDatejoin() {
        return datejoin;
    }
    //Hàm gán ngày tham gia
    public void setDatejoin(String datejoin) {
        this.datejoin = datejoin;
    }
    //hàm lấy id suer
    public String getUserid() {
        return userid;
    }
    //Hàm gán id user
    public void setUserid(String userid) {
        this.userid = userid;
    }
    //hàm lấy số điện thoại user
    public String getPhone() {
        return phone;
    }
    //Hàm gán số điện thoại user
    public void setPhone(String phone) {
        this.phone = phone;
    }
    //Hàm lấy địa chỉ user
    public String getAddress() {
        return address;
    }
    //hàm gán địa chỉ user
    public void setAddress(String address) {
        this.address = address;
    }
    //hàm lấy tuổi
    public String getAge() {
        return age;
    }
    //Hàm gán tuổi
    public void setAge(String age) {
        this.age = age;
    }
    //Hàm lấy ảnh đại diện
    public String getAvatar() {
        return avatar;
    }
    //hàm gán ảnh đại diện
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    //Hàm lấy tên
    public String getName() {
        return name;
    }
    //hàm gán tên
    public void setName(String name) {
        this.name = name;
    }
    //Hàm lấy giới tính
    public String getSex() {
        return sex;
    }
    //hàm gán giới tính
    public void setSex(String sex) {
        this.sex = sex;
    }
    //hàm lấy tình trạng hôn nhân
    public String getMarry() {
        return marry;
    }
    //Hàm gán tình trạng hôn nhân
    public void setMarry(String marry) {
        this.marry = marry;
    }
    //hàm lấy ảnh cover
    public String getCover() {
        return cover;
    }
    //hàm gán ảnh cover
    public void setCover(String cover) {
        this.cover = cover;
    }
    //hàm lấy họ
    public String getFirstname() {
        return firstname;
    }
    //Hàm gán ho
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex='" + sex + '\'' +
                ", marry='" + marry + '\'' +
                ", cover='" + cover + '\'' +
                ", firstname='" + firstname + '\'' +
                ", datejoin='" + datejoin + '\'' +
                ", secretcode='" + secretcode + '\'' +
                '}';
    }
}