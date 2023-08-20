package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.ui.repository.SongsRepoImpl
import com.abhishek.evergreenmusicplayer.ui.viewmodel.MusicViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

private const val TAG = "SongsScreen"

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RenderSongs(
    musicViewModel: MusicViewModel = hiltViewModel(),
    onSongClick: (Songs, Int) -> Unit) {
    val songState = musicViewModel.songsFlow.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.White
            )
    ) {
        Column {
            CustomText(
                text = stringResource(id = R.string.total_songs, songState.value.size),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(songState.value) { song ->
                    RenderSong(song, songState.value.indexOf(song), onSongClick)
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
