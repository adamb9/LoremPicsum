package com.example.lorempicsum.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class CustomTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun customTopBar_displaysAppName() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = {},
                    onSortClick = {},
                    onDisableOfflineModeClick = {},
                    showDisableOfflineMode = false
                )
            }
        }

        composeTestRule.onNodeWithText("LoremPicsum").assertIsDisplayed()
    }

    @Test
    fun customTopBar_displaysCameraIcon() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = {},
                    onSortClick = {},
                    onDisableOfflineModeClick = {},
                    showDisableOfflineMode = false
                )
            }
        }

        composeTestRule.onNodeWithTag("CameraIcon").assertIsDisplayed()
    }

    @Test
    fun customTopBar_displaysFilterIcon() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = {},
                    onSortClick = {},
                    onDisableOfflineModeClick = {},
                    showDisableOfflineMode = false
                )
            }
        }

        composeTestRule.onNodeWithTag("FilterIcon").assertIsDisplayed()
    }

    @Test
    fun customTopBar_displaysSortIcon() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = {},
                    onSortClick = {},
                    onDisableOfflineModeClick = {},
                    showDisableOfflineMode = false
                )
            }
        }

        composeTestRule.onNodeWithTag("SortIcon").assertIsDisplayed()
    }

    @Test
    fun customTopBar_displaysRefreshIcon_whenShowDisableOfflineModeIsTrue() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = {},
                    onSortClick = {},
                    onDisableOfflineModeClick = {},
                    showDisableOfflineMode = true
                )
            }
        }

        composeTestRule.onNodeWithTag("RefreshIcon").assertIsDisplayed()
    }

    @Test
    fun customTopBar_doesNotDisplayRefreshIcon_whenShowDisableOfflineModeIsFalse() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = {},
                    onSortClick = {},
                    onDisableOfflineModeClick = {},
                    showDisableOfflineMode = false
                )
            }
        }

        composeTestRule.onNodeWithTag("RefreshIcon").assertDoesNotExist()
    }

    @Test
    fun customTopBar_callsOnFilterClick_whenFilterIconClicked() {
        var filterClicked = false
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = { filterClicked = true },
                    onSortClick = {},
                    onDisableOfflineModeClick = {},
                    showDisableOfflineMode = false
                )
            }
        }

        composeTestRule.onNodeWithTag("FilterIcon").performClick()
        assert(filterClicked)
    }

    @Test
    fun customTopBar_callsOnSortClick_whenSortIconClicked() {
        var sortClicked = false
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = {},
                    onSortClick = { sortClicked = true },
                    onDisableOfflineModeClick = {},
                    showDisableOfflineMode = false
                )
            }
        }

        composeTestRule.onNodeWithTag("SortIcon").performClick()
        assert(sortClicked)
    }

    @Test
    fun customTopBar_callsOnDisableOfflineModeClick_whenRefreshIconClicked() {
        var disableOfflineModeClicked = false
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopBar(
                    onFilterClick = {},
                    onSortClick = {},
                    onDisableOfflineModeClick = { disableOfflineModeClicked = true },
                    showDisableOfflineMode = true
                )
            }
        }

        composeTestRule.onNodeWithTag("RefreshIcon").performClick()
        assert(disableOfflineModeClicked)
    }
}