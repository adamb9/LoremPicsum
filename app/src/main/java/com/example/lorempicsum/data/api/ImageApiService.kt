package com.example.lorempicsum.data.api

import io.ktor.client.statement.HttpResponse

interface ImageApiService {

    suspend fun getImageList(
        page: Int,
        limit: Int
    ): HttpResponse

}