package com.example.artspaceapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArtViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ArtUiState())
    val uiState: StateFlow<ArtUiState> = _uiState.asStateFlow()

    fun nextArtwork() {
        _uiState.update { currentState ->
            if (currentState.currentArtwork < 3) {
                currentState.copy(currentArtwork = currentState.currentArtwork + 1)
            } else {
                currentState
            }
        }
    }

    fun previousArtwork() {
        _uiState.update { currentState ->
            if (currentState.currentArtwork > 1) {
                currentState.copy(currentArtwork = currentState.currentArtwork - 1)
            } else {
                currentState
            }
        }
    }
}