package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.Manifest
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abhishek.evergreenmusicplayer.ui.theme.EvergreenMusicPlayerTheme
import com.abhishek.evergreenmusicplayer.utils.*
import com.abhishek.evergreenmusicplayer.utils.EMPConstants.displayTabLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.gson.Gson

@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Composable
fun EvergreenMusicPlayerApp(
    navController: NavHostController,
    permissionState: PermissionState = rememberPermissionState(permission = EMPAudioPermission)
) {
    when (permissionState.status) {
        PermissionStatus.Granted -> {
            EMPAppContent(navController, permissionState)
        }

        is PermissionStatus.Denied -> {
            PermissionScreen(permissionState)
        }
    }
}