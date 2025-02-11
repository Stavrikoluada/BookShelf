package com.example.boockshelf.data.storage.model

data class BookModel(
    val title: String?,
    val previewLink: String?,
    val imageLink: String?,
    //val authors: ArrayList<String>,
    val pageCount: Int?,
    val description: String?,
    val id: String?,
)
