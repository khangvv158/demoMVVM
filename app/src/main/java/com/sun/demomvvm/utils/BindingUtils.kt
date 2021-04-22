package com.sun.demomvvm.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtils {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, url: String) {
        if(url.isNotBlank()){
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}
