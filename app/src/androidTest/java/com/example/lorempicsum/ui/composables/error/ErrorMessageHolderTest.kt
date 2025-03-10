package com.example.lorempicsum.ui.composables.error

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListState
import org.junit.Rule
import org.junit.Test

class ErrorMessageHolderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorMessageHolder_whenNoDataAvailable_showsCorrectErrorMessage() {
        val state = ImageListState(noDataAvailable = true, isApiError = false)

        composeTestRule.setContent {
            ErrorMessageHolder(state = state, onRetryClicked = {}, onEnableOfflineModeClicked = {})
        }

        composeTestRule.onNodeWithTag("ErrorMessageContent").assertIsDisplayed()
        composeTestRule.onNodeWithTag("MainText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SubText").assertIsDisplayed()
        composeTestRule.onNodeWithText("No image data available...").assertIsDisplayed()
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
        composeTestRule.onNodeWithText("Offline Mode").assertDoesNotExist()
    }

    @Test
    fun errorMessageHolder_whenIsApiError_showsCorrectErrorMessage() {
        val state = ImageListState(isApiError = true)

        composeTestRule.setContent {
            ErrorMessageHolder(state = state, onRetryClicked = {}, onEnableOfflineModeClicked = {})
        }

        composeTestRule.onNodeWithTag("ErrorMessageContent").assertIsDisplayed()
        composeTestRule.onNodeWithTag("MainText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SubText").assertIsDisplayed()
        composeTestRule.onNodeWithText("Please check your internet and try again soon...")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
        composeTestRule.onNodeWithText("Offline Mode").assertIsDisplayed()
    }

    @Test
    fun errorMessageHolder_whenNoErrors_showsNothing() {
        val state = ImageListState()

        composeTestRule.setContent {
            ErrorMessageHolder(state = state, onRetryClicked = {}, onEnableOfflineModeClicked = {})
        }

        composeTestRule.onNodeWithTag("ErrorMessageContent").assertDoesNotExist()
    }

    @Test
    fun errorMessageHolder_callsOnRetryClicked_whenRetryButtonClicked() {
        var retryClicked = false
        val state = ImageListState(noDataAvailable = true)

        composeTestRule.setContent {
            ErrorMessageHolder(
                state = state,
                onRetryClicked = { retryClicked = true },
                onEnableOfflineModeClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("RetryButton").performClick()
        assert(retryClicked)
    }

    @Test
    fun errorMessageHolder_callsOnEnableOfflineModeClicked_whenOfflineButtonClicked() {
        var offlineClicked = false
        val state = ImageListState(isApiError = true)

        composeTestRule.setContent {
            ErrorMessageHolder(
                state = state,
                onRetryClicked = {},
                onEnableOfflineModeClicked = { offlineClicked = true }
            )
        }

        composeTestRule.onNodeWithTag("OfflineButton").performClick()
        assert(offlineClicked)
    }
}