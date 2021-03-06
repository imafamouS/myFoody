package com.fdsa.infamous.myfoody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;

/**
 * Created by apple on 4/25/17.
 */

public class WelcomeActivity extends AppCompatActivity {

    public WelcomeActivity(){

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_layout);

        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
