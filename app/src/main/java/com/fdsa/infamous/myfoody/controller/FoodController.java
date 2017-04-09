package com.fdsa.infamous.myfoody.controller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.fdsa.infamous.myfoody.AppConfig;
import com.fdsa.infamous.myfoody.database.DataAccess;
import com.fdsa.infamous.myfoody.global.GlobalStaticData;
import com.fdsa.infamous.myfoody.ui.util.bean.Comment;
import com.fdsa.infamous.myfoody.ui.util.bean.Food;
import com.fdsa.infamous.myfoody.ui.util.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/9/2017.
 */

public class FoodController extends DataAccess {

    UserController userController;

    public FoodController(Context context) {
        super(context);
        userController = new UserController(context);
    }

    @Override
    public List<?> executeSelect(String... params) {
        return null;
    }

    @Override
    public void open() {
        super.open();
    }

    @Override
    public void close() {
        super.close();
    }

    public List<Food> getListFood() {
        return getListFood(GlobalStaticData.getCurrentProvince().getIdProvince(), "", "l0", "moinhat");
    }

    public List<Food> getListFood(String province_id) {
        return getListFood(province_id, "", "l0", "moinhat");
    }

    public List<Food> getListFood(String province_id, String district_id, String res_type, String newest_type) {


        List<Food> list = new ArrayList<>();
        this.open();
        String db = "get_food_view";
        String query = "";

        if (district_id.equals("") && res_type.equals("l0")) {
            query = "SELECT * FROM " + db + " WHERE province_id='" + province_id + "'";
        } else if (!district_id.equals("") && res_type.equals("l0")) {
            query = "SELECT * FROM " + db + " WHERE district_id='" + district_id + "'";
        } else if (district_id.equals("") && !res_type.equals("l0")) {
            query = "SELECT * FROM " + db + " WHERE province_id='" + province_id + "'" + " and res_type='" + res_type + "'";
        } else if (!district_id.equals("") && !res_type.equals("l0")) {
            query = "SELECT * FROM " + db + " WHERE district_id='" + district_id + "'" + " and res_type='" + res_type + "'";
        }

        if (newest_type.equals("moinhat")) {
            query += " ";
        } else if (newest_type.equals("phobien") || newest_type.equals("xemnhieu")) {
            query += " order by total_reviews desc";
        }

        Cursor cursor = this.database.rawQuery(query, null);
        try {
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
                if (count > AppConfig.LIMIT_RECORD) {
                    break;
                }
                String id = cursor.getString(0);
                String resid = cursor.getString(1);
                String namefood = cursor.getString(2);
                byte[] photo = cursor.getBlob(3);
                String addressres = cursor.getString(7);
                String nameRes = cursor.getString(8);
                User user = userController.getUser("2");//User admin
                Comment comment = new Comment("idcommentAdmin", user, resid, 10.0, "Thêm bởi admin");
                Food item = new Food(id, namefood, resid, nameRes, addressres, photo);
                item.setComment(comment);

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
