package com.example.lorempicsum.ui.composables.filterSort

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListState

@Composable
fun SortSelectionChipRow(
    state: ImageListState,
    updateSortType: (SortType) -> Unit
) {
    val currentSortType = state.sortType

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SortSelectionChip(
            updateSortType = { updateSortType(SortType.ID_ASCENDING) },
            currentSortType = currentSortType,
            selectedSortType = SortType.ID_ASCENDING
        )
        SortSelectionChip(
            updateSortType = { updateSortType(SortType.ID_DESCENDING) },
            currentSortType = currentSortType,
            selectedSortType = SortType.ID_DESCENDING
        )
        SortSelectionChip(
            updateSortType = { updateSortType(SortType.AUTHOR_ASCENDING) },
            currentSortType = currentSortType,
            selectedSortType = SortType.AUTHOR_ASCENDING
        )
        SortSelectionChip(
            updateSortType = { updateSortType(SortType.AUTHOR_DESCENDING) },
            currentSortType = currentSortType,
            selectedSortType = SortType.AUTHOR_DESCENDING
        )
    }
}

@Composable
fun SortSelectionChip(
    updateSortType: (SortType) -> Unit,
    currentSortType: SortType,
    selectedSortType: SortType,
) {
    Column(
        modifier = Modifier.padding(5.dp, 0.dp)
    ) {
        FilterChip(
            onClick = { updateSortType(selectedSortType) },
            label = {
                Text(selectedSortType.displayString)
            },
            selected = currentSortType == selectedSortType,
            leadingIcon = if (currentSortType == selectedSortType) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                    )
                }
            } else {
                null
            },
        )
    }
}