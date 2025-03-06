package com.example.lorempicsum.ui.composables.filterSort

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListState

@Composable
fun FilterSortHolder(
    state: ImageListState,
    filterImagesByAuthor: (String?) -> Unit,
    sortImages: (SortType) -> Unit
) {
    AnimatedVisibility(
        visible = state.isFilterSelected,
        enter = fadeIn() + expandVertically(),
    ) {
        DropdownMenuFilterRow(
            selectedAuthor = state.selectedAuthor,
            names = state.authors,
            onFilterChanged = {
                filterImagesByAuthor(it)
            }
        )
    }

    AnimatedVisibility(
        visible = state.isSortSelected,
        enter = fadeIn() + expandVertically(),
    ) {
        SortSelectionChipRow(
            state = state,
            updateSortType = {
                sortImages(it)
            }
        )
    }
}