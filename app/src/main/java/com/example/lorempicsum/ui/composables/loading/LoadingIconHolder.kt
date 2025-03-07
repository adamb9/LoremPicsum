package com.example.lorempicsum.ui.composables.loading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.lorempicsum.ui.lottie.LottieAnimationHolder
import com.example.lorempicsum.ui.lottie.LottieType
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListState

@Composable
fun LoadingIconHolder(
    state: ImageListState
) {
    AnimatedVisibility(
        visible = state.isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag("Loading"),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimationHolder(LottieType.LOADING)
        }
    }
}