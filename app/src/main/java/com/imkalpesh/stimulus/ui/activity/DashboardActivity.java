package com.imkalpesh.stimulus.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.baseclasses.BaseActivity;
import com.imkalpesh.stimulus.databinding.ActivityDashboardBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.DashboardChildModel;
import com.imkalpesh.stimulus.models.DashboardParentModel;
import com.imkalpesh.stimulus.ui.fragment.BiographiesFragment;
import com.imkalpesh.stimulus.ui.fragment.HomeFragment;
import com.imkalpesh.stimulus.ui.fragment.LessonFragment;
import com.imkalpesh.stimulus.ui.fragment.QuotesFragment;
import com.imkalpesh.stimulus.ui.fragment.SettingsFragment;

import java.util.ArrayList;

public class DashboardActivity extends BaseActivity implements CommonActionListener, View.OnClickListener {

    private final Context mContext = this;
    private ActivityDashboardBinding binding;
    private ArrayList<DashboardParentModel> parentItemList;
    private ArrayList<DashboardChildModel> userQuotesArrayList;
    private boolean doubleBackToExitPressedOnce;
    private int doubleChecker = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        binding.actionBar.tvTitle.setText(getResources().getString(R.string.app_name));

        if (doubleChecker == 1) {
            System.exit(0);
        }
        loadFragment(new HomeFragment());

        binding.bottomAppBar.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {

                case R.id.menuSettings:
                    if (!(getCurrentFragment() instanceof SettingsFragment)) {
                        loadFragment(new SettingsFragment());
                    }
                    return true;
                case R.id.menuHome:
                    if (!(getCurrentFragment() instanceof HomeFragment)) {
                        loadFragment(new HomeFragment());
                    }
                    return true;
                case R.id.menuQuotes:
                    if (!(getCurrentFragment() instanceof QuotesFragment)) {
                        loadFragment(new QuotesFragment());
                    }
                    return true;
                case R.id.menuBiographies:
                    if (!(getCurrentFragment() instanceof BiographiesFragment)) {
                        loadFragment(new BiographiesFragment());
                    }
                    return true;

                case R.id.menuLessons:
                    if (!(getCurrentFragment() instanceof LessonFragment)) {
                        loadFragment(new LessonFragment());
                    }
                    return true;
            }
            return false;
        });
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    public void onBackPressed() {

        if (getCurrentFragment() instanceof HomeFragment) {
            Toast.makeText(mContext, "Press back again to exit", Toast.LENGTH_SHORT).show();
        } else {
            appExit();
        }
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 1000);
            doubleChecker = 2;
        } else {
            appExit();
        }
    }

    private void appExit() {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}