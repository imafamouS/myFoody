package com.fdsa.infamous.myfoody.ui.menu.fragment.collectiontab;

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

public class CollectionTab2Fragment extends Fragment {
    //Hàm khởi tạo
    public CollectionTab2Fragment() {
        super();
    }

    /**
     * Hàm xử lí sự kiện khi fragment được khởi tạo (khởi tạo view)
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.collection_tab_2, container, false);
    }
}
