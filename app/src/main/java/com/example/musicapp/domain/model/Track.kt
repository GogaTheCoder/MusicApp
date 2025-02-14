package com.example.musicapp.domain.model

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val coverUrl: String?,
    val previewUrl: String?,
    val isDownload: Boolean = false
)
