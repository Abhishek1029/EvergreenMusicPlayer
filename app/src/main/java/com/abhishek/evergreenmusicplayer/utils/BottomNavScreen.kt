package com.abhishek.evergreenmusicplayer.utils

import androidx.annotation.StringRes
import com.abhishek.evergreenmusicplayer.R

sealed class BottomNavScreen(val route:String, @StringRes val resourceId: Int) {
    object Songs : BottomNavScreen(SongsDestination.route, R.string.songs)
    object Artist : BottomNavScreen(ArtistDestination.route, R.string.artist)
    object Album : BottomNavScreen(AlbumDestination.route, R.string.album)
}
