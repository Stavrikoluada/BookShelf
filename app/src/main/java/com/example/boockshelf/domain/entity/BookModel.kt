package com.example.boockshelf.domain.entity

data class BookModel(
    val title: String?,
    val previewLink: String?,
    val imageLink: String?,
    val authors: ArrayList<String>?,
    val pageCount: Int?,
    val description: String?,
    val id: Int?,
    val isSavedInDatabase: Boolean,
)
