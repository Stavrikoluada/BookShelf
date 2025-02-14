package com.example.boockshelf.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "previewLink")
    val previewLink: String?,

    @ColumnInfo(name = "imageLink")
    val imageLink: String?,

    @ColumnInfo(name = "pageCount")
    val pageCount: Int?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "id")
    val id: String?
)

