package com.abhishek.evergreenmusicplayer.utils

import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ALBUM
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ALBUM_DETAIL
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ARTIST
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ARTIST_DETAIL
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.DASHBOARD
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.HOME
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.PERMISSION
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.PLAYER
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.PLAYLIST
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.SETTING
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.SONGS

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

object SongsDestination : NavDestinations {
    override val route: String
        get() = SONGS
}
object ArtistDestination : NavDestinations {
    override val route: String
        get() = ARTIST
}

object AlbumDestination : NavDestinations {
    override val route: String
        get() = ALBUM
}

object PlaylistDestination : NavDestinations {
    override val route: String
        get() = PLAYLIST
}

object SettingDestination : NavDestinations {
    override val route: String
        get() = SETTING
}
object ArtistDetailDestination : NavDestinations {
    override val route: String
        get() = ARTIST_DETAIL

    fun createRoute(artistId: String) = "artistDetail/$artistId"
}

object AlbumDetailDestination : NavDestinations {
    override val route: String
        get() = ALBUM_DETAIL

    fun createRoute(albumId: String) = "albumDetail/$albumId"
}


