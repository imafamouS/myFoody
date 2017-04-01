package com.fdsa.infamous.myfoody.Global;

import com.fdsa.infamous.myfoody.ui.menu.views.MoreItemView;
import com.fdsa.infamous.myfoody.ui.util.bean.MoreItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by FDSA on 3/26/2017.
 */

public class GlobalStaticData {

    public static List<MoreItem> getListMoreItem(int type) {
        List<MoreItem> list = new ArrayList<>();

        if(type == MoreItemView.ITEM_DEFAULT){
            list.add(new MoreItem("Gần tôi", MoreItem.MoreItemCode.NEARBY));
            list.add(new MoreItem("Coupon", MoreItem.MoreItemCode.COUPON));
            list.add(new MoreItem("Đặt chỗ ưu đãi", MoreItem.MoreItemCode.BOOK));
            list.add(new MoreItem("Đặt giao hàng", MoreItem.MoreItemCode.DELIVERY));
            list.add(new MoreItem("E-card", MoreItem.MoreItemCode.ECARD));

            list.add(new MoreItem("Game & Fun", MoreItem.MoreItemCode.GAME_FUN));
            list.add(new MoreItem("Bình luận", MoreItem.MoreItemCode.REVIEW));
            list.add(new MoreItem("Blogs", MoreItem.MoreItemCode.BLOGS));
            list.add(new MoreItem("Top thành viên", MoreItem.MoreItemCode.TOPMEMBER));
            list.add(new MoreItem("Video", MoreItem.MoreItemCode.VIDEO));
        }

        return list;
    }


}
