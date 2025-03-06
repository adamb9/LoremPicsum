package com.example.lorempicsum.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.lorempicsum.entity.ImageEntity

@Dao
interface ImageDao {

    @Query("SELECT * FROM ImageEntity")
    suspend fun getAll(): List<ImageEntity>

    @Upsert
    suspend fun upsertAll(images: List<ImageEntity>)

}