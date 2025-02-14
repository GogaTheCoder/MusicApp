package com.example.musicapp.data.remote.response

import com.example.musicapp.domain.model.Track

data class TrackResponse(
    val id: Long,
    val title: String,
    val artist: ArtistResponse,
    val preview: String,
    val cover: String
) {
    fun toDomain(): Track {
        return Track(
            id = id,
            title = title,
            artist = artist.name,
            previewUrl = preview,
            coverUrl = cover
        )
    }
}
