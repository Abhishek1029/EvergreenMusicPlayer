package com.abhishek.evergreenmusicplayer.ui.service

import android.content.Context
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.media3.session.CommandButton
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionCommand
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.utils.PlaybackCommands.FAVORITE
import com.abhishek.evergreenmusicplayer.utils.PlaybackCommands.FAVORITE_OFF
import com.abhishek.evergreenmusicplayer.utils.PlaybackCommands.FAVORITE_ON
import com.abhishek.evergreenmusicplayer.utils.PlaybackCommands.PLAYBACK_MODE
import com.abhishek.evergreenmusicplayer.utils.PlaybackCommands.PLAYBACK_MODE_REPEAT
import com.abhishek.evergreenmusicplayer.utils.PlaybackCommands.PLAYBACK_MODE_REPEAT_ONE
import com.abhishek.evergreenmusicplayer.utils.PlaybackCommands.PLAYBACK_MODE_SHUFFLE
import com.abhishek.evergreenmusicplayer.utils.PlaybackMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerActionHandler @Inject constructor(
    @ApplicationContext private val context: Context,
    /*private val setPlaybackModeUseCase: SetPlaybackModeUseCase,
    private val toggleFavoriteSongUseCase: ToggleFavoriteSongUseCase*/
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    val customCommands = getAvailableCustomCommands()
    private val customLayoutMap = mutableMapOf<String, CommandButton>().apply {
        this[PLAYBACK_MODE] = customCommands.getValue(PLAYBACK_MODE_REPEAT)
        this[FAVORITE] = customCommands.getValue(FAVORITE_OFF)
    }
    val customLayout: List<CommandButton> get() = customLayoutMap.values.toList()

    fun onCustomCommand(mediaSession: MediaSession, customCommand: SessionCommand) {
        when (customCommand.customAction) {
            PLAYBACK_MODE_REPEAT, PLAYBACK_MODE_REPEAT_ONE, PLAYBACK_MODE_SHUFFLE -> {
               // handleRepeatShuffleCommand(action = customCommand.customAction)
            }

            FAVORITE_ON, FAVORITE_OFF -> {
                val id = mediaSession.player.currentMediaItem?.mediaId ?: return
               // handleFavoriteCommand(action = customCommand.customAction, id = id)
            }
        }
    }

    fun setRepeatShuffleCommand(action: String) =
        customLayoutMap.set(PLAYBACK_MODE, customCommands.getValue(action))

    fun setFavoriteCommand(action: String) =
        customLayoutMap.set(FAVORITE, customCommands.getValue(action))

    fun cancelCoroutineScope() = coroutineScope.cancel()

/*    private fun handleRepeatShuffleCommand(action: String) = coroutineScope.launch {
        when (action) {
            PLAYBACK_MODE_REPEAT -> setPlaybackModeUseCase(PlaybackMode.REPEAT_ONE)
            PLAYBACK_MODE_REPEAT_ONE -> setPlaybackModeUseCase(PlaybackMode.SHUFFLE)
            PLAYBACK_MODE_SHUFFLE -> setPlaybackModeUseCase(PlaybackMode.REPEAT)
        }
    }

    private fun handleFavoriteCommand(action: String, id: String) = coroutineScope.launch {
        when (action) {
            FAVORITE_ON -> toggleFavoriteSongUseCase(id = id, isFavorite = false)
            FAVORITE_OFF -> toggleFavoriteSongUseCase(id = id, isFavorite = true)
        }
    }*/

    private fun getAvailableCustomCommands() = mapOf(
        PLAYBACK_MODE_REPEAT to buildCustomCommand(
            action = PLAYBACK_MODE_REPEAT,
            displayName = context.getString(R.string.repeat),
            iconResource = R.drawable.ic_baseline_repeat_24
        ),
        PLAYBACK_MODE_REPEAT_ONE to buildCustomCommand(
            action = PLAYBACK_MODE_REPEAT_ONE,
            displayName = context.getString(R.string.repeat_one),
            iconResource = R.drawable.baseline_repeat_one_24
        ),
        PLAYBACK_MODE_SHUFFLE to buildCustomCommand(
            action = PLAYBACK_MODE_SHUFFLE,
            displayName = context.getString(R.string.shuffle),
            iconResource = R.drawable.round_shuffle_24
        ),
        FAVORITE_ON to buildCustomCommand(
            action = FAVORITE_ON,
            displayName = context.getString(R.string.favorite_remove),
            iconResource = R.drawable.ic_sharp_favorite_border_24
        ),
        FAVORITE_OFF to buildCustomCommand(
            action = FAVORITE_OFF,
            displayName = context.getString(R.string.favorite_add),
            iconResource = R.drawable.ic_sharp_favorite_24
        )
    )
}

private fun buildCustomCommand(
    action: String,
    displayName: String,
    @DrawableRes iconResource: Int,
) = CommandButton.Builder()
    .setSessionCommand(SessionCommand(action, Bundle.EMPTY))
    .setDisplayName(displayName)
    .setIconResId(iconResource)
    .build()
