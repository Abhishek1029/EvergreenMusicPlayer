package com.abhishek.evergreenmusicplayer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.evergreenmusicplayer.data.Album
import com.abhishek.evergreenmusicplayer.data.Artist
import com.abhishek.evergreenmusicplayer.ui.usecase.AlbumUseCase
import com.abhishek.evergreenmusicplayer.ui.usecase.ArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumUseCase: AlbumUseCase
) : ViewModel() {
    init {
        getAlbumsList()
    }
    private val _albumFlow: MutableStateFlow<List<Album>> = MutableStateFlow(emptyList())
    val albumFlow: StateFlow<List<Album>> = _albumFlow
    private fun getAlbumsList() = viewModelScope.launch(Dispatchers.Main) {
        albumUseCase().flowOn(Dispatchers.IO)
            .catch {
                _albumFlow.value = emptyList()
            }
            .collect {
                _albumFlow.value = it
            }
    }
}