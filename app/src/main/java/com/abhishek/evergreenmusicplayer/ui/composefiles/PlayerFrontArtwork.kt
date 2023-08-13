package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.getUriFromAlbumId
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerFrontArtwork(
    song:Songs?
) {
    GlideImage(
        model = getUriFromAlbumId(song?.albumId?:"0"), contentDescription = null,
        modifier = Modifier.fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.FillHeight
    ){
        it.placeholder(R.drawable.artist_image)
    }
}