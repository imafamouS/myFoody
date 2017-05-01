package com.fdsa.infamous.myfoody.ui.menu.fragment.searchtab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fdsa.infamous.myfoody.R;

/**
 * Created by FDSA on 4/8/2017.
 */

public class SearchTabFragment extends Fragment {
    //Hàm khởi tạo
    public SearchTabFragment() {
        super();
    }


    //Hàm xử lí sự kiện khi fragment được tạo (khởi tạo view)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_tab_fragment, container, false);
    }
}
