package com.fdsa.infamous.myfoody.common.myinterface;

import android.view.View;

/**
 * Created by apple on 5/2/17.
 */
//Interface dùng trong việc chọn hình ảnh để upload
public interface IOnClickImage {
    void onClickImage(View v, int index);

    void onClickReviewImage(View v, int index);
}
