package com.abhishek.evergreenmusicplayer.ui

import com.abhishek.evergreenmusicplayer.data.Album
import com.abhishek.evergreenmusicplayer.data.Artist
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.MusicDataSource
import kotlinx.coroutines.flow.Flow
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
        get() = TODO("Not yet implemented")
    override val albums: Flow<List<Album>>
        get() = TODO("Not yet implemented")

}