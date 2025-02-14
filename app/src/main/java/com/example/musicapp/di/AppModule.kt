package com.example.musicapp.di

import com.example.musicapp.data.dowload.DownloadTracksDataSource
import com.example.musicapp.data.remote.DeezerApiService
import com.example.musicapp.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDeezerApiService(): DeezerApiService {
        return RetrofitClient.create()
    }
}