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
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.ui.theme.EvergreenMusicPlayerTheme
import com.abhishek.evergreenmusicplayer.utils.*
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
            /* val startDestination = if (permissionState.status.isGranted) {
                 HomeDestination.route
             } else {
                 PermissionDestination.route
             }*/
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
                            navController.navigate(ArtistDestination.createRoute(artistId))
                        },
                        onAlbumClick = { albumId ->
                            navController.navigate(AlbumDestination.createRoute(albumId))
                        }
                    )
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
                composable(ArtistDestination.route) { backStackEntry ->
                    ArtistDetail(backStackEntry.arguments?.getString("artistId"))
                }

                composable(AlbumDestination.route) { backStackEntry ->
                    AlbumDetail(backStackEntry.arguments?.getString("albumId"))
                }
            }
        }
    }
}