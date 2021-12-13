package ru.bsc.contemporaryNotes.ui.utils

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(@StringRes value: Int, anchorView: View?) {
    Snackbar.make(this, resources.getString(value), Snackbar.LENGTH_LONG)
        .setAnchorView(anchorView)
        .show()
}

fun View.showErrorSnackBar(@StringRes value: Int, anchorView: View?) {
    Snackbar.make(this, resources.getString(value), Snackbar.LENGTH_LONG)
        .setBackgroundTint(ContextCompat.getColor(context, android.R.color.holo_red_light))
        .setAnchorView(anchorView)
        .show()
}