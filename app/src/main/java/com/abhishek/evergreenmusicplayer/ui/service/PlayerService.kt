package com.abhishek.evergreenmusicplayer.ui.service

import android.app.PendingIntent
import android.app.Service
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@UnstableApi @AndroidEntryPoint
class PlayerService : MediaSessionService() {
    private var mediaSession: MediaSession? = null
    @Inject lateinit var mediaSessionCallback: PlayerSessionCallback

    private val _currentMediaId = MutableStateFlow("")
    private val currentMediaId = _currentMediaId.asStateFlow()


    override fun onCreate() {
        super.onCreate()

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()

        val player = ExoPlayer.Builder(this)
            .setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .build()

        val sessionActivityPendingIntent = TaskStackBuilder.create(this).run {
            addNextIntent(Intent(this@PlayerService, Class.forName(Player_ACTIVITY_PACKAGE_NAME)))
            val immutableFlag =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
            getPendingIntent(0, immutableFlag or PendingIntent.FLAG_UPDATE_CURRENT)
        }

        mediaSession =
            MediaSession.Builder(this, player)
                .setCallback(mediaSessionCallback)
                .setSessionActivity(sessionActivityPendingIntent)
                .build()
                .apply { player.addListener(PlayerListener()) }

       // setMediaNotificationProvider(musicNotificationProvider)

/*        startPlaybackModeSync()
        startFavoriteSync()*/
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo) = mediaSession

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            clearListener()
            mediaSession = null
        }
        mediaSessionCallback.cancelCoroutineScope()
       // musicNotificationProvider.cancelCoroutineScope()
        super.onDestroy()
    }
/*
    private fun startPlaybackModeSync() = coroutineScope.launch {
        getPlaybackModeUseCase().collectLatest { playbackMode ->
            mediaSession?.player?.run {
                when (playbackMode) {
                    PlaybackMode.REPEAT -> {
                        shuffleModeEnabled = false
                        repeatMode = Player.REPEAT_MODE_ALL
                    }

                    PlaybackMode.REPEAT_ONE -> {
                        repeatMode = Player.REPEAT_MODE_ONE
                    }

                    PlaybackMode.SHUFFLE -> {
                        repeatMode = Player.REPEAT_MODE_ALL
                        shuffleModeEnabled = true
                    }
                }
            }
            musicSessionCallback.setPlaybackModeAction(playbackMode)
            mediaSession?.setCustomLayout(musicSessionCallback.customLayout)
        }
    }

    private fun startFavoriteSync() = coroutineScope.launch {
        combine(currentMediaId, getFavoriteSongIdsUseCase()) { currentMediaId, favoriteSongIds ->
            currentMediaId in favoriteSongIds
        }.collectLatest { isCurrentMediaIdFavorite ->
            musicSessionCallback.toggleFavoriteAction(isFavorite = isCurrentMediaIdFavorite)
            mediaSession?.setCustomLayout(musicSessionCallback.customLayout)
        }
    }*/

    private inner class PlayerListener : Player.Listener {
        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            if (mediaItem == null) return
            _currentMediaId.update { mediaItem.mediaId }
        }
    }
}

const val Player_ACTIVITY_PACKAGE_NAME = "com.abhishek.evergreenmusicplayer.MainActivity"