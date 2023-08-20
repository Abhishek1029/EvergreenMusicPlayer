package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.utils.formatMillis
import com.abhishek.evergreenmusicplayer.utils.getUriFromAlbumId
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@ExperimentalGlideComposeApi
@Composable
fun RenderSong(song: Songs, index: Int = 0, onSongClick: (Songs, Int) -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onSongClick(song,index)
        }.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
    ) {
        GlideImage(
            model = getUriFromAlbumId(song.albumId), contentDescription = null,
            modifier = Modifier
                .size(50.dp)
        ){
            it.placeholder(R.drawable.artist_image)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            CustomText(
                text = song.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )

            CustomText(
                text = song.albumName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 2.dp)
            )

            CustomText(
                text = stringResource(id = R.string.duration, formatMillis(song.duration)),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        }
    }
}