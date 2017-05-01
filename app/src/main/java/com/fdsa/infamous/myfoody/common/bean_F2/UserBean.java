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

    public UserBean(String userid,String name, String phone, String address, String age, String avatar,String sex,String marry,String cover,String firstname) {
        this.userid = userid;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.avatar = avatar;
        this.name=name;
        this.sex=sex;
        this.marry=marry;
        this.cover=cover;
        this.firstname=firstname;
    }

    public UserBean(String userid,String name, String phone, String address, String age,String sex,String marry,String firstname) {
        this.userid = userid;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.name=name;
        this.sex=sex;
        this.marry=marry;
        this.firstname=firstname;
    }
    public UserBean(){

    }

    public String getSecretcode() {
        return secretcode;
    }

    public void setSecretcode(String secretcode) {
        this.secretcode = secretcode;
    }

    public UserBean(String userid, String name) {
        this.userid = userid;
        this.name = name;
    }

    public String getDatejoin() {
        return datejoin;
    }

    public void setDatejoin(String datejoin) {
        this.datejoin = datejoin;
    }

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getFirstname() {
        return firstname;
    }

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