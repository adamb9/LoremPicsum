package com.example.lorempicsum.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lorempicsum.data.api.ImageResponse

@Entity
data class ImageEntity(
    @PrimaryKey val id: Int,
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