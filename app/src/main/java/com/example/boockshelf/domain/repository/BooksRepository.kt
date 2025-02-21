package com.example.boockshelf.domain.repository

import com.example.boockshelf.domain.entity.BookModel

interface BooksRepository {
    suspend fun getRepositoryBook(query: String, maxResults: Int): List<BookModel>
    suspend fun saveFavoritesBookToDatabase(book: BookModel)
    suspend fun getFavoritesBookFromDatabase(): List<BookModel>
    suspend fun deleteBookById(id: String?)
    fun getGenresBook(): List<String>
}
