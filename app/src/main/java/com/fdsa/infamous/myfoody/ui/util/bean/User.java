package com.fdsa.infamous.myfoody.ui.util.bean;

/**
 * Created by FDSA on 4/8/2017.
 */

public class User {
    private String id;
    private String name;
    private String age;
    private String address;
    private byte[] avatar;

    //Hàm khởi tạo đối tượng user
    public User() {

    }

    //Hàm khởi tạo đối tượng user
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    //Hàm get Avatar của user
    public byte[] getAvatar() {
        return avatar;
    }

    //Hàm set Avatar của user
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    //Hàm get id của user
    public String getId() {
        return id;
    }

    //Hàm set id của user
    public void setId(String id) {
        this.id = id;
    }

    //Hàm get tên của user
    public String getName() {
        return name;
    }

    //Hàm set tên của user
    public void setName(String name) {
        this.name = name;
    }

    //Hàm get tuổi của user
    public String getAge() {
        return age;
    }

    //Hàm set tuổi của user
    public void setAge(String age) {
        this.age = age;
    }

    //Hàm get địa chỉ của user
    public String getAddress() {
        return address;
    }

    //Hàm set địa chỉ của user
    public void setAddress(String address) {
        this.address = address;
    }
}
