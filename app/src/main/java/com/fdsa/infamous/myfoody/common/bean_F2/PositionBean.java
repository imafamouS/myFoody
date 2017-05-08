package com.fdsa.infamous.myfoody.common.bean_F2;

/**
 * Created by apple on 5/8/17.
 */

public class PositionBean {
    private double _lat;
    private double _long;


    public PositionBean(double _lat, double _long) {
        this._lat = _lat;
        this._long = _long;
    }
    public double get_lat() {
        return _lat;
    }
    public void set_lat(double _lat) {
        this._lat = _lat;
    }
    public double get_long() {
        return _long;
    }
    public void set_long(double _long) {
        this._long = _long;
    }
}
