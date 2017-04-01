package com.fdsa.infamous.myfoody.ui.menu.views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.Global.GlobalFunction;
import com.fdsa.infamous.myfoody.Global.GlobalStaticData;
import com.fdsa.infamous.myfoody.ui.util.adapter.MoreItemAdapter;
import com.fdsa.infamous.myfoody.ui.util.bean.MoreItem;

import java.util.ArrayList;
import java.util.List;

import static com.fdsa.infamous.myfoody.Global.GlobalFunction.dpToPx;

/**
 * Created by FDSA on 4/1/2017.
 */

public class MoreItemView extends LinearLayout implements MoreItemAdapter.IMoreItemClick {

    public static final int ITEM_DEFAULT=0;



    private MoreItemAdapter adapter;
    private RecyclerView recyclerView;
    private List<MoreItem> moreItemList;
    private  myGridLayoutManger myGridLayoutManger;
    private int defaultPadding;
    private Context context;

    class myGridLayoutManger extends GridLayoutManager {
        public myGridLayoutManger(Context context, int spanCount) {
            super(context, spanCount);
        }
        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }

    public MoreItemView(Context context) {
        super(context);
        init(context);

    }

    public MoreItemView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }


    public MoreItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context=context;
        this.moreItemList = new ArrayList<>();
        this.recyclerView = new RecyclerView(context);
        moreItemList = GlobalStaticData.getListMoreItem(MoreItemView.ITEM_DEFAULT);
        defaultPadding= GlobalFunction.dpToPx(3.0f);
        myGridLayoutManger=new myGridLayoutManger(context, 2);

        this.recyclerView.setLayoutManager(myGridLayoutManger);
        this.adapter = new MoreItemAdapter(context, moreItemList, this);
        this.recyclerView.setPadding(defaultPadding, GlobalFunction.dpToPx(10),defaultPadding,GlobalFunction.dpToPx(10));
        this.recyclerView.setAdapter(this.adapter);
        this.addView(this.recyclerView);
    }

    public void setMoreItemListByType(int type){
        moreItemList.clear();
        moreItemList=GlobalStaticData.getListMoreItem(type);
        this.adapter.notifyDataSetChanged();
    }

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
}
