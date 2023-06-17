package com.abhishek.evergreenmusicplayer.utils

import android.net.Uri
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ALBUM_DETAIL
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ARTIST_DETAIL
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.DASHBOARD
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.HOME
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.PERMISSION
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.PLAYER

interface NavDestinations {
    val route: String
}

object HomeDestination : NavDestinations {
    override val route: String
        get() = HOME

}

object DashboardDestination : NavDestinations {
    override val route: String
        get() = DASHBOARD

}

object PermissionDestination : NavDestinations {
    override val route: String
        get() = PERMISSION
}

object PlayerDestination : NavDestinations {
    override val route: String
        get() = PLAYER

    fun createRoute(song: String) = "player/$song"
}

object ArtistDestination : NavDestinations {
    override val route: String
        get() = ARTIST_DETAIL

    fun createRoute(artistId: String) = "artistDetail/$artistId"
}

object AlbumDestination : NavDestinations {
    override val route: String
        get() = ALBUM_DETAIL

    fun createRoute(albumId: String) = "albumDetail/$albumId"
}
