package com.abhishek.evergreenmusicplayer.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.abhishek.evergreenmusicplayer.R

sealed class BottomNavScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Songs : BottomNavScreen(SongsDestination.route, R.string.songs, Icons.Default.PlayArrow)
    object Artist : BottomNavScreen(ArtistDestination.route, R.string.artist, Icons.Default.Person)
    object Album : BottomNavScreen(AlbumDestination.route, R.string.album, Icons.Default.Favorite)

    object Playlist : BottomNavScreen(PlaylistDestination.route, R.string.playlist, Icons.Default.List)
    object Settings :
        BottomNavScreen(SettingDestination.route, R.string.settings, Icons.Default.Settings)
}
