package com.imkalpesh.stimulus.baseclasses;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.imkalpesh.stimulus.BR;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.databinding.DashboardChildItemBinding;
import com.imkalpesh.stimulus.databinding.ItemAboutUsBinding;
import com.imkalpesh.stimulus.databinding.ItemLessonBinding;
import com.imkalpesh.stimulus.databinding.ItemSettingBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.ButtomSheetModel;
import com.imkalpesh.stimulus.models.DashboardChildModel;
import com.imkalpesh.stimulus.models.LessonModel;

import java.util.ArrayList;
import java.util.Random;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<?> itemsArrayList;
    private int rawLayoutId;
    private CommonActionListener commonActionListener;

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
    }

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.commonActionListener = commonActionListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), rawLayoutId, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(itemsArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsArrayList != null ? itemsArrayList.size() : 0;
    }

    public void filterList(ArrayList<?> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        public MyViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            if (binding instanceof DashboardChildItemBinding) {
                if (commonActionListener == null) {
                    commonActionListener = (CommonActionListener) mContext;
                }
            }
        }

        @SuppressLint({"SimpleDateFormat", "UseCompatLoadingForDrawables"})
        void setBinding(Object obj) {
            binding.setVariable(BR.data, obj);
            if (binding instanceof DashboardChildItemBinding) {
                DashboardChildModel dashboardChildModel = (DashboardChildModel) obj;
                ((DashboardChildItemBinding) binding).cvMain.setOnClickListener(view -> {
                    commonActionListener.onChildClick(obj);
                });
            } else if (binding instanceof ItemLessonBinding) {
                LessonModel lessonModel = (LessonModel) obj;
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                ((ItemLessonBinding) binding).viewSepOne.setBackgroundColor(color);

                if (lessonModel.getLessonTitle() != null && lessonModel.getLessonDescription() != null) {
                    ((ItemLessonBinding) binding).rvMain.setOnLongClickListener(view -> {
                        Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                        ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Lesson ", lessonModel.getLessonTitle() + "\n" + lessonModel.getLessonDescription());
                        clipboard.setPrimaryClip(clip);
                        Snackbar.make(view, "Copy to clipboard", Snackbar.LENGTH_SHORT).show();
                        vibe.vibrate(100);
                        return true;
                    });

                    ((ItemLessonBinding) binding).ivLessonShare.setOnClickListener(view -> {

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");

                        String shareString = lessonModel.getLessonTitle() + "\n" + lessonModel.getLessonDescription() + " - @" + mContext.getString(R.string.app_name) + " App";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareString);

                        if (sharingIntent.resolveActivity(mContext.getPackageManager()) != null)
                            mContext.startActivity(Intent.createChooser(sharingIntent, "Share quote using"));
                        else {
                            Toast.makeText(mContext, "No app found on your phone which can perform this action", Toast.LENGTH_SHORT).show();
                        }

                       /* Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                        whatsappIntent.setType("text/plain");
                        whatsappIntent.setPackage("com.whatsapp");
                        whatsappIntent.putExtra(Intent.EXTRA_TEXT, lessonModel.getLessonTitle() + "\n" + lessonModel.getLessonDescription());
                        try {
                            mContext.startActivity(whatsappIntent);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(mContext, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                        }*/
                    });
                }
            } else if (binding instanceof ItemSettingBinding) {

                ((ItemSettingBinding) binding).linearOne.setOnClickListener(view -> {
                    if (commonActionListener == null) {
                        commonActionListener = (CommonActionListener) mContext;
                    }
                    commonActionListener.onEditClick(getAdapterPosition());
                });
            } else if (binding instanceof ItemAboutUsBinding) {
                ButtomSheetModel buttomSheetModel = (ButtomSheetModel) obj;
                ((ItemAboutUsBinding) binding).setData(buttomSheetModel);

            }
        }
    }
}
