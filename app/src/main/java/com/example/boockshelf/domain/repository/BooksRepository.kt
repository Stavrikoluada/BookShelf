package com.example.boockshelf.domain.repository

import com.example.boockshelf.domain.model.BookModel

interface BooksRepository {
    suspend fun getRepositoryBook(query: String, maxResults: Int): List<BookModel>
}
