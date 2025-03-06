package com.example.lorempicsum.repo

import com.example.lorempicsum.entity.ImageEntity

interface ImageRepo {

    suspend fun getImageListOnline(page: Int, limit: Int): List<ImageEntity>

    suspend fun getImageListOffline(): List<ImageEntity>

}