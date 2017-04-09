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

public class CollectionTab1Fragment extends Fragment {

    public CollectionTab1Fragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.collection_tab_1, container, false);
        return v;
    }
}
