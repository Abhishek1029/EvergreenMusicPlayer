package com.abhishek.evergreenmusicplayer.ui.repository

import android.content.Context
import com.abhishek.evergreenmusicplayer.data.Artist
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.filteredSongs
import com.abhishek.evergreenmusicplayer.utils.retrieveCursor
import com.abhishek.evergreenmusicplayer.utils.retrieveListOfSongs

private const val TAG = "ArtistRepository"

interface ArtistRepo {
    fun getArtistList(context: Context): List<Artist>
    fun getArtistById(context: Context, artistId: String?): Artist?
}

class ArtistRepoImpl : ArtistRepo {
    override fun getArtistList(context: Context): List<Artist> {
        val songsList = getSongs(context)
        /**
         * when applying groupBy function to any list,
         * it returns a Map<K,V> based on the key that we pass as parameter
         * for eg:- In below mentioned scenario, it will create a Map<K,V(List)> based on the artistId
         * where Key is ArtistId and Value is List Of Songs containing that ArtistId
         * Applying map function on Map<K,V>, it transforms the whole map and returns a List<R>
         * where R is the Object that we pass as a Parameter,
         * in below eg: it will return List<Artist>
         */
        return songsList.groupBy { it.artistId }.map { Artist(it.key, it.value) }
    }

    override fun getArtistById(context: Context, artistId: String?): Artist? {
       val artistList = getArtistList(context)
        return artistList.singleOrNull { it.artistId == artistId }
    }

    private fun getSongs(context: Context): List<Songs> {
        val cursor = retrieveCursor(context)
        val songsList = retrieveListOfSongs(cursor)
        cursor?.close()
        return filteredSongs(songsList)
    }
}