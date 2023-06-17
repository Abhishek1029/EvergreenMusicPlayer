package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.Manifest
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abhishek.evergreenmusicplayer.ui.theme.EvergreenMusicPlayerTheme
import com.abhishek.evergreenmusicplayer.utils.*
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.displayTabLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.gson.Gson

@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Composable
fun EvergreenMusicPlayerApp() {
    EvergreenMusicPlayerTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            val navController = rememberNavController()
            val permissionState =
                rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
            val startDestination = permissionState.getStartDestination()
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
                composable(PlayerDestination.route,
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