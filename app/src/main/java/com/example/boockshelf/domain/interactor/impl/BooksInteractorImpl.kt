package com.example.boockshelf.domain.interactor.impl

import com.example.boockshelf.domain.entity.BookModel
import com.example.boockshelf.domain.interactor.BooksInteractor
import com.example.boockshelf.domain.repository.BooksRepository

class BooksInteractorImpl(private val booksRepository: BooksRepository) : BooksInteractor {
    override suspend fun getBooks(query: String, maxResults: Int): List<BookModel> {
        return booksRepository.getRepositoryBook(query, maxResults)
    }

    override suspend fun saveBookToFavorites(book: BookModel) {
        booksRepository.saveFavoritesBookToDatabase(book)
    }

    override suspend fun getFavoritesBooks(): List<BookModel> {
        return booksRepository.getFavoritesBookFromDatabase()
    }

    override suspend fun deleteBookById(id: Int?) {
        booksRepository.deleteBookById(id)
    }

    override fun getGenres(): List<String> {
        return booksRepository.getGenresBook()
    }
}