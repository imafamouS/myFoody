package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fdsa.infamous.myfoody.R;

/**
 * Created by apple on 5/16/17.
 */

public abstract class BaseSlideActivity extends AppCompatActivity {
    public BaseSlideActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
