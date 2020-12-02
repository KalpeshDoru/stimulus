package com.imkalpesh.stimulus.adapers.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.databinding.DashboardParentItemBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.DashboardParentModel;

import java.util.ArrayList;

public class DashboardParentAdapter extends RecyclerView.Adapter<DashboardParentAdapter.ParentViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private DashboardChildAdapter dashboardChildAdapter;
    private DashboardParentModel dashboardParentModel;
    private ArrayList<DashboardParentModel> parentModelArrayList;
    private CommonActionListener commonActionListener;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public DashboardParentAdapter(Context mContext, ArrayList<DashboardParentModel> parentModelArrayList, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.parentModelArrayList = parentModelArrayList;
        this.commonActionListener = commonActionListener;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        DashboardParentItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_parent_item, null, false);
        return new ParentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        dashboardParentModel = parentModelArrayList.get(position);
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.dashboardParentItemBinding.childRecyclerview.getContext(), LinearLayoutManager.HORIZONTAL, false);
        holder.dashboardParentItemBinding.parentItemViewMore.setVisibility(View.VISIBLE);
        holder.dashboardParentItemBinding.cvChildAdd.setVisibility(View.GONE);
        dashboardParentModel.setViewMore(true);

            /*holder.dashboardParentItemBinding.cvChildAdd.setVisibility(View.VISIBLE);
            holder.dashboardParentItemBinding.ivAddChild.setOnClickListener(view -> {
                if (commonActionListener != null) {
                    commonActionListener.onViewClick();
                }

            });*/
        layoutManager.setInitialPrefetchItemCount(dashboardParentModel.getChildItemList().size());
        dashboardChildAdapter = new DashboardChildAdapter(mContext, dashboardParentModel.getChildItemList(), commonActionListener);
        holder.dashboardParentItemBinding.childRecyclerview.setLayoutManager(layoutManager);
        holder.dashboardParentItemBinding.childRecyclerview.setAdapter(dashboardChildAdapter);
        holder.dashboardParentItemBinding.childRecyclerview.setRecycledViewPool(viewPool);
        holder.setDashboardParentModel(dashboardParentModel);
    }

    @Override
    public int getItemCount() {
        return parentModelArrayList != null ? parentModelArrayList.size() : 0;
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {
        DashboardParentItemBinding dashboardParentItemBinding;

        public ParentViewHolder(DashboardParentItemBinding dashboardParentItemBinding) {
            super(dashboardParentItemBinding.getRoot());
            this.dashboardParentItemBinding = dashboardParentItemBinding;

        }

        public void setDashboardParentModel(DashboardParentModel dashboardParentModel) {
            dashboardParentItemBinding.setParentData(dashboardParentModel);
            dashboardParentItemBinding.parentItemViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (commonActionListener != null) {
                        commonActionListener.onParentClick(dashboardParentModel);
                    }
                }
            });
        }
    }
}