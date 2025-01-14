package com.app.shopping.ui.views

sealed class UiState<out T> {
    data class  Success<out T>(val data: T) : UiState<T>()
    data class  Error<out T>(val message: String) : UiState<Nothing>()
    data object Loading: UiState<Nothing>()
}