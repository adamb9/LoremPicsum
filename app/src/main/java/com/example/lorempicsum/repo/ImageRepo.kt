package com.example.lorempicsum.repo

import com.example.lorempicsum.entity.ImageEntity

interface ImageRepo {

    suspend fun getImageList(page: Int, limit: Int): List<ImageEntity>

}