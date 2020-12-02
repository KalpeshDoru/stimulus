package com.imkalpesh.stimulus.baseclasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cittasolutions.cittalibrary.utils.AppPreference;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.utility.AppConstants;

public class BaseActivity extends AppCompatActivity {
    private final Context mContext = this;
    public AppPreference appPreference;
    public SharedPreferences pref;
    public long now;
    public long diffMillis;
    private String TAG = "BaseActivity";
    public SharedPreferences.Editor editor;
    public InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        appPreference = AppPreference.getInstance(mContext, AppConstants.PREFERENCE_FILE_NAME);

        MobileAds.initialize(mContext, initializationStatus -> {
        });

        pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        now = System.currentTimeMillis();
        diffMillis = now - pref.getLong("CurrentTimeMillis", 0);


    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(TAG);
        transaction.commit();
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.content);
    }

    public void bindingAdsView(AdView adView) {
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("TAG", "onAdLoaded: Banner ");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                Log.d("TAG", "onAdFailedToLoad: Banner ");
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                Log.d("TAG", "onAdOpened: Banner ");
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                // covers the screen.
                AppConstants.BANNER_ADS_CHECKER += 1;
                Log.d("TAG", "onAdClicked: Banner " + AppConstants.BANNER_ADS_CHECKER);

                if (AppConstants.BANNER_ADS_CHECKER >= 5 || AppConstants.INTERTITIAL_ADS_CHECKER >= 5) {
                    editor = pref.edit();
                    editor.putLong("CurrentTimeMillis", System.currentTimeMillis());
                    editor.apply();

                    finishAndRemoveTask();

                }
            }

            @Override
            public void onAdClicked() {
                Log.d("TAG", "onAdClicked: Banner ");
                // Code to be executed when the user clicks on an ad.

            }

            @Override
            public void onAdLeftApplication() {
                Log.d("TAG", "onAdLeftApplication: Banner ");
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                Log.d("TAG", "onAdClosed: Banner ");
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }


}
