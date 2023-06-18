package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhishek.evergreenmusicplayer.R
import com.abhishek.evergreenmusicplayer.data.PermissionItem
import com.abhishek.evergreenmusicplayer.utils.permissionItemList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
@Composable
fun PermissionScreen(permissionState: PermissionState) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Magenta
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.app_name),
                    color = Color.White,
                    fontSize = 16.sp,
                    alignment = TextAlign.Center
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /**
             * LazyColumn:- Used for displaying list of Items Vertically
             */
            LazyColumn(
                contentPadding = it,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(permissionItemList) { item ->
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
}

@Composable
fun PermissionListComposable(permissionItem: PermissionItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        Column {
            CustomText(
                text = stringResource(id = permissionItem.title),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomText(
                text = stringResource(id = permissionItem.description),
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}