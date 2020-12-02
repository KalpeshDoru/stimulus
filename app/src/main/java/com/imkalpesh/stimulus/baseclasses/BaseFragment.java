package com.imkalpesh.stimulus.baseclasses;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.utility.AppConstants;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public abstract class BaseFragment extends Fragment {

   /* private final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);*/
    public Activity activity;
    public Fragment fragment;
    public SharedPreferences pref;
    public long now;
    public long diffMillis;
    public SharedPreferences.Editor editor;
    public RewardedAd rewardedAd;
    public RewardedAdLoadCallback adLoadCallback;
    public InterstitialAd mInterstitialAd;

    public BaseFragment() {
    }

    public abstract Activity registerActivity();

    public abstract Fragment registerFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getActivity(), initializationStatus -> {
        });

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        now = System.currentTimeMillis();
        diffMillis = now - pref.getLong("CurrentTimeMillis", 0);


        if (diffMillis >= (3600000 * 12)) {
            AppConstants.isSaved = false;
            Log.d("TAG", "Adds Shown: ");

        } else {
            // too early
            Log.d("TAG", "Ads shown After 12Hr later: ");
            AppConstants.isSaved = true;

        }
    }

    public void bindingAdsView(AdView adView) {
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("TAG", "onAdLoaded Banner: ");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.d("TAG", "onAdFailedToLoad Banner: ");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                // covers the screen.
                AppConstants.BANNER_ADS_CHECKER += 1;
                Log.d("TAG", "onAdClicked: " + AppConstants.BANNER_ADS_CHECKER);

                if (AppConstants.BANNER_ADS_CHECKER >= 5 || AppConstants.INTERTITIAL_ADS_CHECKER >= 5) {
                    editor = pref.edit();
                    editor.putLong("CurrentTimeMillis", System.currentTimeMillis());
                    editor.apply();
                    /*scheduler.shutdown();*/
                    Objects.requireNonNull(getActivity()).finishAndRemoveTask();

                }
            }

            @Override
            public void onAdClicked() {
                Log.d("TAG", "onAdClicked Banner: ");
                // Code to be executed when the user clicks on an ad.

            }

            @Override
            public void onAdLeftApplication() {
                Log.d("TAG", "onAdLeftApplication Banner: ");
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                Log.d("TAG", "onAdClosed Banner: ");
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = registerActivity();

        fragment = registerFragment();
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        // For FileURIExposedException Handling
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        // To Prevent NetworkOnMainThread Exception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public RewardedAd createAndLoadRewardedAd() {
        rewardedAd = new RewardedAd(Objects.requireNonNull(getActivity()), getString(R.string.st_rewared_one_id));
        adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                Log.d("TAG", "onRewardedAdLoaded: ");
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                Log.d("TAG", "onRewardedAdFailedToLoad: " + adError);
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        return rewardedAd;
    }

    public void onRewardedAdClosed() {
        this.rewardedAd = createAndLoadRewardedAd();
    }

    public void prepareIntertitialAds() {

        mInterstitialAd = new InterstitialAd(Objects.requireNonNull(getActivity()));
        //main
        mInterstitialAd.setAdUnitId(getString(R.string.st_interstial_one_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        attachingListnerwithInAds(mInterstitialAd);

    }

    public void attachingListnerwithInAds(InterstitialAd mInterstitialAd) {
        this.mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("TAG", "onAdLoaded IN : ");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                Log.d("TAG", "onAdFailedToLoad IN: ");
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.d("TAG", "onAdOpened IN : ");
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                AppConstants.INTERTITIAL_ADS_CHECKER += 1;

                Log.d("TAG", "onAdClicked IN: " + AppConstants.INTERTITIAL_ADS_CHECKER);

                if (AppConstants.INTERTITIAL_ADS_CHECKER >= 5 || AppConstants.BANNER_ADS_CHECKER >= 5) {
                    editor = pref.edit();
                    editor.putLong("CurrentTimeMillis", System.currentTimeMillis());
                    editor.apply();
                    /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);*/

                    /*scheduler.shutdown();*/

                    Objects.requireNonNull(getActivity()).finishAndRemoveTask();
                }

            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.d("TAG", "onAdLeftApplication IN : ");
            }

            @Override
            public void onAdClosed() {
                Log.d("TAG", "onAdClosed IN: ");

            }
        });
    }

}
