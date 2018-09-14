package io.skillstest.cdeveloper.skillstest.util

import android.graphics.Color
import android.os.Build
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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