package com.imkalpesh.stimulus.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.ads.AdRequest;
import com.google.android.material.snackbar.Snackbar;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.adapers.general.QuotesAdapter;
import com.imkalpesh.stimulus.baseclasses.BaseActivity;
import com.imkalpesh.stimulus.databinding.ActivityQuotesListBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.QuotesModel;
import com.imkalpesh.stimulus.utility.AppConstants;

import java.util.ArrayList;
import java.util.Objects;

public class QuotesListActivity extends BaseActivity implements CommonActionListener, View.OnClickListener {
    private ActivityQuotesListBinding binding;
    private ArrayList<QuotesModel> quotesListActivityArrayList, userQuotesArraylist;
    private QuotesAdapter quotesAdapter;

    @SuppressLint({"SetTextI18n", "ShowToast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quotes_list);
        binding.actionBar.ivBack.setOnClickListener(view -> finish());
        userQuotesArraylist = new ArrayList<>();
        binding.actionBar.tvTitle.setText(this.getIntent().getStringExtra(AppConstants.QUOTES_AUTHOR_NAME) + " Quotes");
        binding.actionBar.ivBack.setVisibility(View.VISIBLE);

        if (!appPreference.getBooleanPreference(AppConstants.SHOWNTOAST)) {

           /* Toast toast= Toast.makeText(getApplicationContext(),
                    "Long press for copy into clipboard", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();*/

            Snackbar.make(binding.getRoot(),"Long press for copy into clipboard",Snackbar.LENGTH_LONG).show();
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
        if (Objects.requireNonNull(getIntent().getExtras()).getBoolean(AppConstants.ISUSERCREATEDQUOTE)) {
            binding.actionBar.ivAdd.setVisibility(View.VISIBLE);
            binding.actionBar.ivAdd.setOnClickListener(this);
            binding.fabAddQuotes.setOnClickListener(this);
        }

        quotesListActivityArrayList = this.getIntent().getParcelableArrayListExtra(AppConstants.QUOTES_ARRAYLIST_NAME);

        quotesAdapter = new QuotesAdapter(this, quotesListActivityArrayList, this);
        binding.rvQuotes.setAdapter(quotesAdapter);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onViewClick() {

    }

    @Override
    public void onInfoClick(int itemPosition) {
        /*quotesListActivityArrayList.get(itemPosition).getQuotes();
        Log.d("TAG", "onInfoClick: "+ quotesListActivityArrayList.get(itemPosition).getQuotes());*/

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                binding.relativeAddQuotes.setVisibility(View.VISIBLE);
                showSoftKeyboard(binding.edtAddQuotes);
                break;

            case R.id.fabAddQuotes:
                userQuotesArraylist.add(new QuotesModel(binding.edtAddQuotes.getText().toString(), true));
                quotesAdapter = new QuotesAdapter(this, userQuotesArraylist, this);
                binding.rvQuotes.setAdapter(quotesAdapter);
                quotesAdapter.notifyDataSetChanged();
                binding.edtAddQuotes.setText("");
                binding.relativeAddQuotes.setVisibility(View.GONE);
                break;
        }
    }
}