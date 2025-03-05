package com.example.lorempicsum.repo

import com.example.lorempicsum.data.api.ImageApiService
import com.example.lorempicsum.data.api.ImageResponse
import com.example.lorempicsum.entity.ImageEntity
import com.example.lorempicsum.exceptions.ExternalAPIException
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlin.coroutines.cancellation.CancellationException

class ImageRepoImpl(
    private val apiService: ImageApiService
) : ImageRepo {

    @Throws(ExternalAPIException::class, CancellationException::class)
    override suspend fun getImageList(page: Int, limit: Int): List<ImageEntity> {
        val response = apiService.getImageList(page, limit)
        val imageList = mutableListOf<ImageEntity>()
        when (response.status) {
            HttpStatusCode.OK -> {
                val imageListResponse: List<ImageResponse> = response.body()
                imageListResponse.forEach { result ->
                    val imageEntity = ImageEntity.fromResponse(result)
                    imageList.add(imageEntity)
                }
            }

            else -> {
                throw ExternalAPIException("Error getting data from picsum.photos: ${response.status.description}")
            }
        }
        return imageList
    }

}