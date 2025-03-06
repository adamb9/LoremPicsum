package com.example.lorempicsum.ui.screens.imageListScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorempicsum.repo.AuthorRepo
import com.example.lorempicsum.repo.ImageRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageListViewModel(
    private val imageRepo: ImageRepo,
    private val authorRepo: AuthorRepo
) : ViewModel() {

    private val _state = mutableStateOf(ImageListState())
    val state: State<ImageListState> = _state

    fun loadImagesFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(
                isLoading = true
            )
            try {
                val imageList = imageRepo.getImageList(1, 30)
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
        loadImagesFromAPI()
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

    private fun restoreLastSavedAuthorFilter() {
        viewModelScope.launch(Dispatchers.IO) {
            val author = authorRepo.getSelectedAuthorFilter()
            filterImagesByAuthor(author)
        }
    }

    private fun resetState() {
        _state.value = _state.value.copy(
            isLoading = false,
            isApiError = false,
            noDataAvailable = false,
            images = emptyList(),
            authors = emptyList()
        )
    }

}