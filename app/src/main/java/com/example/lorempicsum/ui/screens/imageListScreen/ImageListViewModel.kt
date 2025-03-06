package com.example.lorempicsum.ui.screens.imageListScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorempicsum.data.api.ImageApiServiceImpl
import com.example.lorempicsum.repo.ImageRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageListViewModel(

) : ViewModel() {

    private val _state = mutableStateOf(ImageListState())
    val state: State<ImageListState> = _state

    val apiService = ImageApiServiceImpl()
    val repo = ImageRepoImpl(apiService)

    fun loadImagesFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(
                isLoading = true
            )
            try {
                val imageList = repo.getImageList(1, 30)
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