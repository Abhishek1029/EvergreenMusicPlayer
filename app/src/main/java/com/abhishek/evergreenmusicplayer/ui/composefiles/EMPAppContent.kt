package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abhishek.evergreenmusicplayer.ui.theme.EvergreenMusicPlayerTheme
import com.abhishek.evergreenmusicplayer.utils.AlbumDestination
import com.abhishek.evergreenmusicplayer.utils.AlbumDetailDestination
import com.abhishek.evergreenmusicplayer.utils.ArtistDestination
import com.abhishek.evergreenmusicplayer.utils.ArtistDetailDestination
import com.abhishek.evergreenmusicplayer.utils.AssetParamType
import com.abhishek.evergreenmusicplayer.utils.DashboardDestination
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.displayTabLayout
import com.abhishek.evergreenmusicplayer.utils.HomeDestination
import com.abhishek.evergreenmusicplayer.utils.PermissionDestination
import com.abhishek.evergreenmusicplayer.utils.PlayerDestination
import com.abhishek.evergreenmusicplayer.utils.SongsDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.gson.Gson

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun EMPAppContent(
    navController: NavHostController,
    permissionState: PermissionState
) {
    EvergreenMusicPlayerTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            val startDestination = HomeDestination.route
            NavHost(navController = navController, startDestination = startDestination) {
                composable(PermissionDestination.route) {
                    PermissionScreen(permissionState)
                }
                composable(DashboardDestination.route) {
                    DashboardScreen {

                    }
                }
                composable(HomeDestination.route) {
                    if (displayTabLayout)
                        HomeTabLayout(navController = navController)
                    else
                        HomeWithBottomNav(navController)
                }
                composable(
                    PlayerDestination.route,
                    arguments = listOf(
                        navArgument("song") {
                            type = AssetParamType()
                        }
                    )
                ) { backStackEntry ->
                    PlayerScreen(backStackEntry.arguments?.getParcelable("song"))
                }
                composable(ArtistDetailDestination.route) { backStackEntry ->
                    ArtistDetail(backStackEntry.arguments?.getString("artistId"))
                }

                composable(AlbumDetailDestination.route) { backStackEntry ->
                    AlbumDetail(backStackEntry.arguments?.getString("albumId"))
                }

                composable(SongsDestination.route) {
                    RenderSongs { song ->
                        navController.navigate(
                            PlayerDestination.createRoute(
                                Uri.encode(
                                    Gson().toJson(
                                        song
                                    )
                                )
                            )
                        )
                    }
                }
                composable(ArtistDestination.route) {
                    RenderArtists { artistId ->
                        navController.navigate(ArtistDetailDestination.createRoute(artistId))
                    }
                }

                composable(AlbumDestination.route) {
                    RenderAlbums { albumId ->
                        navController.navigate(AlbumDetailDestination.createRoute(albumId))
                    }
                }
            }
        }
    }
}