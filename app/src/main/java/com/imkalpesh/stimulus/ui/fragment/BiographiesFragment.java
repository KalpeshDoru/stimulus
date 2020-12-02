package com.imkalpesh.stimulus.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.baseclasses.BaseAdapter;
import com.imkalpesh.stimulus.baseclasses.BaseFragment;
import com.imkalpesh.stimulus.databinding.ActivityGridBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.DashboardChildModel;
import com.imkalpesh.stimulus.utility.AppConstants;

import java.util.ArrayList;
import java.util.Objects;


public class BiographiesFragment extends BaseFragment implements View.OnClickListener, CommonActionListener {
    private ActivityGridBinding binding;
    private ArrayList<DashboardChildModel> biographieArraylist;

    @Override
    public Activity registerActivity() {
        return null;
    }

    @Override
    public Fragment registerFragment() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareIntertitialAds();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_grid, container, false);
        binding.actionBar.actionBarMainLayout.setVisibility(View.GONE);
        biographieArraylist = new ArrayList<>();
        startWork();
        if (AppConstants.isSaved) {
            binding.adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
        }

        bindingAdsView(binding.adView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        binding.rvGrid.setLayoutManager(staggeredGridLayoutManager);
        BaseAdapter dashboardChildAdapter = new BaseAdapter(getActivity(), biographieArraylist, R.layout.dashboard_child_item, this);
        binding.rvGrid.setAdapter(dashboardChildAdapter);

        return binding.getRoot();
    }

    private void startWork() {
        biographieArraylist.add(new DashboardChildModel("Nikola Tesla", R.drawable.nikol));
        biographieArraylist.add(new DashboardChildModel("Mother Teresa", R.drawable.mothertera));
        biographieArraylist.add(new DashboardChildModel("Einstein", R.drawable.img_albert));
        biographieArraylist.add(new DashboardChildModel("Steve jobs", R.drawable.img_steve));
        biographieArraylist.add(new DashboardChildModel("Swami vivekananda", R.drawable.img_vivekanada));
        biographieArraylist.add(new DashboardChildModel("Bill Gates", R.drawable.biily));
        biographieArraylist.add(new DashboardChildModel("Sachin Tendulkar", R.drawable.sachin));
        biographieArraylist.add(new DashboardChildModel("Abdul Kalam", R.drawable.img_kalam));
        biographieArraylist.add(new DashboardChildModel("Narendra Modi", R.drawable.img_modi));
        biographieArraylist.add(new DashboardChildModel("Elon Musk", R.drawable.img_elon));
        biographieArraylist.add(new DashboardChildModel("Jack Ma", R.drawable.imgjackma));
        biographieArraylist.add(new DashboardChildModel("Vallabhbhai Patel", R.drawable.sardar));
        biographieArraylist.add(new DashboardChildModel("Mahatma Gandhi", R.drawable.gandhi));
        biographieArraylist.add(new DashboardChildModel("Chanakya", R.drawable.img_chankya));
        biographieArraylist.add(new DashboardChildModel("Coming Soon",R.drawable.coming_soon));
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

    }

    @Override
    public void onDeleteClick(int itemPosition) {

    }

    @Override
    public void onChildClick(Object object) {
        DashboardChildModel childModel = (DashboardChildModel) object;
        switch (childModel.getChildItemTitle()) {
            case "Nikola Tesla":
                biographiesURI(1, "https://drive.google.com/file/d/13qp-y9SyEQfvvmDQuA29AjflTRqnoU2g/view?usp=sharing");
                break;
            case "Mother Teresa":
                biographiesURI(1, "https://drive.google.com/file/d/1FakqOPxkgf_4kad52gidkg7MWg69HBPK/view?usp=sharing");
                break;
            case "Steve jobs":
                biographiesURI(1, "https://drive.google.com/file/d/1i4J3P8KgEINmPVCpQIqO_qJZnV11TNuN/view?usp=sharing");
                break;
            case "Swami vivekananda":
                biographiesURI(1, "https://drive.google.com/file/d/1vixS_vsTiUTeXdBFB1LnyJNPj50epeqm/view?usp=sharing");
                break;
            case "Sachin Tendulkar":
                biographiesURI(1, "https://drive.google.com/file/d/1Bv_lWpD0TiM4BYB_53LgbF8I0311Iq9X/view?usp=sharing");
                break;
            case "Jack Ma":
                biographiesURI(1, "https://drive.google.com/file/d/1zvV8wPwBkxb5ahWg914-5Y_0nhXaufdI/view?usp=sharing");
                break;
            case "Bill Gates":
                biographiesURI(1, "https://drive.google.com/file/d/1g7szSuQPe5x-0JT1FQLnzojYjhxAERUK/view?usp=sharing");
                break;
            case "Chanakya":
                biographiesURI(1, "https://drive.google.com/file/d/1y490xVnp0oXAUHz1DPZ6WJtEl7brhbM2/view?usp=sharing");
                break;
            case "Einstein":
                biographiesURI(1, "https://drive.google.com/file/d/1xaVvO8R9xWhrZguhK_xpB1_LlHDwgMFa/view?usp=sharing");
                break;
            case "Abdul Kalam":
                biographiesURI(1, "https://drive.google.com/file/d/1f3I565Zn4YUh9Q_UTOmtbZwlTmLiqv9K/view?usp=sharing");
                break;
            case "Elon Musk":
                biographiesURI(1, "https://drive.google.com/file/d/1pKL8nVDeQqnfhcb_wpoYQseykcbgWsu3/view?usp=sharing");
                break;
            case "Narendra Modi":
                biographiesURI(1, "https://drive.google.com/file/d/1aEjoFk4AnpTTPByzgF39EX7NPc1pq_bN/view?usp=sharing");
                break;
            case "Vallabhbhai Patel":
                biographiesURI(1, "https://drive.google.com/file/d/1X5TTkp3_w0ulZaqDwy3SjsRUtOaNsjtA/view?usp=sharing");
                break;
            case "Mahatma Gandhi":
                biographiesURI(1, "https://drive.google.com/file/d/1baJPiEtka-WlifoJEdIcaVRYBywH5X2U/view?usp=sharing");
                break;

            case "Coming Soon":
                Toast toast= Toast.makeText(getActivity(),
                        "Under Development", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
        }
    }

    private void biographiesURI(int i, String uri) {
        if (i == 1) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            AppConstants.ISURI = 1;
        } else {
            AppConstants.ISURI = 0;
            Toast.makeText(getActivity(), "No Data Found!!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onParentClick(Object object) {

    }
}