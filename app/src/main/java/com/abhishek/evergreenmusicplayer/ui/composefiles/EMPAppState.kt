package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abhishek.evergreenmusicplayer.utils.BottomNavScreen
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ALBUM
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.ARTIST
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.PLAYLIST
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.SETTING
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.SONGS
import com.abhishek.evergreenmusicplayer.utils.contains
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@Composable
fun rememberEMPAppState(
    navController: NavHostController = rememberNavController(),
    startDestination: BottomNavScreen = BottomNavScreen.Songs
) = remember(navController, startDestination) {
    EMPAppState(navController, startDestination)
}

@OptIn(ExperimentalPermissionsApi::class)
@Stable
class EMPAppState(
    val navController: NavHostController,
    val startDestination: BottomNavScreen
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    private val currentTopLevelDestination: BottomNavScreen
        @Composable get() {
            topLevelDestinations.firstOrNull { it.route in currentDestination }
                ?.let { _currentTopLevelDestination = it }
            return _currentTopLevelDestination
        }

    val isTopLevelDestination: Boolean
        @Composable get() = currentTopLevelDestination.route in currentDestination

    val shouldShowBackButton: Boolean
        @Composable get() = currentDestination != null && topLevelRoutes.none { it in currentDestination }

    val topLevelDestinations = arrayOf(
        BottomNavScreen.Songs,
        BottomNavScreen.Artist,
        BottomNavScreen.Album,
        BottomNavScreen.Playlist,
        BottomNavScreen.Settings
    )

    private var _currentTopLevelDestination by mutableStateOf(startDestination)
    private val topLevelRoutes = listOf(SONGS, ARTIST, ALBUM, PLAYLIST, SETTING)

/*    val permissionState: PermissionState
        @Composable get() = rememberMusicmaxPermissionState { isPermissionRequested = true }

    var isPermissionRequested by mutableStateOf(false)
        private set*/

    fun navigateToTopLevelDestination(destination: BottomNavScreen) =
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
}