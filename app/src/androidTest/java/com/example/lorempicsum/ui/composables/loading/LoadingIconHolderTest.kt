package com.example.lorempicsum.ui.composables.loading

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListState
import org.junit.Rule
import org.junit.Test

class LoadingIconHolderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingIconHolder_displaysLoadingIcon_whenIsLoading() {
        val state = ImageListState(isLoading = true)
        composeTestRule.setContent {
            LoadingIconHolder(state = state)
        }

        composeTestRule.onNodeWithTag("LoadingAnimation").assertIsDisplayed()
    }

    @Test
    fun loadingIconHolder_doesNotDisplayLoadingIcon_whenIsNotLoading() {
        val state = ImageListState(isLoading = false)
        composeTestRule.setContent {
            LoadingIconHolder(state = state)
        }

        composeTestRule.onNodeWithTag("LoadingAnimation").assertIsNotDisplayed()
    }
}