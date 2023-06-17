package com.abhishek.evergreenmusicplayer.data

data class Album(
    val albumId: String,
    val songsList: List<Songs>,
    val firstSong: Songs = songsList.firstOrNull() ?: Songs(
        "-1",
        "Aaye Ho Meri Zindagi Me",
        "Raja Hindustani",
        "",
        "",
        "52217321113",
        "124357773666",
        "Udit Narayan"
    )
)
