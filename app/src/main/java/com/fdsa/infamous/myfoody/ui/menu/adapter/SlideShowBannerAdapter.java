package com.fdsa.infamous.myfoody.ui.menu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.R;

import java.util.List;

/**
 * Created by FDSA on 3/31/2017.
 */

public class SlideShowBannerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<Integer> mResources;

    public SlideShowBannerAdapter(Context context, List<Integer> mResources) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.banner_image_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view_banner_image_item);
        Glide.with(mContext).load(mResources.get(position)).into(imageView);
        // imageView.setImageResource(mResources.get(position));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


}
