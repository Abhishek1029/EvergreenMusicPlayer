package com.abhishek.evergreenmusicplayer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.UnstableApi
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.ui.usecase.PlayerConnection
import com.abhishek.evergreenmusicplayer.ui.usecase.SongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@UnstableApi @HiltViewModel
class MusicViewModel @Inject constructor(
    private val songsUseCase: SongsUseCase,
    private val playerConnection: PlayerConnection
) : ViewModel() {
    init {
        getSongs()
    }
    private val _songsFlow: MutableStateFlow<List<Songs>> = MutableStateFlow(emptyList())
    val songsFlow: StateFlow<List<Songs>> = _songsFlow
    private fun getSongs() = viewModelScope.launch(Dispatchers.Main) {
        songsUseCase().flowOn(Dispatchers.IO)
            .catch {
                _songsFlow.value = emptyList()
            }
            .collect {
                _songsFlow.value = it
            }
    }

    fun playSongs(startIndex: Int = 0,startPosition: Long = 0L){
        playerConnection.playSongs(songsFlow.value,startIndex,startPosition)
    }
}