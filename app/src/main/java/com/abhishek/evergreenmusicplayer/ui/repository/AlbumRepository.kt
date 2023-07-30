package com.abhishek.evergreenmusicplayer.ui.repository

import android.content.Context
import com.abhishek.evergreenmusicplayer.data.Album
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.filteredSongs
import com.abhishek.evergreenmusicplayer.utils.retrieveCursor
import com.abhishek.evergreenmusicplayer.utils.retrieveListOfSongs

interface AlbumRepo {
    fun getAlbumList(context: Context): List<Album>
    fun getAlbumById(context: Context, albumId: String?) : Album?
}

class AlbumRepoImpl : AlbumRepo {
    override fun getAlbumList(context: Context): List<Album> {
        val songsList = getSongs(context)
        return songsList.groupBy { it.albumId }.map { Album(it.key, it.value) }
    }

    override fun getAlbumById(context: Context, albumId: String?): Album? {
        val albumList = getAlbumList(context)
        return albumList.singleOrNull { it.albumId == albumId }
    }

    private fun getSongs(context: Context): List<Songs> {
        val cursor = retrieveCursor(context)
        val songsList = retrieveListOfSongs(cursor)
        cursor?.close()
        return filteredSongs(songsList)
    }

}