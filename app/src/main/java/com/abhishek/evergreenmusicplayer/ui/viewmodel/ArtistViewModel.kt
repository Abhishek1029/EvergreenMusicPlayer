package com.abhishek.evergreenmusicplayer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.evergreenmusicplayer.data.Artist
import com.abhishek.evergreenmusicplayer.data.Songs
import com.abhishek.evergreenmusicplayer.ui.usecase.ArtistUseCase
import com.abhishek.evergreenmusicplayer.ui.usecase.SongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {
    init {
        getArtistList()
    }
    private val _artistFlow: MutableStateFlow<List<Artist>> = MutableStateFlow(emptyList())
    val artistFlow: StateFlow<List<Artist>> = _artistFlow
    private fun getArtistList() = viewModelScope.launch(Dispatchers.Main) {
        artistUseCase().flowOn(Dispatchers.IO)
            .catch {
                _artistFlow.value = emptyList()
            }
            .collect {
                _artistFlow.value = it
            }
    }
}