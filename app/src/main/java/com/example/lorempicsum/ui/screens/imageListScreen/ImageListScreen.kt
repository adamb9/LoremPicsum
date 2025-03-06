package com.example.lorempicsum.ui.screens.imageListScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lorempicsum.ui.composables.DropdownMenuFilterRow
import com.example.lorempicsum.ui.composables.ImageList
import com.example.lorempicsum.ui.composables.error.ErrorMessageHolder
import com.example.lorempicsum.ui.composables.loading.LoadingIconHolder

@Composable
fun ImageListScreen(
    viewModel: ImageListViewModel
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.loadImagesFromAPI()
    }

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
            DropdownMenuFilterRow(state.authors)
            HorizontalDivider(color = Color.LightGray, thickness = 2.dp)

            ImageList(state.images)
        }
    }

}