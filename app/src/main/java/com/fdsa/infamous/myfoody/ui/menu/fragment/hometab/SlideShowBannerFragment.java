package com.fdsa.infamous.myfoody.ui.menu.fragment.hometab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.global.GlobalStaticData;
import com.fdsa.infamous.myfoody.ui.menu.views.AutoScrollViewPager;
import com.fdsa.infamous.myfoody.ui.util.adapter.SlideShowBannerAdapter;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by FDSA on 3/28/2017.
 */


public class SlideShowBannerFragment extends Fragment {

    public static final int TYPE_HAVE_ADS = 1;
    public static final int TYPE_NO_ADS = 2;

    public AutoScrollViewPager view_pager_slide_show;
    FrameLayout frame_layout_banner_image_parent;
    Context context;
    List<Integer> mResources;
    View view;
    SlideShowBannerAdapter adapter;
    CircleIndicator indicator;

    //Hàm khởi tạo
    public SlideShowBannerFragment() {
        super();
    }

    //Hàm set context
    public void setContext(Context context) {
        this.context = context;
    }

    //Hàm set dữ liệu cho fragment
    public void setResourcesSlideShow(List<Integer> resources) {
        if (resources != null) {
            mResources = resources;
            adapter.notifyDataSetChanged();
        } else {
            adapter = null;
        }

    }

    /**
     * Hàm xử lí sự kiện khi fragment được tạo (khởi tạo view)
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.banner_image_layout, container, false);
        initView(view);
        return view;
    }

    /**
     * Hàm khởi tạo view
     * @param view
     */
    public void initView(View view) {

        frame_layout_banner_image_parent = (FrameLayout) view.findViewById(R.id.frame_layout_banner_image_parent);
        mResources = GlobalStaticData.getImageSlideShow(TYPE_HAVE_ADS);
        view_pager_slide_show = (AutoScrollViewPager) view.findViewById(R.id.view_pager_slide_show);
        adapter = new SlideShowBannerAdapter(getContext(), mResources);
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
