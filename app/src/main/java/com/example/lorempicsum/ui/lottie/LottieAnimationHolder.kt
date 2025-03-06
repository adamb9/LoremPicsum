package com.example.lorempicsum.ui.lottie

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.lorempicsum.R

@Composable
fun LottieAnimationHolder(type: LottieType) {
    val file: LottieCompositionResult = when (type) {
        LottieType.ERROR -> rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_anim))
        LottieType.LOADING -> rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_anim))
    }
    LottieAnimation(
        composition = file.value,
        modifier = Modifier.fillMaxSize()
    )
}