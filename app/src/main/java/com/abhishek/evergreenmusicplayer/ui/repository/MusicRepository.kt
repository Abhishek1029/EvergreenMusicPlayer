package com.abhishek.evergreenmusicplayer.ui.repository

import com.abhishek.evergreenmusicplayer.data.Album
import com.abhishek.evergreenmusicplayer.data.Artist
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.MusicDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MusicRepository {
    val songs: Flow<List<Songs>>
    val artists: Flow<List<Artist>>
    val albums: Flow<List<Album>>
}

class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSource
) : MusicRepository {
    override val songs: Flow<List<Songs>>
        get() = musicDataSource.getSongs()
    override val artists: Flow<List<Artist>>
        get() = songs.map { songs ->
            songs.groupBy(Songs::artistId).map { (artistId, songs) ->
                Artist(artistId = artistId, songsList = songs)
            }
        }
    override val albums: Flow<List<Album>>
        get() = songs.map { songs ->
            songs.groupBy(Songs::albumId).map { (albumId, songs) ->
                Album(
                    albumId = albumId,
                    songsList = songs
                )
            }
        }
}