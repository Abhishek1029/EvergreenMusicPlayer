package com.abhishek.evergreenmusicplayer.ui.repository

import android.content.Context
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.*
import javax.inject.Inject

interface SongsRepo {
    fun getSongs(context: Context) : List<Songs>
}

class SongsRepoImpl @Inject constructor() : SongsRepo {
    override fun getSongs(context: Context): List<Songs> {
        val cursor = retrieveCursor(context)
        val songsList = retrieveListOfSongs(cursor)
        cursor?.close()
        return filteredSongs(songsList)
    }

    companion object{
        private const val TAG = "SongsRepository"
    }
}