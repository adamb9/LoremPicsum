package com.example.lorempicsum.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class AuthorRepoImpl(private val dataStore: DataStore<Preferences>) : AuthorRepo {

    override suspend fun getSelectedAuthorFilter(): String? {
        val dataStoreKey = stringPreferencesKey("selected_author")
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    override suspend fun setSelectedAuthorFilter(author: String?) {
        val dataStoreKey = stringPreferencesKey("selected_author")
        if (author == null) {
            dataStore.edit { settings ->
                settings.remove(dataStoreKey)
            }
        } else {
            dataStore.edit { settings ->
                settings[dataStoreKey] = author
            }
        }
    }

}