package com.example.lorempicsum.ui.screens.imageListScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorempicsum.repo.AuthorRepo
import com.example.lorempicsum.repo.ImageRepo
import com.example.lorempicsum.ui.composables.filterSort.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageListViewModel(
    private val imageRepo: ImageRepo,
    private val authorRepo: AuthorRepo
) : ViewModel() {

    private val _state = mutableStateOf(ImageListState())
    val state: State<ImageListState> = _state

    fun loadImages(offlineMode: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(
                isLoading = true
            )
            try {
                val imageList = when (offlineMode) {
                    true -> imageRepo.getImageListOffline()
                    else -> imageRepo.getImageListOnline(1, 30)
                }
                if (imageList.isEmpty()) {
                    _state.value = _state.value.copy(
                        noDataAvailable = true
                    )
                } else {
                    _state.value = _state.value.copy(
                        images = imageList,
                        authors = imageList.map { it.author }.distinct(),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = _state.value.copy(
                    isLoading = false,
                    isApiError = true
                )
            }
            restoreLastSavedAuthorFilter()
        }
    }

    fun retryLoadingImages() {
        resetState()
        loadImages()
    }

    fun filterImagesByAuthor(author: String?) {
        val filteredImages = if (author == null) {
            null
        } else {
            state.value.images.filter { it.author == author }
        }
        _state.value = _state.value.copy(
            selectedAuthor = author,
            filteredImages = filteredImages
        )
        viewModelScope.launch(Dispatchers.IO) {
            authorRepo.setSelectedAuthorFilter(author)
        }
    }

    fun sortImages(sortType: SortType) {
        val sortedImages = when (sortType) {
            SortType.ID_ASCENDING -> state.value.images.sortedBy { it.id }
            SortType.ID_DESCENDING -> state.value.images.sortedByDescending { it.id }
            SortType.AUTHOR_ASCENDING -> state.value.images.sortedBy { it.author }
            SortType.AUTHOR_DESCENDING -> state.value.images.sortedByDescending { it.author }
        }
        _state.value = _state.value.copy(
            sortType = sortType,
            images = sortedImages
        )
        if (state.value.filteredImages != null) {
            val sortedFilteredImages = when (sortType) {
                SortType.ID_ASCENDING -> state.value.filteredImages?.sortedBy { it.id }
                SortType.ID_DESCENDING -> state.value.filteredImages?.sortedByDescending { it.id }
                else -> state.value.filteredImages
            }
            _state.value = _state.value.copy(
                filteredImages = sortedFilteredImages
            )
        }
    }

    fun onFilterClick() {
        _state.value = _state.value.copy(
            isFilterSelected = !_state.value.isFilterSelected,
        )
    }

    fun onSortClick() {
        _state.value = _state.value.copy(
            isSortSelected = !_state.value.isSortSelected,
        )
    }

    fun enableOfflineMode() {
        _state.value = _state.value.copy(
            offlineModeEnabled = true,
            isApiError = false,
            noDataAvailable = false
        )
        loadImages(true)
    }

    private fun restoreLastSavedAuthorFilter() {
        viewModelScope.launch(Dispatchers.IO) {
            val author = authorRepo.getSelectedAuthorFilter()
            filterImagesByAuthor(author)
        }
    }

    private fun resetState() {
        _state.value = _state.value.copy(
            isLoading = true,
            isApiError = false,
            noDataAvailable = false,
            images = emptyList(),
            authors = emptyList()
        )
    }

}