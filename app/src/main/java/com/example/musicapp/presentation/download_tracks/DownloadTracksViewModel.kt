package com.example.musicapp.presentation.download_tracks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.domain.model.Track

class DownloadTracksViewModel : ViewModel() {

    private val _tracks = MutableLiveData<List<Track>?>()
    val tracks: MutableLiveData<List<Track>?> get() = _tracks

    fun downloadTracks() {
        // Загрузка треков (например, из репозитория)
        val tracks = listOf(
            Track(1, "Song 1", "Artist 1", "https://example.com/cover1.jpg", null),
            Track(2, "Song 2", "Artist 2", "https://example.com/cover2.jpg", null)
        )
        _tracks.value = tracks
    }

    fun searchTracks(query: String) {
        // Фильтрация треков по запросу
        val filteredTracks = _tracks.value?.filter { track ->
            track.title.contains(query, true) || track.artist.contains(query, true)
        }
        _tracks.value = filteredTracks
    }
}