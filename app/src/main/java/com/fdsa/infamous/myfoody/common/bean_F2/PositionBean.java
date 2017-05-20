package com.fdsa.infamous.myfoody.common.bean_F2;

/**
 * Created by apple on 5/8/17.
 */

public class PositionBean {
    private double _lat;
    private double _long;

    //Hàm khởi tạo
    public PositionBean(double _lat, double _long) {
        this._lat = _lat;
        this._long = _long;
    }
    //Hàm lấy lat
    public double get_lat() {
        return _lat;
    }
    //Hàm gán lat
    public void set_lat(double _lat) {
        this._lat = _lat;
    }
    //Hàm lấy long
    public double get_long() {
        return _long;
    }
    //Hàm gán long
    public void set_long(double _long) {
        this._long = _long;
    }
}
