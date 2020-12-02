package com.imkalpesh.stimulus.adapers.general;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.databinding.ItemQuotesBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.QuotesModel;

import java.util.ArrayList;
import java.util.Random;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder> {
    private LayoutInflater inflater;
    private Context mContext;
    private QuotesModel quotesModel;
    private ArrayList<QuotesModel> quotesModelArrayList;
    private CommonActionListener commonActionListener;

    public QuotesAdapter(Context mContext, ArrayList<QuotesModel> quotesModelArrayList, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.quotesModelArrayList = quotesModelArrayList;
        this.commonActionListener = commonActionListener;
    }

    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuotesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_quotes, null, false);
        return new QuotesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, int position) {
        quotesModel = quotesModelArrayList.get(position);
        if (quotesModel.isUserAddedQuotes()) {
            holder.itemQuotesBinding.ivShare.setImageResource(R.drawable.ic_more);
        }
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.itemQuotesBinding.viewSepOne.setBackgroundColor(color);
        holder.setDashboardChildItemBinding(quotesModel);
    }

    @Override
    public int getItemCount() {
        return quotesModelArrayList != null ? quotesModelArrayList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        quotesModel = quotesModelArrayList.get(position);
        if (quotesModel.isUserAddedQuotes()) {
            return R.layout.dashboard_add_child;
        } else {
            return R.layout.dashboard_child_item;
        }
    }

    public class QuotesViewHolder extends RecyclerView.ViewHolder {
        ItemQuotesBinding itemQuotesBinding;

        public QuotesViewHolder(ItemQuotesBinding itemQuotesBinding) {
            super(itemQuotesBinding.getRoot());
            this.itemQuotesBinding = itemQuotesBinding;

            itemQuotesBinding.rvMain.setOnLongClickListener(view -> {
                Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE) ;
                ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Quotes", quotesModelArrayList.get(getAdapterPosition()).getQuotes());
                clipboard.setPrimaryClip(clip);
                Snackbar.make(view, "Copy to clipboard ", Snackbar.LENGTH_SHORT).show();
                vibe.vibrate(100);
                return true;
            });

            itemQuotesBinding.ivShare.setOnClickListener(view -> {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                String shareString = quotesModelArrayList.get(getAdapterPosition()).getQuotes() + " - @"+ mContext.getString(R.string.app_name) + " App";
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareString);

                if (sharingIntent.resolveActivity(mContext.getPackageManager()) != null)
                    mContext.startActivity(Intent.createChooser(sharingIntent, "Share quote using"));
                else {
                    Toast.makeText(mContext, "No app found on your phone which can perform this action", Toast.LENGTH_SHORT).show();
                }

                /*PopupMenu popup = new PopupMenu(mContext, view);
                MenuInflater inflater = popup.getMenuInflater();
                if (quotesModel.isUserAddedQuotes()) {
                    popup.getMenu().add(Menu.NONE, 3, 3, "Update");
                    popup.getMenu().add(Menu.NONE, 4, 4, "Delete");
                }*/
                /*inflater.inflate(R.menu.quotes_share_menu, popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menuCopy:
                               *//* ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Quotes", quotesModelArrayList.get(getAdapterPosition()).getQuotes());
                                clipboard.setPrimaryClip(clip);
                                Snackbar.make(view, "Copy to clipboard ", Snackbar.LENGTH_SHORT).show();*//*

                                break;

                            case 3:
                                break;

                            case 4:
                                quotesModelArrayList.remove(getAdapterPosition()).getQuotes();
                                notifyDataSetChanged();
                                break;

                            case R.id.menuWhatsapp:
                                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                                whatsappIntent.setType("text/plain");
                                whatsappIntent.setPackage("com.whatsapp");
                                whatsappIntent.putExtra(Intent.EXTRA_TEXT, quotesModelArrayList.get(getAdapterPosition()).getQuotes());
                                try {
                                    mContext.startActivity(whatsappIntent);
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(mContext, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                                }

                            default:
                                return false;
                        }
                        return true;
                    }
                });*/
            });

        }

        public void setDashboardChildItemBinding(QuotesModel quotesModel) {
            itemQuotesBinding.setQuotesData(quotesModel);
            itemQuotesBinding.executePendingBindings();
        }

    }
}
