package com.fdsa.infamous.myfoody.ui.menu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_Foody1_do_not_use.MoreItem;
import com.fdsa.infamous.myfoody.common.myinterface.IMoreItemClick;

import java.util.List;

/**
 * Created by FDSA on 3/31/2017.
 */

/**
 * Adapter cho Recycleview dùng trong MoreItemView
 **/
public class MoreItemAdapter extends Adapter<MoreItemAdapter.MoreItemViewHolder> {

    static Context context;
    IMoreItemClick itemClick;
    /**
     * interface của sự kiện khi nhấn vào các layout
     **/
    List<MoreItem> moreItemList;

    //Hàm khởi tạo
    public MoreItemAdapter(Context context, List<MoreItem> moreItemList, IMoreItemClick itemClick) {
        super();
        this.context = context;
        this.moreItemList = moreItemList;
        this.itemClick = itemClick;
    }

    /***
     * Hàm hiện dữ liệu lên view
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MoreItemViewHolder holder, int position) {
        holder.renderData(this.moreItemList.get(position));
    }

    /***
     * Hàm khởi tạo ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MoreItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreItemViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.home_more_item_layout, parent, false), this.itemClick);
    }

    /***
     * Hàm get id của phần tử thứ posion
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /***
     * Hàm get số lượng phần tử trong adapter
     * @return
     */
    @Override
    public int getItemCount() {
        return moreItemList.size();
    }


    /**
     * Class dùng trong việc lưu lại các view để được lấy ID để không cần phải thực hiện findViewById nhiều lần
     **/
    static class MoreItemViewHolder extends ViewHolder implements View.OnClickListener {
        ImageView image_view_more_item_menu;
        TextView text_view_more_item_menu;
        IMoreItemClick itemClick;

        //Hàm khởi tạo ViewHolder
        public MoreItemViewHolder(View itemView, IMoreItemClick itemClick) {
            super(itemView);
            this.image_view_more_item_menu = (ImageView) itemView.findViewById(R.id.image_view_more_item_menu);
            this.text_view_more_item_menu = (TextView) itemView.findViewById(R.id.text_view_more_item_menu);
            this.itemClick = itemClick;
            itemView.setOnClickListener(this);
        }

        //Hàm hiện data cho các view
        public void renderData(MoreItem data) {
            int resIcon = data.getImage();
            if (resIcon == -1) {
                this.image_view_more_item_menu.setVisibility(View.GONE);
            } else {
                //this.image_view_more_item_menu.setImageResource(resIcon);
                Glide.with(context).load(resIcon).into(this.image_view_more_item_menu);
                this.image_view_more_item_menu.setVisibility(View.VISIBLE);
            }
            this.text_view_more_item_menu.setText(data.getTittle());

        }

        //Sự kiện click vào các item của MoreItemView thì interface IMoreItemCLick được thực hiện
        @Override
        public void onClick(View view) {
            this.itemClick.moreItemClick(getLayoutPosition());
        }
    }

}
