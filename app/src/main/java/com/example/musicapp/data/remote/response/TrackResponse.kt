package com.example.musicapp.data.remote.response

data class TrackResponse(
    val id: Long,
    val title: String,
    val artist: ArtistResponse,
    val preview: String
)
