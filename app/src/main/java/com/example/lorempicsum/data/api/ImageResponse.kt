package com.example.lorempicsum.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerialName("download_url") val downloadUrl: String
)