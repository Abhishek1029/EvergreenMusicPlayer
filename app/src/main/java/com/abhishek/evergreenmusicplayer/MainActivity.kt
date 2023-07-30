package com.abhishek.evergreenmusicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.abhishek.evergreenmusicplayer.ui.composefiles.EvergreenMusicPlayerApp
import com.abhishek.evergreenmusicplayer.ui.theme.EvergreenMusicPlayerTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvergreenMusicPlayerTheme {
                EvergreenMusicPlayerApp()
            }
        }
    }
}