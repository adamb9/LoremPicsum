package com.example.lorempicsum.ui.composables.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.lorempicsum.R
import com.example.lorempicsum.ui.lottie.LottieAnimationHolder
import com.example.lorempicsum.ui.lottie.LottieType

@Composable
fun ErrorMessageContent(
    mainText: String,
    subText: String,
    onRetryClicked: () -> Unit,
    showOfflineOption: Boolean = false,
    onEnableOfflineModeClicked: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .testTag("ErrorMessageContent"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.weight(0.65F)
        ) {
            LottieAnimationHolder(LottieType.ERROR)
        }
        Column(
            modifier = Modifier.weight(0.35F),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.testTag("MainText"),
                text = mainText,
                fontSize = TextUnit(24F, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.testTag("SubText"),
                text = subText,
                fontSize = TextUnit(18F, TextUnitType.Sp),
                fontWeight = FontWeight.Light,
            )
            Spacer(Modifier.height(30.dp))
            FilledTonalButton(
                modifier = Modifier.testTag("RetryButton"),
                onClick = onRetryClicked,
                content = {
                    Text(stringResource(R.string.retry))
                }
            )
            if (showOfflineOption) {
                Spacer(Modifier.height(5.dp))
                FilledTonalButton(
                    modifier = Modifier.testTag("OfflineButton"),
                    onClick = onEnableOfflineModeClicked,
                    content = {
                        Text(stringResource(R.string.offline_mode))
                    }
                )
            }
        }

    }
}