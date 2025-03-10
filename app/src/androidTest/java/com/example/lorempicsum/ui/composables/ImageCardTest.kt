package com.example.lorempicsum.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.lorempicsum.entity.ImageEntity
import org.junit.Rule
import org.junit.Test

class ImageCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun imageCard_displaysAuthorName() {
        val image = ImageEntity(
            id = 0,
            author = "Test Author",
            width = 100,
            height = 200,
            url = "test_url",
            downloadUrl = "test_download_url"
        )

        composeTestRule.setContent {
            MaterialTheme {
                ImageCard(image = image)
            }
        }

        composeTestRule.onNodeWithText("Test Author").assertIsDisplayed()
    }

    @Test
    fun imageCard_displaysImage() {
        val image = ImageEntity(
            id = 0,
            author = "Test Author",
            width = 100,
            height = 200,
            url = "test_url",
            downloadUrl = "https://picsum.photos/id/0/5616/3744"
        )

        composeTestRule.setContent {
            MaterialTheme {
                ImageCard(image = image)
            }
        }

        composeTestRule.onNodeWithTag("AsyncImage").assertExists()
    }
}