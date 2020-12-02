package com.imkalpesh.stimulus.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.adapers.general.SliderImageAdaper;
import com.imkalpesh.stimulus.baseclasses.BaseActivity;
import com.imkalpesh.stimulus.databinding.ActivitySplashBinding;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    private final static String TAG = SplashActivity.class.getSimpleName();
    private static final Integer[] IMAGES = {R.drawable.spone, R.drawable.sptwo, R.drawable.spthree, R.drawable.spfour, R.drawable.spfive};
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private final Context mContext = this;
    private ActivitySplashBinding binding;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private Runnable Update;
    private Handler handler, handler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_splash);

        handler = new Handler();
        handler1 = new Handler();

        if (appPreference.getStringPreference(getResources().getString(R.string.pref_key)).equals("true")) {

            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

        } else {
            startWork();
        }

        /*Toast.makeText(mContext, ""+appPreference.getStringPreference(getResources().getString(R.string.pref_key)), Toast.LENGTH_SHORT).show();*/
    }


    private void startWork() {

        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
        binding.pager.setAdapter(new SliderImageAdaper(this, ImagesArray));

        /*CirclePageIndicator indicator = (CirclePageIndicator)
                view.findViewById(R.id.indicator);*/
        final float density = getResources().getDisplayMetrics().density;
        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager

        Update = new Runnable() {
            public void run() {

                    binding.process.setVisibility(View.VISIBLE);
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    if (currentPage == 4) {

                        handler1.removeCallbacks(Update);
                        Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        appPreference.setStringPreference(getResources().getString(R.string.pref_key), "true");

                        finishAfterTransition();
                        Log.d(TAG, "run: ");
                        currentPage = -1;
                    }
                    if (currentPage != -1) {
                        binding.pager.setCurrentItem(currentPage++, true);
                    }
                    Log.d(TAG, "run: ");
            }
        };


        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);
        handler.removeCallbacks(Update);

    }

}