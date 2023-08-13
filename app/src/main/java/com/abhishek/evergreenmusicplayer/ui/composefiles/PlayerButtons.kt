package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abhishek.evergreenmusicplayer.R

@Composable
fun PlayerButtons(
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onFavClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomPlayerButtons(
            iconDrawable = R.drawable.ic_baseline_repeat_24,
            contentDesStr = R.string.duration
        ) {

        }
        CustomPlayerButtons(
            iconDrawable = R.drawable.ic_sharp_skip_previous_24,
            contentDesStr = R.string.duration
        ) {

        }

        PlayPauseButton(
            isPlaying,
            R.drawable.ic_round_play_arrow_24,
            R.drawable.ic_round_pause_24,
            onPlayClick = onPlayClick,
            onPauseClick = onPauseClick
        )

        CustomPlayerButtons(
            iconDrawable = R.drawable.ic_sharp_skip_next_24,
            contentDesStr = R.string.duration,
            onClick = onNextClick
        )

        CustomPlayerButtons(
            iconDrawable = R.drawable.ic_sharp_favorite_border_24,
            contentDesStr = R.string.duration,
            onClick = onFavClick
        )
    }
}

@Composable
fun PlayPauseButton(
    isPlaying: Boolean,
    @DrawableRes playIcon: Int,
    @DrawableRes pauseIcon: Int,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit
) {
    CustomPlayerButtons(
        iconDrawable = if (isPlaying) playIcon else pauseIcon,
        contentDesStr = R.string.duration,
        onClick = if (isPlaying) onPlayClick else onPauseClick
    )
}