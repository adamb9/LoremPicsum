package com.example.lorempicsum.di

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.lorempicsum.data.api.ImageApiService
import com.example.lorempicsum.data.api.ImageApiServiceImpl
import com.example.lorempicsum.data.db.LoremPicsumDatabase
import com.example.lorempicsum.repo.AuthorRepo
import com.example.lorempicsum.repo.AuthorRepoImpl
import com.example.lorempicsum.repo.ImageRepo
import com.example.lorempicsum.repo.ImageRepoImpl
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val USER_PREFERENCES = "user_preferences"

val appModule = module {
    single {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(androidContext(), USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { androidContext().preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            LoremPicsumDatabase::class.java,
            "loremPicsum.db"
        ).build().imageDao()
    }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
    singleOf(::ImageApiServiceImpl).bind<ImageApiService>()
    singleOf(::ImageRepoImpl).bind<ImageRepo>()
    singleOf(::AuthorRepoImpl).bind<AuthorRepo>()
    viewModelOf(::ImageListViewModel)
}