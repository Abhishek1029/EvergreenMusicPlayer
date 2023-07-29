package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.BottomNavScreen

@Composable
fun PlayerBackgroundArtwork(
    songData: Songs?,
    content: @Composable ColumnScope.() -> Unit
) {

    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        PlayerFrontArtwork()
        content()
    }

}