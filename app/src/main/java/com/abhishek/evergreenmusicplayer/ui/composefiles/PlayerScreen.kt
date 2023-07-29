package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.util.Log
import androidx.compose.runtime.Composable
import com.abhishek.evergreenmusicplayer.data.Songs

private const val TAG = "PlayerScreen"

@Composable
fun PlayerScreen(
    songData: Songs?
) {
    PlayerBackgroundArtwork(songData) {
        SingleLineText(songData?.title ?: "")
    }
}