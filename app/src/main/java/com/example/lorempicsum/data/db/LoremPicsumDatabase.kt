package com.example.lorempicsum.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lorempicsum.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class LoremPicsumDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}