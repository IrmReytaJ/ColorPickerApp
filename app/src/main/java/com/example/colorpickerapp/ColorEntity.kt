package com.example.colorpickerapp

import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.room.ColumnInfo

@Entity(tableName = "colors")
data class ColorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int = 0,

    @ColumnInfo(name = "hex_color")
    val hex: String,

    val name: String,

    val red: Int,
    val green: Int,
    val blue: Int
)
