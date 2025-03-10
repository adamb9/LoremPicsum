package com.example.lorempicsum.ui.composables.filterSort

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListState
import org.junit.Rule
import org.junit.Test

class FilterSortHolderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun filterSortHolder_displaysDropdownMenuFilterRow_whenIsFilterSelected() {
        val state =
            ImageListState(isFilterSelected = true, authors = listOf("Author 1", "Author 2"))
        composeTestRule.setContent {
            FilterSortHolder(state = state, filterImagesByAuthor = {}, sortImages = {})
        }

        composeTestRule.onNodeWithTag("FilterRow").assertIsDisplayed()
    }

    @Test
    fun filterSortHolder_doesNotDisplayDropdownMenuFilterRow_whenIsNotFilterSelected() {
        val state =
            ImageListState(isFilterSelected = false, authors = listOf("Author 1", "Author 2"))
        composeTestRule.setContent {
            FilterSortHolder(state = state, filterImagesByAuthor = {}, sortImages = {})
        }

        composeTestRule.onNodeWithText("Filter").assertIsNotDisplayed()
    }

    @Test
    fun filterSortHolder_displaysSortSelectionChipRow_whenIsSortSelected() {
        val state = ImageListState(isSortSelected = true)
        composeTestRule.setContent {
            FilterSortHolder(state = state, filterImagesByAuthor = {}, sortImages = {})
        }

        composeTestRule.onNodeWithText(SortType.ID_ASCENDING.displayString).assertIsDisplayed()
    }

    @Test
    fun filterSortHolder_doesNotDisplaySortSelectionChipRow_whenIsNotSortSelected() {
        val state = ImageListState(isSortSelected = false)
        composeTestRule.setContent {
            FilterSortHolder(state = state, filterImagesByAuthor = {}, sortImages = {})
        }

        composeTestRule.onNodeWithText(SortType.ID_ASCENDING.displayString).assertIsNotDisplayed()
    }

    @Test
    fun filterSortHolder_callsFilterImagesByAuthor_whenDropdownMenuFilterRowChanges() {
        var filteredAuthor: String? = null
        val state =
            ImageListState(isFilterSelected = true, authors = listOf("Author 1", "Author 2"))
        composeTestRule.setContent {
            FilterSortHolder(
                state = state,
                filterImagesByAuthor = { filteredAuthor = it },
                sortImages = {}
            )
        }
        composeTestRule.onNodeWithText("Author 1").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("DropdownIcon").performClick()
        composeTestRule.onNodeWithText("Author 1").performClick()

        assert(filteredAuthor == "Author 1")
    }

    @Test
    fun filterSortHolder_callsSortImages_whenSortSelectionChipRowChanges() {
        var sortedBy: SortType? = null
        val state = ImageListState(isSortSelected = true)
        composeTestRule.setContent {
            FilterSortHolder(
                state = state,
                filterImagesByAuthor = {},
                sortImages = { sortedBy = it }
            )
        }

        composeTestRule.onNodeWithText(SortType.ID_ASCENDING.displayString).performClick()
        assert(sortedBy == SortType.ID_ASCENDING)
    }
}