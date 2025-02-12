package com.example.musicapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State> : ViewModel() {

    private val _state = MutableStateFlow<State?>(null)
    val state: StateFlow<State?> = _state

    protected fun updateState(newState: State) {
        viewModelScope.launch {
            _state.emit(newState)
        }
    }
}