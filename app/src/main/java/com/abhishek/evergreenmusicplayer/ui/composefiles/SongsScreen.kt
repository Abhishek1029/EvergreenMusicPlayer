package com.abhishek.evergreenmusicplayer.ui.composefiles

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.ui.SongsRepoImpl
import com.abhishek.evergreenmusicplayer.utils.PlayerDestination
import com.abhishek.evergreenmusicplayer.utils.formatMillis
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

private const val TAG = "SongsScreen"

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RenderSongs(onSongClick: (Songs) -> Unit) {
    val songs = SongsRepoImpl()
    val songsList = songs.getSongs(LocalContext.current)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
            Color.White
        )
    ) {
        Column {
            CustomText(
                text = stringResource(id = R.string.total_songs, songsList.size),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(songsList) { song ->
                    RenderSong(song, onSongClick)
                }
            }
        }
/*        BottomMusicTab(
            Modifier
                .align(Alignment.BottomCenter)
        )*/
    }
}

@Composable
fun BottomMusicTab(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 5.dp, end = 5.dp)
            .background(Color.Blue)
    ) {
        val (title, artist, icon) = createRefs()
        CustomText(
            text = "Song Title",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 5.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(icon.start)
                })

        CustomText(text = "Artist Name",
            color = Color.White,
            modifier = Modifier
                .padding(start = 5.dp)
                .constrainAs(artist) {
                    start.linkTo(parent.start)
                    top.linkTo(title.bottom)
                    end.linkTo(icon.start)
                })
/*
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            Modifier.constrainAs(icon) {
                end.linkTo(parent.end)
                centerVerticallyTo(parent)
            })*/
    }
}
