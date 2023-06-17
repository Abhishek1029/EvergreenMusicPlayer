package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.abhishek.evergreenmusicplayer.utils.AlbumDetailDestination
import com.abhishek.evergreenmusicplayer.utils.ArtistDetailDestination
import com.abhishek.evergreenmusicplayer.utils.PlayerDestination
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTabLayout(
    navController:NavController
) {
    TabLayout(
        onSongClick = { song ->
            navController.navigate(
                PlayerDestination.createRoute(
                    Uri.encode(
                        Gson().toJson(
                            song
                        )
                    )
                )
            )
        },
        onArtistClick = { artistId ->
            navController.navigate(ArtistDetailDestination.createRoute(artistId))
        },
        onAlbumClick = { albumId ->
            navController.navigate(AlbumDetailDestination.createRoute(albumId))
        }
    )
}