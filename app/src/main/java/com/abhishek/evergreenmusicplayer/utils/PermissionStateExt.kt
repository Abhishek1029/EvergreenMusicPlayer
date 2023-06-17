package com.abhishek.evergreenmusicplayer.utils

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.getStartDestination() = if (status.isGranted) {
    HomeDestination.route
} else {
    PermissionDestination.route
}