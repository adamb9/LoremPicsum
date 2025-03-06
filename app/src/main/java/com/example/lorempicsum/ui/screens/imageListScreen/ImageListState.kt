package com.example.lorempicsum.ui.screens.imageListScreen

import com.example.lorempicsum.entity.ImageEntity

data class ImageListState(
    val isLoading: Boolean = false,
    val isApiError: Boolean = false,
    val noDataAvailable: Boolean = false,
    val images: List<ImageEntity> = emptyList(),
    val authors: List<String> = emptyList()
)