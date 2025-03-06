package com.example.lorempicsum.ui.composables.error

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.lorempicsum.R
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListState

@Composable
fun ErrorMessageHolder(
    state: ImageListState,
    onRetryClicked: () -> Unit
) {

    AnimatedVisibility(
        visible = state.noDataAvailable,
        enter = fadeIn() + slideInHorizontally(),
        exit = fadeOut() + slideOutHorizontally()
    ) {
        ErrorMessageContent(
            mainText = stringResource(R.string.error_getting_image_data),
            subText = stringResource(R.string.no_image_data_error),
            onClick = {
                onRetryClicked.invoke()
            }
        )
    }
    AnimatedVisibility(
        visible = state.isApiError,
        enter = fadeIn() + slideInHorizontally(),
        exit = fadeOut() + slideOutHorizontally()
    ) {
        ErrorMessageContent(
            mainText = stringResource(R.string.error_getting_image_data),
            subText = stringResource(R.string.generic_api_error),
            onClick = {
                onRetryClicked.invoke()
            }
        )
    }
}