package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.abhishek.evergreenmusicplayer.utils.BottomNavScreen

@Composable
fun HomeWithBottomNav(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavScreen.Songs,
        BottomNavScreen.Artist,
        BottomNavScreen.Album,
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                /** Pop up to the start destination of the graph to
                                 avoid building up a large stack of destinations
                                 on the back stack as users select items **/
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                /** Avoid multiple copies of the same destination when
                                 reselecting the same item **/
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ){
        Log.e("TAG", "HomeWithBottomNav: $it")
    }
}