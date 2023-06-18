package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.abhishek.evergreenmusicplayer.utils.BottomNavScreen

private const val TAG = "EMPNavigationBar"

@Composable
fun EMPNavigationBar(
    navController: NavHostController,
    bottomTabItems: List<BottomNavScreen>,
    appState: EMPAppState,
    onNavItemClick: (BottomNavScreen) -> Unit
) {
    NavigationBar(
        containerColor = Color.Magenta
    ) {
        bottomTabItems.forEach { bottomTabItems ->
            Log.e(TAG, "EMPNavigationBar: " )
            NavigationBarItem(selected = appState.currentDestination.isTopLevelDestinationInHierarchy(
                bottomTabItems
            ),
                icon = {
                    Icon(imageVector = bottomTabItems.icon, contentDescription = null)
                }, onClick = {
                    onNavItemClick(bottomTabItems)
                },
                label = { Text(text = stringResource(id = bottomTabItems.resourceId)) })
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomNavScreen) =
    this?.hierarchy?.any { it.route == destination.route } == true