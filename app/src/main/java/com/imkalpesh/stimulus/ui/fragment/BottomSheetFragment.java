package com.imkalpesh.stimulus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.baseclasses.BaseAdapter;
import com.imkalpesh.stimulus.databinding.ButtomsheetAboutUsBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.ButtomSheetModel;

import java.util.ArrayList;


public class BottomSheetFragment extends BottomSheetDialogFragment implements CommonActionListener {
    private ButtomsheetAboutUsBinding binding;
    private ArrayList<ButtomSheetModel> buttomSheetModelArrayList;


    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        buttomSheetModelArrayList = new ArrayList<>();
        binding = DataBindingUtil.inflate(inflater, R.layout.buttomsheet_about_us, container, false);

        buttomSheetModelArrayList.add(new ButtomSheetModel(ContextCompat.getDrawable(getActivity(), R.drawable.info), "Motivation is the reason for people's actions, willingness and goals. Motivation is derived from the word motive which is defined as a need that requires satisfaction" +
                "otivation is the reason for people's actions, willingness and goals. Motivation is derived from the word motive which is defined as a need that requires satisfaction" +
                "otivation is the reason for people's actions, willingness and goals. Motivation is derived from the word motive which is defined as a need that requires satisfaction" +
                "otivation is the reason for people's actions, willingness and goals. Motivation is derived from the word motive which is defined as a need that requires satisfaction"));
        buttomSheetModelArrayList.add(new ButtomSheetModel(ContextCompat.getDrawable(getActivity(), R.drawable.mail), "wwww.kalpeshdoru@gmail.com"));
        buttomSheetModelArrayList.add(new ButtomSheetModel(ContextCompat.getDrawable(getActivity(), R.drawable.globe), "wwww.Stimulus.in"));

        binding.rvAboutUs.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        binding.rvAboutUs.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        BaseAdapter baseAdapter = new BaseAdapter(getActivity(), buttomSheetModelArrayList, R.layout.item_about_us, this);
        binding.rvAboutUs.setAdapter(baseAdapter);
        return binding.getRoot();
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