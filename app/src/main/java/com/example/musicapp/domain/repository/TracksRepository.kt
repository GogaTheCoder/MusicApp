package com.example.musicapp.domain.repository

import com.example.musicapp.domain.model.Track

interface TracksRepository {
    fun getLocalTracks(): List<Track>
    suspend fun getApiTracks(): List<Track>
    suspend fun searchApiTracks(query: String): List<Track>
}