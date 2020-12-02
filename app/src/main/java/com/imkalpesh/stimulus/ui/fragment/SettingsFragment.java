package com.imkalpesh.stimulus.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.imkalpesh.stimulus.BuildConfig;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.baseclasses.BaseAdapter;
import com.imkalpesh.stimulus.baseclasses.BaseFragment;
import com.imkalpesh.stimulus.databinding.ActivityGridBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.ButtomSheetModel;
import com.imkalpesh.stimulus.models.SettingModel;
import com.imkalpesh.stimulus.utility.AppConstants;

import java.util.ArrayList;
import java.util.Objects;

public class SettingsFragment extends BaseFragment implements View.OnClickListener, CommonActionListener {
    private ArrayList<SettingModel> settingModelArrayList;
    private ActivityGridBinding binding;
    private BottomSheetBehavior sheetBehavior;
    @Override
    public Activity registerActivity() {
        return getActivity();
    }

    @Override
    public Fragment registerFragment() {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareIntertitialAds();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_grid, container, false);
        sheetBehavior = BottomSheetBehavior.from(binding.buttonSheet.rlAboutUs);

        binding.actionBar.actionBarMainLayout.setVisibility(View.GONE);
        binding.tvVersion.setVisibility(View.VISIBLE);
        startWork();
        if (AppConstants.isSaved) {
            binding.adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
        }

        bindingAdsView(binding.adView);


        binding.rvGrid.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        BaseAdapter baseAdapter = new BaseAdapter(getActivity(), settingModelArrayList, R.layout.item_setting, this);
        binding.rvGrid.setAdapter(baseAdapter);
        return binding.getRoot();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void startWork() {
        settingModelArrayList = new ArrayList<>();

        settingModelArrayList.add(new SettingModel(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.mail), "Contact Us"));
        settingModelArrayList.add(new SettingModel(ContextCompat.getDrawable(getActivity(), R.drawable.info), "About us"));
        settingModelArrayList.add(new SettingModel(ContextCompat.getDrawable(getActivity(), R.drawable.ic_share), "Share App"));
        settingModelArrayList.add(new SettingModel(ContextCompat.getDrawable(getActivity(), R.drawable.rating), "Rate App"));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onViewClick() {

    }

    @Override
    public void onInfoClick(int itemPosition) {

    }

    @Override
    public void onEditClick(int itemPosition) {
        switch (itemPosition) {
            case 0:

                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "kalpeshdoru@gmail.com", null));
                i.putExtra(Intent.EXTRA_SUBJECT, "Stimulus");
                i.putExtra(Intent.EXTRA_EMAIL, "Best motivational app!");

                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                break;
            case 1:
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(Objects.requireNonNull(getFragmentManager()), bottomSheetFragment.getTag());

                break;

            case 2:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, Objects.requireNonNull(getActivity()).getResources().getString(R.string.app_name));
                    String shareMessage= "\nLet me i recommend you this application is best motivational app \n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse("market://details?id=" + Objects.requireNonNull(getActivity()).getPackageName()));
                startActivity(i2);
                break;
        }

    }

    @Override
    public void onDeleteClick(int itemPosition) {

    }

    @Override
    public void onChildClick(Object object) {

    }

    @Override
    public void onParentClick(Object object) {

    }
}