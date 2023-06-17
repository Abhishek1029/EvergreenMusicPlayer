package com.abhishek.evergreenmusicplayer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Songs(
    val id:String,
    val title:String,
    val albumName:String,
    val path:String,
    val duration:String,
    val albumId:String,
    val artistId:String,
    val artistName:String
) : Parcelable
