/*
package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import com.abhishek.evergreenmusicplayer.utils.HomeDestination
import com.abhishek.evergreenmusicplayer.utils.PermissionDestination
import com.google.accompanist.permissions.isGranted

val lifeCycleOwner = LocalLifecycleOwner.current
DisposableEffect(key1 = lifeCycleOwner, effect = {
    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            startDestination = if (permissionState.status.isGranted) {
                HomeDestination.route
            } else {
                PermissionDestination.route
            }
        }
    }
    lifeCycleOwner.lifecycle.addObserver(observer)
    onDispose {
        lifeCycleOwner.lifecycle.removeObserver(observer)
    }
})*/
