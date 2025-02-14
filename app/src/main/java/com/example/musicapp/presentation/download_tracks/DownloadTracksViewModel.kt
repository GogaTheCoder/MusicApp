package com.example.musicapp.presentation.download_tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.model.Track
import com.example.musicapp.domain.repository.TracksRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DownloadTracksViewModel @Inject constructor(
    private val repository: TracksRepository
) : ViewModel() {

    private val _tracks = MutableLiveData<List<Track>?>()
    val tracks: LiveData<List<Track>?> get() = _tracks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        downloadTracks()
    }

    fun downloadTracks() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val downloadedTracks = repository.getDownloadTracks()
                _tracks.value = downloadedTracks
            } catch (e: Exception) {
                // Обработка ошибок, например, логирование
                _tracks.value = emptyList() // Или можно установить сообщение об ошибке
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchTracks(query: String) {
        // Фильтрация треков по запросу
        val filteredTracks = _tracks.value?.filter { track ->
            track.title.contains(query, true) || track.artist.contains(query, true)
        }
        _tracks.value = filteredTracks
    }
}