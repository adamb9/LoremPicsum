package com.example.lorempicsum.ui.screens.imageListScreen

import com.example.lorempicsum.entity.ImageEntity
import com.example.lorempicsum.ui.composables.filterSort.SortType

data class ImageListState(
    val isLoading: Boolean = true,
    val isApiError: Boolean = false,
    val noDataAvailable: Boolean = false,
    val offlineModeEnabled: Boolean = false,

    val images: List<ImageEntity> = emptyList(),
    val filteredImages: List<ImageEntity>? = null,
    val authors: List<String> = emptyList(),
    val selectedAuthor: String? = null,

    val isFilterSelected: Boolean = true,
    val isSortSelected: Boolean = false,
    val sortType: SortType = SortType.ID_ASCENDING
)