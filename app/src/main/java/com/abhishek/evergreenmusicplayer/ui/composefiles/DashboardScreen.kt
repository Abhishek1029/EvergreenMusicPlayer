package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.utils.wishAccordingToTime

@Composable
fun DashboardScreen(
    onRedirectHomeBtnClick: () -> Unit
) {
    Surface() {
        val context = LocalContext.current
        ConstraintLayout {
            val (profileIcon, wishTextView, recentlyPlayedTV,
                recentlyPlayedLR, mostPlayedArtistTV,
                mostPlayedArtistLR, goToHomeBtn) = createRefs()

            Icon(
                painter = painterResource(id = R.drawable.artist_image),
                contentDescription = stringResource(
                    id = R.string.user_name
                ),
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(BorderStroke(2.dp, Color.White), shape = CircleShape)
                    .padding(4.dp)
                    .constrainAs(profileIcon) {
                        top.linkTo(parent.top, margin = 30.dp)
                        start.linkTo(parent.start, margin = 20.dp)
                    }
            )

            CustomText(
                text = "${stringResource(id = R.string.good)}${context.wishAccordingToTime()}",
                modifier = Modifier
                    .height(50.dp)
                    .constrainAs(wishTextView) {
                        top.linkTo(parent.top, margin = 30.dp)
                        start.linkTo(profileIcon.end, margin = 10.dp)
                    }, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold
            )

            CustomText(
                text = stringResource(id = R.string.recently_played),
                modifier = Modifier
                    .height(50.dp)
                    .constrainAs(recentlyPlayedTV) {
                        top.linkTo(profileIcon.bottom, margin = 30.dp)
                        start.linkTo(profileIcon.start)
                    }, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold
            )

/*            LazyRow(content =, modifier = Modifier
                .constrainAs(recentlyPlayedLR){

                })*/
        }
    }
}