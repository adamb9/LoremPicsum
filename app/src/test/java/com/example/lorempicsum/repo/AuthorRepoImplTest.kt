package com.example.lorempicsum.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class AuthorRepoImplTest {

    private lateinit var authorRepo: AuthorRepoImpl
    private lateinit var dataStore: DataStore<Preferences>
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        dataStore = mockk(relaxed = true)
        authorRepo = AuthorRepoImpl(dataStore)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `setSelectedAuthorFilter and getSelectedAuthorFilter should store and retrieve data correctly`() =
        runTest(testDispatcher) {
            val author = "Test Author"
            val preferences =
                mutablePreferencesOf(stringPreferencesKey("selected_author") to author)
            coEvery { dataStore.data } returns flowOf(preferences)

            authorRepo.setSelectedAuthorFilter(author)
            val retrievedAuthor = authorRepo.getSelectedAuthorFilter()

            assertEquals(author, retrievedAuthor)
        }

    @Test
    fun `getSelectedAuthorFilter should return null when no data is set`() =
        runTest(testDispatcher) {
            val preferences = mutablePreferencesOf()
            coEvery { dataStore.data } returns flowOf(preferences)

            val retrievedAuthor = authorRepo.getSelectedAuthorFilter()

            assertNull(retrievedAuthor)
        }
}