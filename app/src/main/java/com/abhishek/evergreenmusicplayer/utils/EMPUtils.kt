package com.abhishek.evergreenmusicplayer.utils

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Parcelable
import android.provider.MediaStore
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.data.PermissionItem
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ALBUMS
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ARTISTS
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ART_WORK_URI
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.PLAYLISTS
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.SONGS
import com.google.gson.Gson

object EMPUtils {
    val tabTitles = listOf(
        SONGS to R.drawable.ic_baseline_music_note_24,
        ARTISTS to R.drawable.ic_baseline_music_note_24,
        ALBUMS to R.drawable.ic_baseline_music_note_24,
    )
}

fun retrieveContentResolver(context: Context): ContentResolver? = context.contentResolver

val retrieveUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

val projection = arrayOf(
    MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
    MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.DATA,
    MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.ALBUM_ID,
    MediaStore.Audio.Media.ARTIST_ID, MediaStore.Audio.Media.ARTIST,
)

fun retrieveCursor(context: Context): Cursor? =
    retrieveContentResolver(context)?.query(retrieveUri, projection, null, null, null)

fun retrieveListOfSongs(cursor: Cursor?): MutableList<Songs> {
    val songsList = mutableListOf<Songs>()
    while (cursor?.moveToNext() == true) {
        val songId = cursor.getString(0)
        val name = cursor.getString(1)
        val album = cursor.getString(2)
        val path = cursor.getString(3)
        val duration = cursor.getString(4)
        val albumId = cursor.getString(5)
        val artistId = cursor.getString(6)
        val artistName = cursor.getString(7)
        val songs = Songs(songId, name, album, path, duration, albumId, artistId, artistName)
        songsList.add(songs)
    }
    return songsList
}

fun filteredSongs(songsList: MutableList<Songs>) =
    songsList.filter { it.duration.toLong() > 30000 }.toTypedArray().toList()

fun changeColorOfTextInString(changeableString: String, str: String): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        withStyle(SpanStyle(Color.Magenta)) {
            append(changeableString)
        }
        append(" ")
        append(str)
    }
    return annotatedString
}

val permissionItemList = listOf(
    PermissionItem(R.string.storage, R.string.storage_desc, R.drawable.baseline_store_24),
  //  PermissionItem(R.string.location, R.string.location_desc, R.drawable.ic_baseline_music_note_24),
)

fun Context?.checkPermission(permission: String) =
    this?.let { ContextCompat.checkSelfPermission(it, permission) }

fun formatMillis(time: String): String {
    val milliseconds: Long = time.toLong()
    val minutes = formatMinutes(minutes = minutes(milliseconds))
    val seconds = formatSeconds(seconds = seconds(milliseconds))
    return "$minutes:$seconds mins"
}

fun minutes(milliSecond: Long) = milliSecond / 1000 / 60
fun seconds(milliSecond: Long) = milliSecond / 1000 % 60

fun formatSeconds(seconds: Long) = if (seconds.toString().length == 2) {
    seconds
} else {
    "0$seconds"
}

fun formatMinutes(minutes: Long) = if (minutes.toString().length == 2) {
    minutes
} else {
    "0$minutes"
}

val mArtWorkUri: Uri? = Uri.parse(ART_WORK_URI)

fun getUriFromAlbumId(albumId: String) =
    mArtWorkUri?.let { ContentUris.withAppendedId(it, albumId.toLong()) }

fun Int.getSongText() = if (this > 1) {
    "$this Songs"
} else {
    "$this Song"
}

operator fun NavDestination?.contains(route: String) =
    this?.hierarchy?.any { it.route == route } == true