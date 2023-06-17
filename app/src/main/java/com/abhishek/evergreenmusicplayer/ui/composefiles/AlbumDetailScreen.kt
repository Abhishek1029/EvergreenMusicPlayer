package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.ui.AlbumRepoImpl
import com.abhishek.evergreenmusicplayer.utils.getSongText
import com.abhishek.evergreenmusicplayer.utils.getUriFromAlbumId
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AlbumDetail(
    albumId: String?
) {
    val context = LocalContext.current
    val album = AlbumRepoImpl().getAlbumById(context, albumId)
    Surface(Modifier.fillMaxWidth()) {
        ConstraintLayout {
            val (icon, artistName, songCount, songList) = createRefs()
            val startGuideline = createGuidelineFromStart(16.dp)
            val endGuideline = createGuidelineFromEnd(16.dp)
            GlideImage(
                model = getUriFromAlbumId(album?.firstSong?.albumId ?: ""),
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                    },
                contentScale = ContentScale.FillBounds
            ) {
                it.placeholder(R.drawable.artist_image)
            }
            CustomText(
                text = album?.firstSong?.albumName ?: "Anari",
                Modifier.constrainAs(artistName) {
                    start.linkTo(startGuideline)
                    top.linkTo(icon.bottom, margin = 20.dp)
                },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            CustomText(
                text = (album?.songsList?.size ?: 0).getSongText(),
                Modifier.constrainAs(songCount) {
                    start.linkTo(startGuideline)
                    top.linkTo(artistName.bottom, margin = 5.dp)
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            LazyColumn(Modifier.constrainAs(songList) {
                top.linkTo(songCount.bottom, margin = 20.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                bottom.linkTo(parent.bottom)
            }) {
                items(album?.songsList ?: emptyList()) { song ->
                    RenderSong(song = song)
                }
            }
        }
    }
}