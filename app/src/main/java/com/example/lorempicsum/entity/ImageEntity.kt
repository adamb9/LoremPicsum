package com.example.lorempicsum.entity

import com.example.lorempicsum.data.api.ImageResponse

data class ImageEntity(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String
) {

    companion object {
        fun fromResponse(response: ImageResponse) = ImageEntity(
            id = response.id,
            author = response.author,
            width = response.width,
            height = response.height,
            url = response.url,
            downloadUrl = response.downloadUrl
        )
    }

}