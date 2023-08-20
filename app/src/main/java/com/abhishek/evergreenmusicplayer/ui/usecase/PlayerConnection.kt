package com.abhishek.evergreenmusicplayer.ui.usecase

import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.ui.service.PlayerService
import com.abhishek.evergreenmusicplayer.utils.toMediaItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import javax.inject.Inject

@UnstableApi class PlayerConnection @Inject constructor(
    @ApplicationContext context: Context,
) {
    private var mediaController: MediaController? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        coroutineScope.launch {
            mediaController = MediaController.Builder(
                context, SessionToken(
                    context, ComponentName(
                        context,
                        PlayerService::class.java
                    )
                )
            ).buildAsync().await().apply { addListener(PlayerListener()) }
        }
    }

    fun playSongs(songsList: List<Songs>, startIndex: Int, startPosition: Long) {
        mediaController?.run {
            setMediaItems(songsList.map { songs ->
                songs.toMediaItem()
            }, startIndex, startPosition)
            prepare()
            play()
        }
    }

    inner class PlayerListener : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            super.onEvents(player, events)
        }
    }
}