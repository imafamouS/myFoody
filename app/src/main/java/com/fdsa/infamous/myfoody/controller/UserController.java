package com.fdsa.infamous.myfoody.controller;

import android.content.Context;
import android.database.Cursor;

import com.fdsa.infamous.myfoody.database.DataAccess;
import com.fdsa.infamous.myfoody.ui.util.bean.User;

import java.util.List;

/**
 * Created by FDSA on 4/8/2017.
 */

public class UserController extends DataAccess {
    public UserController(Context context) {
        super(context);
    }

    @Override
    public void open() {
        super.open();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public List<?> executeSelect(String... params) {
        return null;
    }

    public User getUser(String uid) {
        this.open();
        User user = null;
        String db = "tbl_user";
        String query = "SELECT * FROM " + db + " WHERE uid ='" + uid + "'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                byte[] avatar = cursor.getBlob(3);

                user = new User(id, name);
                user.setAvatar(avatar);
            }
        } finally {
            cursor.close();
            this.close();
        }
        return user;
    }
}
