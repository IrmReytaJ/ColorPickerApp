package com.example.colorpickerapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ColorDao {
    @Insert
    suspend fun insertColor(color: ColorEntity)

    @Query("SELECT * FROM colors ORDER BY _id DESC")
    suspend fun getAllColors(): List<ColorEntity>
}
