package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abhishek.evergreenmusicplayer.data.Songs

@Composable
fun PlayerScreen(
    songData: Songs?,
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onFavClick: () -> Unit
) {
    PlayerBackgroundArtwork(songData) {
        PlayerTitleArtist(
            songData
        )
        PlayerSeekbar()
        PlayerButtons(
            isPlaying,
            onPlayClick = onPlayClick,
            onPauseClick = onPauseClick,
            onNextClick = onNextClick,
            onFavClick = onFavClick
        )
    }
}

@Composable
fun PlayerTitleArtist(
    songData: Songs?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(
            fraction = 0.9f
        )
    ) {
        SingleLineText(songData?.title ?: "", fontSize = 23.sp, fontWeight = FontWeight.Bold)
        SingleLineText(songData?.artistName ?: "", fontSize = 16.sp)
    }
}