package io.skillstest.cdeveloper.skillstest.util

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(url: String): ImageView {
    setBackgroundColor(Color.TRANSPARENT)
    Glide.with(context)
            .load(url)
            .into(this)
    return this
}

fun String.isNotNull(): Boolean {
    return !equals("") and (toLowerCase() != "null")
}

fun SwipeRefreshLayout.hide() {
    isRefreshing = false
}