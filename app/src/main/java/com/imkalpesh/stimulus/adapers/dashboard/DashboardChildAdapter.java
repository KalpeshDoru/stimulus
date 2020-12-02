package com.imkalpesh.stimulus.adapers.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.databinding.DashboardChildItemBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.DashboardChildModel;

import java.util.ArrayList;

public class DashboardChildAdapter extends RecyclerView.Adapter<DashboardChildAdapter.ChildViewHolder> {
    private LayoutInflater inflater;
    private Context mContext;
    private DashboardChildModel dashboardChildModel;
    private ArrayList<DashboardChildModel> childModelArrayList;
    private CommonActionListener commonActionListener;

    public DashboardChildAdapter(Context mContext, ArrayList<DashboardChildModel> childModelArrayList, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.childModelArrayList = childModelArrayList;
        this.commonActionListener = commonActionListener;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        DashboardChildItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_child_item, null, false);
        return new ChildViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        dashboardChildModel = childModelArrayList.get(position);
        if (childModelArrayList.size() == 0) {
            dashboardChildModel.setShowDeleteButton(false);
        }
        holder.setDashboardChildItemBinding(dashboardChildModel);
    }

    @Override
    public int getItemCount() {

            return childModelArrayList != null ? childModelArrayList.size() : 0;

    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        DashboardChildItemBinding dashboardChildItemBinding;

        public ChildViewHolder(DashboardChildItemBinding dashboardChildItemBinding) {
            super(dashboardChildItemBinding.getRoot());
            this.dashboardChildItemBinding = dashboardChildItemBinding;

        }

        public void setDashboardChildItemBinding(DashboardChildModel childModel) {
            dashboardChildItemBinding.setData(childModel);

            dashboardChildItemBinding.childLayoutMain.setOnClickListener(view -> {

                if (commonActionListener == null) {
                    commonActionListener = (CommonActionListener) mContext;
                }
                commonActionListener.onChildClick(childModel);
            });
        }
    }
}
