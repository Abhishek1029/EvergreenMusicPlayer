package com.abhishek.evergreenmusicplayer.ui.usecase

import com.abhishek.evergreenmusicplayer.ui.repository.MusicRepository
import javax.inject.Inject

class AlbumUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke() = musicRepository.albums
}