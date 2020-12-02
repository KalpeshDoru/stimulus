package com.imkalpesh.stimulus.adapers.general;

import android.annotation.SuppressLint;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;


public class ViewBindingAdapter {

    @SuppressLint("CheckResult")
    @BindingAdapter({"bindImage"})
    public static void bindImage(AppCompatImageView appCompatImageView, @DrawableRes int resourceId) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
        requestOptions.fitCenter();
        requestOptions.override(appCompatImageView.getWidth(), appCompatImageView.getHeight());

        Glide.with(appCompatImageView.getContext())
                .load(resourceId)
                .apply(requestOptions)
                .into(appCompatImageView);
    }
}
