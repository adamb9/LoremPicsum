package com.example.lorempicsum.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lorempicsum.data.db.ImageDao
import com.example.lorempicsum.data.db.LoremPicsumDatabase
import com.example.lorempicsum.entity.ImageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ImageDaoTest {

    private lateinit var imageDao: ImageDao
    private lateinit var db: LoremPicsumDatabase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun createDb() {
        Dispatchers.setMain(testDispatcher)
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, LoremPicsumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        imageDao = db.imageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
        Dispatchers.resetMain()
    }

    @Test
    @Throws(Exception::class)
    fun getAllImages_imagesInDB() = runTest(testDispatcher) {
        val image1 = ImageEntity(
            0,
            "Alejandro Escamilla",
            5616,
            3744,
            "https://unsplash.com/photos/yC-Yzbqy7PY",
            "https://picsum.photos/id/0/5616/3744"
        )
        val image2 = ImageEntity(1, "Author 2", 100, 200, "url2", "download_url2")
        val images = listOf(image1, image2)

        imageDao.upsertAll(images)
        val retrievedImages = imageDao.getAll()

        assertEquals(2, retrievedImages.size)
        assertEquals(image1, retrievedImages[0])
        assertEquals(image2, retrievedImages[1])
    }

    @Test
    @Throws(Exception::class)
    fun getAllImages_emptyDB() = runTest(testDispatcher) {
        val retrievedImages = imageDao.getAll()

        assertEquals(0, retrievedImages.size)
    }

    @Test
    @Throws(Exception::class)
    fun upsertAll_updatesExistingData() = runTest(testDispatcher) {
        val image1 = ImageEntity(
            0,
            "Alejandro Escamilla",
            5616,
            3744,
            "https://unsplash.com/photos/yC-Yzbqy7PY",
            "https://picsum.photos/id/0/5616/3744"
        )
        val image2 = ImageEntity(1, "Author 2", 100, 200, "url2", "download_url2")
        val images = listOf(image1, image2)
        imageDao.upsertAll(images)

        val updatedImage1 =
            ImageEntity(0, "Updated Author", 100, 100, "updated_url", "updated_download_url")
        val updatedImages = listOf(updatedImage1)
        imageDao.upsertAll(updatedImages)
        val retrievedImages = imageDao.getAll()

        assertEquals(2, retrievedImages.size)
        assertEquals(updatedImage1, retrievedImages[0])
    }

}