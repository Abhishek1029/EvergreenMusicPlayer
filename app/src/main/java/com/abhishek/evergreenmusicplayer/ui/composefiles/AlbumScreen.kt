package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.abhishek.evergreenmusicplayer.ui.AlbumRepoImpl
import com.abhishek.evergreenmusicplayer.utils.getUriFromAlbumId
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RenderAlbums(
    onAlbumClick : (String) -> Unit
) {
    val context = LocalContext.current
    val listOfArtist = AlbumRepoImpl().getAlbumList(context)
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(listOfArtist) { item ->
            Surface(
                Modifier.padding(4.dp)
                    .clickable {
                        onAlbumClick(item.albumId)
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
                        text = item.firstSong.albumName,
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