package com.example.lorempicsum.ui.composables.filterSort

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListState
import org.junit.Rule
import org.junit.Test

class SortSelectionChipRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun sortSelectionChipRow_displaysAllSortOptions() {
        composeTestRule.setContent {
            SortSelectionChipRow(state = ImageListState(), updateSortType = {})
        }

        composeTestRule.onNodeWithText(SortType.ID_ASCENDING.displayString).assertIsDisplayed()
        composeTestRule.onNodeWithText(SortType.ID_DESCENDING.displayString).assertIsDisplayed()
        composeTestRule.onNodeWithText(SortType.AUTHOR_ASCENDING.displayString).assertIsDisplayed()
        composeTestRule.onNodeWithText(SortType.AUTHOR_DESCENDING.displayString).assertIsDisplayed()
    }

    @Test
    fun sortSelectionChip_selectsCorrectSortType() {
        var selectedSortType: SortType? = null
        composeTestRule.setContent {
            SortSelectionChipRow(
                state = ImageListState(),
                updateSortType = { selectedSortType = it }
            )
        }

        composeTestRule.onNodeWithText(SortType.ID_ASCENDING.displayString).performClick()
        assert(selectedSortType == SortType.ID_ASCENDING)

        composeTestRule.onNodeWithText(SortType.ID_DESCENDING.displayString).performClick()
        assert(selectedSortType == SortType.ID_DESCENDING)

        composeTestRule.onNodeWithText(SortType.AUTHOR_ASCENDING.displayString).performClick()
        assert(selectedSortType == SortType.AUTHOR_ASCENDING)

        composeTestRule.onNodeWithText(SortType.AUTHOR_DESCENDING.displayString).performClick()
        assert(selectedSortType == SortType.AUTHOR_DESCENDING)
    }

    @Test
    fun sortSelectionChip_displaysDoneIcon_whenSelected() {
        val state = ImageListState(sortType = SortType.ID_ASCENDING)
        composeTestRule.setContent {
            SortSelectionChipRow(state = state, updateSortType = {})
        }

        composeTestRule.onNodeWithContentDescription("Done icon").assertIsDisplayed()
    }

    @Test
    fun sortSelectionChip_isMarkedAsSelected_whenSelected() {
        val state = ImageListState(sortType = SortType.ID_ASCENDING)
        composeTestRule.setContent {
            SortSelectionChipRow(state = state, updateSortType = {})
        }

        composeTestRule.onNodeWithText(SortType.ID_ASCENDING.displayString).assertIsSelected()
    }

    @Test
    fun sortSelectionChip_isMarkedAsNotSelected_whenNotSelected() {
        val state = ImageListState(sortType = SortType.ID_ASCENDING)
        composeTestRule.setContent {
            SortSelectionChipRow(state = state, updateSortType = {})
        }

        composeTestRule.onNodeWithText(SortType.ID_DESCENDING.displayString).assertIsNotSelected()
        composeTestRule.onNodeWithText(SortType.AUTHOR_ASCENDING.displayString)
            .assertIsNotSelected()
        composeTestRule.onNodeWithText(SortType.AUTHOR_DESCENDING.displayString)
            .assertIsNotSelected()
    }
}