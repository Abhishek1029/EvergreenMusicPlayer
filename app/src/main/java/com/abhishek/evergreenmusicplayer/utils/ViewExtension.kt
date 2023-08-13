package com.abhishek.evergreenmusicplayer.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(
    message: String,
    isLong: Boolean
) {
    Toast.makeText(
        this, message, if (isLong) Toast.LENGTH_LONG
        else Toast.LENGTH_SHORT
    )
        .show()
}