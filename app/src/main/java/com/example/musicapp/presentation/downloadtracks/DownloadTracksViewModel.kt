package com.example.musicapp.presentation.downloadtracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.model.Track
import com.example.musicapp.domain.repository.TracksRepository
import kotlinx.coroutines.launch

class LocalTracksViewModel(
    private val repository: TracksRepository
) : ViewModel() {

    private val _tracks = mutableListOf<Track>()
    val tracks: List<Track> get() = _tracks

    init {
        loadTracks()
    }

    private fun loadTracks() {
        _tracks.clear()
        _tracks.addAll(repository.getLocalTracks())
    }

    fun searchTracks(query: String) {
        _tracks.clear()
        _tracks.addAll(repository.getLocalTracks().filter { track ->
            track.title.contains(query, true) || track.artist.contains(query, true)
        })
    }
}