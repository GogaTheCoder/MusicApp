package com.example.musicapp.data.repository

import android.content.Context
import com.example.musicapp.data.dowload.DownloadTracksDataSource
import com.example.musicapp.data.remote.DeezerApiService
import com.example.musicapp.data.remote.response.TrackResponse
import com.example.musicapp.domain.model.Track
import com.example.musicapp.domain.repository.TracksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val apiService: DeezerApiService,
    private val localDataSource: DownloadTracksDataSource,
    private val context: Context
) : TracksRepository {

    // Получение треков из API
    override suspend fun getApiTracks(): List<Track> {
        return try {
            val response = apiService.getChart()
            if (response.isSuccessful) {
                response.body()?.tracks?.data?.map { it.toDomain() } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList() // В реальном проекте обработайте ошибку
        }
    }

    // Получение локальных треков
    override suspend fun getDownloadTracks(): List<Track> {
        return withContext(Dispatchers.IO) {
            downloadDataSource.getDownloadTracks(context)
        }
    }

    // Поиск треков через API
    override suspend fun searchTracks(query: String): List<Track> {
        return try {
            val response = apiService.searchTracks(query)
            if (response.isSuccessful) {
                response.body()?.data?.map { it.toDomain() } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Маппинг DTO → Domain
    private fun TrackResponse.toDomain(): Track {
        return Track(
            id = this.id,
            title = this.title,
            artist = this.artist.name,
            duration = this.duration,
            previewUrl = this.preview,
            isDownload = false // Для API-треков
        )
    }
}