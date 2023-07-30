package com.abhishek.evergreenmusicplayer.di

import android.content.ContentResolver
import android.content.Context
import com.abhishek.evergreenmusicplayer.ui.repository.MusicRepository
import com.abhishek.evergreenmusicplayer.ui.repository.MusicRepositoryImpl
import com.abhishek.evergreenmusicplayer.utils.MusicDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MusicModule {
    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver =
        context.contentResolver

    @Provides
    fun provideMusicRepository(musicDataSource: MusicDataSource): MusicRepository = MusicRepositoryImpl(musicDataSource)
}