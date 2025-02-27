package com.example.boockshelf.domain.interactor

import com.example.boockshelf.domain.entity.BookModel

interface BooksInteractor {
    suspend fun getBooks(query: String, maxResults: Int): List<BookModel>
    suspend fun saveBookToFavorites(book: BookModel)
    suspend fun getFavoritesBooks(): List<BookModel>
    suspend fun deleteBookById(id: Int?)
    fun getGenres(): List<String>
}