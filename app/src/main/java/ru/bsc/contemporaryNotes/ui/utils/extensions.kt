package ru.bsc.contemporaryNotes.ui.utils

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(@StringRes value: Int, anchorView : View?) {
    Snackbar.make(this, resources.getString(value), Snackbar.LENGTH_LONG)
        .setAnchorView(anchorView)
        .show()
}