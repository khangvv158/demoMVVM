package com.sun.demomvvm.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingUtils {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, url: String) {
        if (url.isNotBlank()) {
            imageView.loadUrl(url)
        }
    }
}
