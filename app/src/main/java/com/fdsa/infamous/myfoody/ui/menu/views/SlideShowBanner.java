package com.fdsa.infamous.myfoody.ui.menu.views;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static android.support.v7.widget.AppCompatDrawableManager.get;
import static com.fdsa.infamous.myfoody.R.id.viewpager;

/**
 * Created by FDSA on 3/28/2017.
 */

public class SlideShowBanner extends LinearLayout{

    LinearLayout linear_layout_banner_image_parent;
    ViewPager view_pager_slide_show;
    Context context;
    List<Integer> mResources;
    View view;
    mySlideShowBannerAdapter adapter;

    public SlideShowBanner(Context context){
        super(context);
        intitImage();
        initView();

    }
    public void setContext(Context context){
        this.context=context;
    }

    public void intitImage(){
       this.mResources=new ArrayList<>();

        mResources.add(R.drawable.icon_foody);
        mResources.add(R.drawable.icon_bottom_menu_user_selected);
    }

    private void initView(){
        view=inflate(getContext(), R.layout.banner_image_layout, this);
        linear_layout_banner_image_parent=(LinearLayout)findViewById(R.id.linear_layout_banner_image_parent);
        adapter=new mySlideShowBannerAdapter(getContext());
        view_pager_slide_show=(ViewPager)findViewById(R.id.view_pager_slide_show);
        view_pager_slide_show.setAdapter(adapter);

      //  view_pager_slide_show.startAutoScroll();


    }




    class mySlideShowBannerAdapter extends PagerAdapter{
        Context mContext;
        LayoutInflater mLayoutInflater;

        public mySlideShowBannerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            imageView.setImageResource(mResources.get(position));

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }


    }
}
