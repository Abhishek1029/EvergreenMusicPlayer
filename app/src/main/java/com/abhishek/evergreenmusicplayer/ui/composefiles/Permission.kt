package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.data.PermissionItem
import com.abhishek.evergreenmusicplayer.utils.changeColorOfTextInString
import com.abhishek.evergreenmusicplayer.utils.permissionItemList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
@Composable
fun PermissionScreen(permissionState: PermissionState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        CustomAnnotatedText(
            annotatedText = changeColorOfTextInString(
                stringResource(id = R.string.ok),
                stringResource(id = R.string.need_access)
            ), 20.sp,
            TextAlign.Center,
            Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        /**
         * LazyColumn:- Used for displaying list of Items
         */
        LazyColumn(
            contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            items(permissionItemList){ item->
                PermissionListComposable(item)
            }
        }

        OutlinedButton(onClick = {
            permissionState.launchPermissionRequest()
        }) {
            Text(text = stringResource(id = R.string.allow_access))
        }
    }
}

@Composable
fun PermissionListComposable(permissionItem: PermissionItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        Icon(painter = painterResource(id = permissionItem.icon), contentDescription = null)
        Column {
            CustomText(
                text = stringResource(id = permissionItem.title),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            CustomText(
                text = stringResource(id = permissionItem.description),
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }
    }
}