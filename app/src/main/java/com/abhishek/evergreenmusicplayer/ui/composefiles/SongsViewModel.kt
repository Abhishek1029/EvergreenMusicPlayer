package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.lifecycle.ViewModel
import com.abhishek.evergreenmusicplayer.ui.repository.SongsRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(songsRepoImpl: SongsRepoImpl) : ViewModel() {

    fun getSongsList(){

    }
}