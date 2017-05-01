package com.fdsa.infamous.myfoody.util.controller_F1;

import android.content.Context;
import android.database.Cursor;

import com.fdsa.infamous.myfoody.util.database.DataAccess;
import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;

import java.util.List;

/**
 * Created by FDSA on 4/8/2017.
 */

public class UserController extends DataAccess {
    //Hàm khởi tạo
    public UserController(Context context) {
        super(context);
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

    //Hàm lấy UserBean thông qua uid
    public UserBean getUser(String uid) {
        this.open();
        UserBean userBean = null;
        String db = "tbl_user";
        String query = "SELECT * FROM " + db + " WHERE uid ='" + uid + "'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                byte[] avatar = cursor.getBlob(3);

                userBean = new UserBean(id, name);
               // userBean.setAvatar(avatar);
            }
        } finally {
            cursor.close();
            this.close();
        }
        return userBean;
    }
}
