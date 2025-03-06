package com.example.lorempicsum.ui.screens.imageListScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lorempicsum.ui.composables.CustomTopBar
import com.example.lorempicsum.ui.composables.ImageList
import com.example.lorempicsum.ui.composables.error.ErrorMessageHolder
import com.example.lorempicsum.ui.composables.filterSort.FilterSortHolder
import com.example.lorempicsum.ui.composables.loading.LoadingIconHolder

@Composable
fun ImageListScreen(
    viewModel: ImageListViewModel
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.loadImagesFromAPI()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AnimatedVisibility(
                visible = !state.isLoading && !state.isApiError && !state.noDataAvailable,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CustomTopBar(
                    onFilterClick = { viewModel.onFilterClick() },
                    onSortClick = { viewModel.onSortClick() }
                )
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {

            ErrorMessageHolder(
                state = state,
                onRetryClicked = { viewModel.retryLoadingImages() }
            )
            LoadingIconHolder(
                state = state
            )

            AnimatedVisibility(
                visible = !state.isLoading && !state.isApiError && !state.noDataAvailable,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    FilterSortHolder(
                        state = state,
                        filterImagesByAuthor = { viewModel.filterImagesByAuthor(it) },
                        sortImages = { viewModel.sortImages(it) }
                    )

                    HorizontalDivider(color = Color.LightGray, thickness = 2.dp)

                    ImageList(state.filteredImages ?: state.images)
                }
            }

        }
    }

}