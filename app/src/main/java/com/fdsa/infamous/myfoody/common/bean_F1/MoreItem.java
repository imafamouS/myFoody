package com.fdsa.infamous.myfoody.common.bean_F1;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.myenum.MoreItemCode;

/**
 * Created by FDSA on 3/31/2017.
 */

public class MoreItem {
    private String tittle;
    private MoreItemCode code;
    public MoreItem(String tittle, MoreItemCode code) {
        this.tittle = tittle;
        this.code = code;
    }

    //Hàm get Tiêu đề
    public String getTittle() {
        return tittle;
    }

    //Hàm set Tiêu đề
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /***
     * Hàm trả về id của drawable hình của đối tượng moreItem
     *
     * @return
     */
    public int getImage() {
        int img = -1;
        if (code == null) {
            img = -1;
        } else {
            if (code.equals(MoreItemCode.NEARBY)) {
                img = R.drawable.icon_more_item_nearby;
            }
            if (code.equals(MoreItemCode.BOOK)) {
                img = R.drawable.icon_more_item_booking;
            }
            if (code.equals(MoreItemCode.ECARD)) {
                img = R.drawable.icon_more_item_ecard;
            }
            if (code.equals(MoreItemCode.REVIEW)) {
                img = R.drawable.icon_more_item_review;
            }
            if (code.equals(MoreItemCode.TOPMEMBER)) {
                img = R.drawable.icon_more_item_top_user;
            }
            if (code.equals(MoreItemCode.COUPON)) {
                img = R.drawable.icon_more_item_ecoup;
            }
            if (code.equals(MoreItemCode.DELIVERY)) {
                img = R.drawable.icon_more_item_delivery;
            }
            if (code.equals(MoreItemCode.GAME_FUN)) {
                img = R.drawable.icon_more_item_game_fun;
            }
            if (code.equals(MoreItemCode.BLOGS)) {
                img = R.drawable.icon_more_item_blogs;
            }
            if (code.equals(MoreItemCode.VIDEO)) {
                img = R.drawable.icon_more_item_video;
            }
        }
        return img;
    }


}
