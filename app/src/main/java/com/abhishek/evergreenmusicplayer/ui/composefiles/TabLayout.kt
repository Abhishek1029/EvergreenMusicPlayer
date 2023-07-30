package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.EMPUtils.tabTitles
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabLayout(
    onSongClick: (Songs) -> Unit,
    onArtistClick: (String) -> Unit,
    onAlbumClick: (String) -> Unit
) {
    Column {
        val pagerState = rememberPagerState(pageCount = tabTitles.size)
        val coroutineScope = rememberCoroutineScope()
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.White,
            backgroundColor = Color.Magenta,
            modifier = Modifier.height(56.dp)
        ) {
            tabTitles.forEachIndexed { index, _ ->
                Tab(
                    selected = pagerState.currentPage == index, onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = tabTitles[index].second),
                            contentDescription = null
                        )
                    },
                    text = {
                        Text(text = tabTitles[index].first)
                    }
                )
            }
        }

        TabContents(
            pagerState, onSongClick = onSongClick,
            onArtistClick = onArtistClick, onAlbumClick = onAlbumClick
        )
    }
}

@ExperimentalPagerApi
@Composable
fun TabContents(
    pagerState: PagerState,
    onSongClick: (Songs) -> Unit,
    onArtistClick: (String) -> Unit,
    onAlbumClick: (String) -> Unit
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> {
                RenderSongs(onSongClick = onSongClick)
            }
            1 -> {
                RenderArtists(onArtistClick = onArtistClick)
            }
            2 -> {
                RenderAlbums(onAlbumClick = onAlbumClick)
            }
        }
    }
}