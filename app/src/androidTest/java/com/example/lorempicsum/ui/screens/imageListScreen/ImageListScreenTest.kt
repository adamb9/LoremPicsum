package com.example.lorempicsum.ui.screens.imageListScreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.lorempicsum.entity.ImageEntity
import com.example.lorempicsum.ui.composables.filterSort.SortType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * I wanted to add UI tests but ran out of time. The ones below have issues with compose idling.
 * My theory is that the loading and error tests idle due to something involving lottie animations and
 * the Image List loaded test idles due Coil trying to load the images from the internet
 *
 * If I had more time I could investigate more but for now this can give you an idea of what type of tests I wanted to write
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ImageListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var imageListViewModel: ImageListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        imageListViewModel = mockk(relaxed = true)
        coEvery { imageListViewModel.state.value.isLoading } returns false
        coEvery { imageListViewModel.state.value.isApiError } returns false
        coEvery { imageListViewModel.state.value.noDataAvailable } returns false
        coEvery { imageListViewModel.state.value.images } returns emptyList()
        coEvery { imageListViewModel.state.value.filteredImages } returns null
        coEvery { imageListViewModel.state.value.authors } returns emptyList()
        coEvery { imageListViewModel.state.value.selectedAuthor } returns null
        coEvery { imageListViewModel.state.value.isFilterSelected } returns true
        coEvery { imageListViewModel.state.value.isSortSelected } returns false
        coEvery { imageListViewModel.state.value.sortType } returns SortType.ID_ASCENDING
        coEvery { imageListViewModel.state.value.offlineModeEnabled } returns false
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun imageListScreen_whenLoading_showLoadingIndicator() = runTest(testDispatcher) {
        coEvery { imageListViewModel.state.value.isLoading } returns true
        composeTestRule.setContent { ImageListScreen(imageListViewModel) }

        advanceUntilIdle()

        composeTestRule.onNodeWithTag("Loading").assertIsDisplayed()
    }

    @Test
    fun imageListScreen_loadingComplete_showImageList() = runTest(testDispatcher) {
        val imageList = listOf(
            ImageEntity(
                0,
                "Alejandro Escamilla",
                5616,
                3744,
                "https://unsplash.com/photos/yC-Yzbqy7PY",
                "https://picsum.photos/id/0/5616/3744"
            ),
            ImageEntity(1, "Author 2", 100, 200, "url2", "download_url2")
        )
        coEvery { imageListViewModel.state.value.images } returns imageList

        composeTestRule.setContent { ImageListScreen(imageListViewModel) }

        advanceUntilIdle()

        composeTestRule.onNodeWithTag("ImageList").assertIsDisplayed()
        composeTestRule.onNodeWithText("Alejandro Escamilla").assertIsDisplayed()
        composeTestRule.onNodeWithText("Author 2").assertIsDisplayed()
    }

    @Test
    fun imageListScreen_whenErrorOccurs_showErrorMessage() = runTest(testDispatcher) {
        coEvery { imageListViewModel.state.value.isApiError } returns true
        composeTestRule.setContent { ImageListScreen(imageListViewModel) }

        advanceUntilIdle()

        composeTestRule.onNodeWithText("Error").assertIsDisplayed()
    }

}