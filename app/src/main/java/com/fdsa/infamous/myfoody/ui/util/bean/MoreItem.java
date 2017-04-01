package com.fdsa.infamous.myfoody.ui.util.bean;

import com.fdsa.infamous.myfoody.R;

/**
 * Created by FDSA on 3/31/2017.
 */

public class MoreItem {
    String tittle;
    MoreItemCode code;
    public MoreItem(String tittle, MoreItemCode code) {
        this.tittle = tittle;
        this.code = code;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

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

    public enum MoreItemCode {
        NEARBY,
        BOOK,
        ECARD,
        REVIEW,
        TOPMEMBER,
        COUPON,
        DELIVERY,
        GAME_FUN,
        BLOGS,
        VIDEO
    }
}
