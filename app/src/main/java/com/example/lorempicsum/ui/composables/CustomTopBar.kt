package com.example.lorempicsum.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SortByAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.lorempicsum.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    onFilterClick: () -> Unit,
    onSortClick: () -> Unit,
    onDisableOfflineModeClick: () -> Unit,
    showDisableOfflineMode: Boolean
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.CameraEnhance,
                    contentDescription = stringResource(R.string.top_bar_icon_label),
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = TextUnit(20F, TextUnitType.Sp),
                    fontWeight = FontWeight.SemiBold,
                )
            }
        },
        actions = {
            val colors = IconButtonColors(
                IconButtonDefaults.iconButtonColors().containerColor,
                MaterialTheme.colorScheme.onPrimary,
                IconButtonDefaults.iconButtonColors().disabledContainerColor,
                IconButtonDefaults.iconButtonColors().disabledContentColor
            )

            if (showDisableOfflineMode) {
                IconButton(
                    onClick = { onDisableOfflineModeClick() },
                    colors = colors
                ) {
                    Icon(Icons.Outlined.Refresh, stringResource(R.string.refresh_icon_label))
                }
            }

            IconButton(
                onClick = { onFilterClick() },
                colors = colors
            ) {
                Icon(Icons.Outlined.FilterAlt, stringResource(R.string.filter_icon_label))
            }
            IconButton(
                onClick = { onSortClick() },
                colors = colors
            ) {
                Icon(Icons.Outlined.SortByAlpha, stringResource(R.string.sort_icon_label))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}