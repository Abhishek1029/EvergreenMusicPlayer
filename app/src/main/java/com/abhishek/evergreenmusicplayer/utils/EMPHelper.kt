package com.abhishek.evergreenmusicplayer.utils

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaItem.RequestMetadata
import androidx.media3.common.MediaMetadata
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.data.Songs
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

fun Uri.requestMetaData() = RequestMetadata.Builder().setMediaUri(this).build()
fun String.getUri(): Uri = this.toUri()
fun Songs.toMediaItem() = buildMediaItem()

fun Songs.getMediaMetaData() = MediaMetadata.Builder()
    .setTitle(title)
    .setArtist(artistName)
    .setIsPlayable(true)
    .setExtras(
        bundleOf(
            ARTIST_ID to artistId,
            ALBUM_ID to albumId,
            DURATION to duration
        )
    )
    .build()

fun Songs.buildMediaItem() = MediaItem.Builder()
    .setMediaId(id)
    //.setUri(path.toUri())
    .setRequestMetadata(path.getUri().requestMetaData())
    .setMediaMetadata(getMediaMetaData())
    .build()

const val ARTIST_ID = "artist_id"
const val ALBUM_ID = "album_id"
const val DURATION = "duration"