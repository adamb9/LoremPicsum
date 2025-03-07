package com.example.lorempicsum.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.http.path

class ImageApiServiceImpl(
    private val client: HttpClient
) : ImageApiService {

    override suspend fun getImageList(page: Int, limit: Int): HttpResponse {
        val response: HttpResponse = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "picsum.photos"
                path("v2/list")
                parameters.append("page", "$page")
                parameters.append("limit", "$limit")
            }
        }
        return response
    }
}