package com.nytimes.articles.splash;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.nytimes.articles.R;
import com.nytimes.articles.dashboard.ui.DashboardActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int DELAY_LAUNCH_DURATION = 3000;// 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> startActivity(DashboardActivity.getIntent(SplashActivity.this)), DELAY_LAUNCH_DURATION);
    }
}
