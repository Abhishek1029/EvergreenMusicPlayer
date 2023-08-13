package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun CustomPlayerButtons(
    @DrawableRes iconDrawable: Int,
    @StringRes contentDesStr: Int,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = iconDrawable),
            contentDescription = null
        )
    }
}