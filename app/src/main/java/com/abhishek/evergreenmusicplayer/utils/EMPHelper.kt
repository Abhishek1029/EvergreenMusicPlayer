package com.abhishek.evergreenmusicplayer.utils

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.abhishek.evergreenmusicplayer.R
import java.util.Calendar
import java.util.Calendar.HOUR_OF_DAY

fun Context.wishAccordingToTime(): String {
    val calender = Calendar.getInstance()
    return when (calender.get(HOUR_OF_DAY)) {
        in 0..11 -> resources.getString(R.string.morning)
        in 12..15 -> resources.getString(R.string.afternoon)
        in 16..20 -> resources.getString(R.string.evening)
        else -> resources.getString(R.string.night)
    }
}

val EMPAudioPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    Manifest.permission.READ_MEDIA_AUDIO
} else {
    Manifest.permission.READ_EXTERNAL_STORAGE
}
