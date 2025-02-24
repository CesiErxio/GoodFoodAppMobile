package com.example.goodfood.presentation.login

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
}