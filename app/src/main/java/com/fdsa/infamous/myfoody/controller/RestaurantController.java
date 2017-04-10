package com.fdsa.infamous.myfoody.controller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.fdsa.infamous.myfoody.AppConfig;
import com.fdsa.infamous.myfoody.database.DataAccess;
import com.fdsa.infamous.myfoody.global.GlobalFunction;
import com.fdsa.infamous.myfoody.global.GlobalStaticData;
import com.fdsa.infamous.myfoody.ui.util.bean.Comment;
import com.fdsa.infamous.myfoody.ui.util.bean.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/7/2017.
 */

public class RestaurantController extends DataAccess {
    CommentController commentController;
    MoreImageRestaurantController moreImageRestaurantController;

    //Hàm khởi tạo
    public RestaurantController(Context context) {
        super(context);
        commentController = new CommentController(context);
        moreImageRestaurantController = new MoreImageRestaurantController(context);
    }

    //Hàm mở kết nối
    @Override
    public void open() {
        super.open();
    }

    //Hàm đóng kết nối
    @Override
    public void close() {
        super.close();
    }

    @Override
    public List<?> executeSelect(String... params) {
        return null;
    }

    //Hàm lấy danhsach1 nhà hàng
    public List<Restaurant> getListRestaurant_Where2go() {
        return getListRestaurant_Where2go(GlobalStaticData.getCurrentProvince().getIdProvince(), "", "l0", "moinhat");
    }

    //Hàm lấy danhsach1 nhà hàng
    public List<Restaurant> getListRestaurant_Where2go(String province_id) {
        return getListRestaurant_Where2go(province_id, "", "l0", "moinhat");
    }

    //Hàm lấy danhsach1 nhà hàng
    public List<Restaurant> getListRestaurant_Where2go(String province_id, String district_id, String where_type, String newest_type) {
        List<Restaurant> list = new ArrayList<>();
        this.open();
        String db = "tbl_restaurant";
        String query = "";

        if (district_id.equals("") && where_type.equals("l0")) {
            query = "SELECT * FROM " + db + " WHERE province_id='" + province_id + "'";
        } else if (!district_id.equals("") && where_type.equals("l0")) {
            query = "SELECT * FROM " + db + " WHERE district_id='" + district_id + "'";
        } else if (district_id.equals("") && !where_type.equals("l0")) {
            query = "SELECT * FROM " + db + " WHERE province_id='" + province_id + "'" + " and where_type='" + where_type + "'";
        } else if (!district_id.equals("") && !where_type.equals("l0")) {
            query = "SELECT * FROM " + db + " WHERE district_id='" + district_id + "'" + " and where_type='" + where_type + "'";
        }

        if (newest_type.equals("moinhat")) {
            query += " ";
        } else if (newest_type.equals("phobien") || newest_type.equals("xemnhieu")) {
            query += " order by total_reviews  desc";
        }

        Cursor cursor = this.database.rawQuery(query, null);
        try {
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
                if (count > AppConfig.LIMIT_RECORD) {
                    break;
                }
                String id = cursor.getString(10);
                String name = cursor.getString(2);
                String add = cursor.getString(3);
                double rate = GlobalFunction.round(cursor.getDouble(4), 1);
                String phone = cursor.getString(5);
                byte[] img = cursor.getBlob(6);
                int totalview = cursor.getInt(7);

                Restaurant item = new Restaurant(id, name, add, rate, phone, img, totalview);

                List<Comment> comments = commentController.getCommentList(id);
                item.setComments(comments);

                List<byte[]> moreImages = moreImageRestaurantController.getListMoreImage(id);
                item.setSubImg(moreImages);

                list.add(item);

                Log.d("QUERY", query);
            }
        } finally {
            cursor.close();
            this.close();
        }
        return list;
    }
}
