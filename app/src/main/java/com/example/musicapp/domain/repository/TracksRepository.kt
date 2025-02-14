package com.example.musicapp.domain.repository

import com.example.musicapp.domain.model.Track

interface TracksRepository {
    suspend fun getApiTracks(): List<Track>
    suspend fun getDownloadTracks(): List<Track>
    suspend fun searchTracks(query: String): List<Track>
}