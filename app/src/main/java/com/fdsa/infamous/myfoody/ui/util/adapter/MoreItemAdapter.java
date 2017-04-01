package com.fdsa.infamous.myfoody.ui.util.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.bean.MoreItem;

import java.util.List;

/**
 * Created by FDSA on 3/31/2017.
 */

public class MoreItemAdapter extends Adapter<MoreItemAdapter.MoreItemViewHolder> {

    IMoreItemClick itemClick;
    List<MoreItem> moreItemList;
    Context context;
    public MoreItemAdapter(Context context, List<MoreItem> moreItemList, IMoreItemClick itemClick) {
        super();
        this.context = context;
        this.moreItemList = moreItemList;
        this.itemClick = itemClick;
    }

    @Override
    public void onBindViewHolder(MoreItemViewHolder holder, int position) {
        holder.onBind(this.moreItemList.get(position));
    }

    @Override
    public MoreItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.home_more_item_layout, parent, false), this.itemClick);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return moreItemList.size();
    }

    public interface IMoreItemClick {
        void moreItemClick(int pos);
    }

    static class MoreItemViewHolder extends ViewHolder implements View.OnClickListener {
        ImageView image_view_more_item_menu;
        TextView text_view_more_item_menu;
        IMoreItemClick itemClick;

        public MoreItemViewHolder(View itemView, IMoreItemClick itemClick) {
            super(itemView);
            this.image_view_more_item_menu = (ImageView) itemView.findViewById(R.id.image_view_more_item_menu);
            this.text_view_more_item_menu = (TextView) itemView.findViewById(R.id.text_view_more_item_menu);
            this.itemClick = itemClick;
            itemView.setOnClickListener(this);
        }

        public void onBind(MoreItem data) {
            int resIcon = data.getImage();
            if (resIcon == -1) {
                this.image_view_more_item_menu.setVisibility(View.GONE);
            } else {
                this.image_view_more_item_menu.setImageResource(resIcon);
                this.image_view_more_item_menu.setVisibility(View.VISIBLE);
            }
            this.text_view_more_item_menu.setText(data.getTittle());

        }

        @Override
        public void onClick(View view) {
            this.itemClick.moreItemClick(getLayoutPosition());
        }
    }

}
