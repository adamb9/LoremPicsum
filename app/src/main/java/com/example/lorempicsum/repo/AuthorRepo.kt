package com.example.lorempicsum.repo

interface AuthorRepo {

    suspend fun getSelectedAuthorFilter(): String?

    suspend fun setSelectedAuthorFilter(author: String?)

}