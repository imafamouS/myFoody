package com.fdsa.infamous.myfoody.ui.menu.views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.common.bean_Foody1_do_not_use.MoreItem;
import com.fdsa.infamous.myfoody.common.myinterface.IMoreItemClick;
import com.fdsa.infamous.myfoody.ui.menu.adapter.MoreItemAdapter;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/1/2017.
 */

public class MoreItemView extends LinearLayout implements IMoreItemClick {

    public static final int ITEM_DEFAULT = 0;
    public static final int ITEM_TYPE_1 = 1;

    private MoreItemAdapter adapter;
    private RecyclerView recyclerView;
    private List<MoreItem> moreItemList;
    private myGridLayoutManger myGridLayoutManger;
    private int defaultPadding;
    private Context context;

    /***
     * Hàm khởi tạo MoreItemView (10 nút trên menu) kế thừa từ lớp Linearlayout
     *
     * @param context
     */
    public MoreItemView(Context context) {
        super(context);
        init(context);

    }

    public MoreItemView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MoreItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init(context);
    }

    /***
     * Hàm khởi tạo các view cho MoreItemView
     * Ý tưởng: Tạo 1 recycleview với layout dạng GridLayout có 2 cột, load dữ liệu từ nguồn dữ liệu có sẵn trong lớp GbloBalStaticData
     *
     * @param context
     */
    private void init(Context context) {


        this.context = context;
        this.moreItemList = new ArrayList<>();
        this.recyclerView = new RecyclerView(context);
        if (GlobalStaticData.TYPE_MOREITEM == -1) {
            moreItemList = GlobalStaticData.getListMoreItem(ITEM_DEFAULT);
        } else {
            moreItemList = GlobalStaticData.getListMoreItem(GlobalStaticData.TYPE_MOREITEM);
        }

        defaultPadding = GlobalFunction.dpToPx(3.0f);
        myGridLayoutManger = new myGridLayoutManger(context, 2);

        this.recyclerView.setLayoutManager(myGridLayoutManger);
        this.adapter = new MoreItemAdapter(context, moreItemList, this);
        this.recyclerView.setPadding(defaultPadding, GlobalFunction.dpToPx(10), defaultPadding, GlobalFunction.dpToPx(10));
        this.recyclerView.setAdapter(this.adapter);

        this.addView(this.recyclerView);
    }

    /***
     * Hàm gọi khi có sự kiện click trên layout của MoreItemView
     *
     * @param pos:vị trí đươc click
     */
    @Override
    public void moreItemClick(int pos) {
        switch (this.moreItemList.get(pos).getTittle()) {
            case "Gần tôi":
            case "Coupon":
            case "Đặt chỗ ưu đãi":
            case "Đặt giao hàng":
            case "E-card":
                Toast.makeText(context, "You clicked " + this.moreItemList.get(pos).getTittle(), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "You clicked " + this.moreItemList.get(pos).getTittle(), Toast.LENGTH_SHORT).show();
                break;

        }
    }

    class myGridLayoutManger extends GridLayoutManager {
        public myGridLayoutManger(Context context, int spanCount) {
            super(context, spanCount);
        }

        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }
}


   /* public void setMoreItemListByType(int type){
        moreItemList.clear();
        moreItemList=GlobalStaticData.getListMoreItem(type);
        this.adapter.notifyDataSetChanged();
    }
*/