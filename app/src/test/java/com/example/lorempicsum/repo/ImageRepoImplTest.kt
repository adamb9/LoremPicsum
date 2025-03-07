package com.example.lorempicsum.repo

import com.example.lorempicsum.data.api.ImageApiServiceImpl
import com.example.lorempicsum.data.api.ImageResponse
import com.example.lorempicsum.data.db.ImageDao
import com.example.lorempicsum.entity.ImageEntity
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
class ImageRepoImplTest {

    private lateinit var imageRepo: ImageRepoImpl
    private lateinit var imageApiService: ImageApiServiceImpl
    private lateinit var imageDao: ImageDao
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        imageApiService = mockk(relaxed = true)
        imageDao = mockk(relaxed = true)
        imageRepo = ImageRepoImpl(imageApiService, imageDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getImageListOnline should call imageApiService with correct parameters and return a list of images`() =
        runTest(testDispatcher) {
            val imageList = listOf(
                ImageResponse(
                    0,
                    "Alejandro Escamilla",
                    5616,
                    3744,
                    "https://unsplash.com/photos/yC-Yzbqy7PY",
                    "https://picsum.photos/id/0/5616/3744"
                ),
                ImageResponse(1, "Author 2", 100, 200, "url2", "download_url2")
            )
            val mockHttpResponse = mockk<HttpResponse>(relaxed = true)
            coEvery { mockHttpResponse.status } returns HttpStatusCode.OK
            coEvery { mockHttpResponse.body<List<ImageResponse>>() } returns imageList
            coEvery { imageApiService.getImageList(1, 10) } returns mockHttpResponse

            val result = imageRepo.getImageListOnline(1, 10)
            val entities = imageList.map { ImageEntity.fromResponse(it) }

            coVerify { imageApiService.getImageList(1, 10) }
            assertEquals(entities, result)
        }

    @Test
    fun `getImageListOnline should handle errors`() = runTest(testDispatcher) {
        var exception: Exception? = null
        try {
            imageRepo.getImageListOnline(1, 10)
        } catch (e: Exception) {
            exception = e
        }

        coVerify { imageApiService.getImageList(1, 10) }
        assertNotNull(exception)
    }

    @Test
    fun `getImageListOffline should call imageDao getAll`() = runTest(testDispatcher) {
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
        coEvery { imageDao.getAll() } returns imageList

        val result = imageRepo.getImageListOffline()

        coVerify { imageDao.getAll() }
        assertEquals(imageList, result)
    }

    @Test
    fun `getImageListOffline should handle empty database`() = runTest(testDispatcher) {
        coEvery { imageDao.getAll() } returns emptyList()

        val result = imageRepo.getImageListOffline()

        coVerify { imageDao.getAll() }
        assertEquals(emptyList(), result)
    }
}