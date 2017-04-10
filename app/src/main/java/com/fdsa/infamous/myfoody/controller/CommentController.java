package com.fdsa.infamous.myfoody.controller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.fdsa.infamous.myfoody.database.DataAccess;
import com.fdsa.infamous.myfoody.ui.util.bean.Comment;
import com.fdsa.infamous.myfoody.ui.util.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/8/2017.
 */

public class CommentController extends DataAccess {

    static String TAG;

    static {
        TAG = "CommentController";
    }

    UserController userController;

    //Hàm khởi tạo
    public CommentController(Context context) {
        super(context);
        userController = new UserController(context);
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

    //Hàm lấy danh sách các bình luận thông qua mã nhà hàng
    public List<Comment> getCommentList(String res_id) {
        List<Comment> list = new ArrayList<>();
        this.open();
        String db = "tbl_comment";
        String query = "SELECT * FROM " + db + " WHERE res_id ='" + res_id + "'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String _res_id = cursor.getString(1);
                // User uid = cursor.getString(2);
                String text = cursor.getString(3);
                Double rate = cursor.getDouble(4);
                User user = userController.getUser(cursor.getString(2)) == null ? new User("anomynous", "anomynous") : userController.getUser(cursor.getString(2));
                Comment item = new Comment(id, user, _res_id, rate, text);
                list.add(item);
            }
        } finally {
            cursor.close();
            this.close();
        }
        Log.d(TAG, "Get list ok");

        return list;
    }
}
