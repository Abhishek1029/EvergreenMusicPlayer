package com.abhishek.evergreenmusicplayer.utils

import android.content.ContentResolver
import com.abhishek.evergreenmusicplayer.data.Songs
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MusicDataSource @Inject constructor(private val contentResolver: ContentResolver) {
    /**
     * map operator is used to apply the transformation to each item emitted by the flow
     */
    fun getSongs() = contentResolver.observe(retrieveUri).map {
        buildList {
            contentResolver.query(
                retrieveUri,
                projection,
                null,
                null,
                null
            )?.use { cursor ->
                while (cursor.moveToNext()) {
                    val songId = cursor.getString(0)
                    val name = cursor.getString(1)
                    val album = cursor.getString(2)
                    val path = cursor.getString(3)
                    val duration = cursor.getString(4)
                    val albumId = cursor.getString(5)
                    val artistId = cursor.getString(6)
                    val artistName = cursor.getString(7)
                    Songs(
                        songId,
                        name,
                        album,
                        path,
                        duration,
                        albumId,
                        artistId,
                        artistName
                    ).let(::add)
                }
            }
        }
    }
}