package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abhishek.evergreenmusicplayer.utils.AlbumDestination
import com.abhishek.evergreenmusicplayer.utils.AlbumDetailDestination
import com.abhishek.evergreenmusicplayer.utils.ArtistDestination
import com.abhishek.evergreenmusicplayer.utils.ArtistDetailDestination
import com.abhishek.evergreenmusicplayer.utils.AssetParamType
import com.abhishek.evergreenmusicplayer.utils.DashboardDestination
import com.abhishek.evergreenmusicplayer.utils.EMPConstants
import com.abhishek.evergreenmusicplayer.utils.HomeDestination
import com.abhishek.evergreenmusicplayer.utils.PermissionDestination
import com.abhishek.evergreenmusicplayer.utils.PlayerDestination
import com.abhishek.evergreenmusicplayer.utils.PlaylistDestination
import com.abhishek.evergreenmusicplayer.utils.SettingDestination
import com.abhishek.evergreenmusicplayer.utils.SongsDestination
import com.abhishek.evergreenmusicplayer.utils.showToast
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.gson.Gson

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun EMPNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    permissionState: PermissionState,
    startDestination: String,
    appState: EMPAppState
) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = startDestination) {
        composable(PermissionDestination.route) {
            PermissionScreen(permissionState)
        }
        composable(DashboardDestination.route) {
            DashboardScreen {

            }
        }
        composable(HomeDestination.route) {
            if (EMPConstants.displayTabLayout)
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
            val isPlaying = true
            PlayerScreen(backStackEntry.arguments?.getParcelable("song"), isPlaying,
                {
                    context.showToast("Play Clicked", false)
                }, {
                    context.showToast("Pause Clicked", false)
                }, {}) {

            }
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

        composable(PlaylistDestination.route) {
            RenderPlayList()
        }

        composable(SettingDestination.route) {
            SettingScreen()
        }
    }
}