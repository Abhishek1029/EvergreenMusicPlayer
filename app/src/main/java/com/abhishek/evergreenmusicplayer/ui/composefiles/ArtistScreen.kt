package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.ui.ArtistRepoImpl
import com.abhishek.evergreenmusicplayer.utils.getUriFromAlbumId
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RenderArtists(
    onArtistClick: (String) -> Unit
) {
    val context = LocalContext.current
    val listOfArtist = ArtistRepoImpl().getArtistList(context)
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.Top
    ) {
        items(listOfArtist) { item ->
            Surface(
                Modifier.padding(4.dp)
                    .clickable {
                        onArtistClick(item.artistId)
                    }
            ) {
                Column {
                    GlideImage(
                        model = getUriFromAlbumId(item.firstSong.albumId),
                        contentDescription = null,
                        Modifier.height(175.dp),
                        contentScale = ContentScale.FillBounds
                    ){
                        it.placeholder(R.drawable.artist_image)
                    }
                    CustomText(
                        text = item.firstSong.artistName,
                        fontSize = 16.sp,
                        color = Color.Black,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}