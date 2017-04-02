package com.fdsa.infamous.myfoody.ui.menu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fdsa.infamous.myfoody.Global.GlobalStaticData;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.menu.views.AutoScrollViewPager;
import com.fdsa.infamous.myfoody.ui.util.adapter.MySlideShowBannerAdapter;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by FDSA on 3/28/2017.
 */

public class SlideShowBannerFragment extends Fragment {

    public AutoScrollViewPager view_pager_slide_show;
    FrameLayout frame_layout_banner_image_parent;
    Context context;
    List<Integer> mResources;
    View view;
    MySlideShowBannerAdapter adapter;
    CircleIndicator indicator;

    public SlideShowBannerFragment() {
        super();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setResourcesSlideShow(List<Integer> resources) {
        if (resources != null)
            mResources = resources;

        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.banner_image_layout, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {

        frame_layout_banner_image_parent = (FrameLayout) view.findViewById(R.id.frame_layout_banner_image_parent);
        mResources = GlobalStaticData.getDefaultImageSlideShow();
        view_pager_slide_show = (AutoScrollViewPager) view.findViewById(R.id.view_pager_slide_show);
        adapter = new MySlideShowBannerAdapter(getContext(), mResources);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);

        view_pager_slide_show.setAdapter(adapter);

        view_pager_slide_show.setInterval(1500);
        view_pager_slide_show.setStopScrollWhenTouch(false);
        view_pager_slide_show.setBorderAnimation(false);
        view_pager_slide_show.setCycle(true);
        view_pager_slide_show.startAutoScroll();

        indicator.setViewPager(view_pager_slide_show);
    }
}
