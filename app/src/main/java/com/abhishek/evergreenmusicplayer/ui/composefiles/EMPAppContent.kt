package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.abhishek.evergreenmusicplayer.utils.BottomNavScreen
import com.abhishek.evergreenmusicplayer.utils.SongsDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun EMPAppContent(
    permissionState: PermissionState,
    appState: EMPAppState
) {
    val bottomTabItems = listOf(
        BottomNavScreen.Songs,
        BottomNavScreen.Artist,
        BottomNavScreen.Album,
        BottomNavScreen.Playlist,
        BottomNavScreen.Settings
    )
    Scaffold(
        bottomBar = {
            EMPNavigationBar(appState.navController, bottomTabItems,appState){
                appState.navigateToTopLevelDestination(it)
            }
        }
    ) {
        EMPNavHost(
            modifier = Modifier.padding(it),
            navController = appState.navController,
            permissionState = permissionState,
            startDestination = SongsDestination.route,
            appState
        )
    }
}