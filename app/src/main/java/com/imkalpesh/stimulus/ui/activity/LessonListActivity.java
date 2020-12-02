package com.imkalpesh.stimulus.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.snackbar.Snackbar;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.baseclasses.BaseActivity;
import com.imkalpesh.stimulus.baseclasses.BaseAdapter;
import com.imkalpesh.stimulus.databinding.ActivityQuotesListBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.LessonModel;
import com.imkalpesh.stimulus.utility.AppConstants;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class LessonListActivity extends BaseActivity implements View.OnClickListener, CommonActionListener {
    private ActivityQuotesListBinding binding;
    private ArrayList<LessonModel> lessonModelArrayList;
    private BaseAdapter baseAdapter;
    private Context mContext = this;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quotes_list);
        binding.actionBar.ivBack.setVisibility(View.VISIBLE);
        binding.actionBar.ivBack.setOnClickListener(view -> finish());
        binding.actionBar.tvTitle.setText(this.getIntent().getStringExtra(AppConstants.LESSON_NAME) + " Lessons");
        lessonModelArrayList = new ArrayList<>();
        lessonModelArrayList = this.getIntent().getParcelableArrayListExtra(AppConstants.LESSON_ARRAYLIST_NAME);
        if (lessonModelArrayList.size() == 0) {
            Toast.makeText(mContext, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            binding.rvQuotes.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
            baseAdapter = new BaseAdapter(mContext, lessonModelArrayList, R.layout.item_lesson, this);
            binding.rvQuotes.setAdapter(baseAdapter);
        }
        if (!appPreference.getBooleanPreference(AppConstants.SHOWNTOAST)) {
            /*Toast toast= Toast.makeText(getApplicationContext(),
                    "Long press for copy into clipboard", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();*/
            Snackbar.make(binding.getRoot(),"Long press for copy into clipboard",Snackbar.LENGTH_LONG)
                    .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                    .show();
            /*Toast.makeText(this, "Long press for copy into clipboard", Toast.LENGTH_LONG).show();*/
            appPreference.setBooleanPreference(AppConstants.SHOWNTOAST, true);
        }

        if (AppConstants.isSaved) {
            binding.adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
        }

        bindingAdsView(binding.adView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }

    @Override
    public void onViewClick() {

    }

    @Override
    public void onInfoClick(int itemPosition) {

    }

    @Override
    public void onEditClick(int itemPosition) {

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