package com.example.boockshelf.data.repository

import com.example.boockshelf.data.db.AppDatabase
import com.example.boockshelf.data.db.BookEntity
import com.example.boockshelf.data.remote.network.api.BookApi
import com.example.boockshelf.data.storage.GenresList
import com.example.boockshelf.domain.entity.BookModel
import com.example.boockshelf.domain.repository.BooksRepository

class BooksRepositoryImpl(
    private val booksApi: BookApi
) : BooksRepository {

    private val db = AppDatabase.instance

    override suspend fun getRepositoryBook(query: String, maxResults: Int): List<BookModel> {
        return booksApi.bookSearch(query, maxResults).items.map { items ->
            BookModel(
                title = items.volumeInfo?.title,
                previewLink = items.volumeInfo?.previewLink,
                imageLink = items.volumeInfo?.imageLinks?.thumbnail,
                authors = items.volumeInfo?.authors,
                pageCount = items.volumeInfo?.pageCount,
                description = items.volumeInfo?.description,
                id = items.id?.toByteArray()?.fold(0) { acc, byte ->
                    acc + (byte.toInt() and 0xFF)
                },
                isSavedInDatabase = false
            )
        }
    }

    override suspend fun saveFavoritesBookToDatabase(book: BookModel) {
        db.bookDao().insertToFavoritesBooks(
            BookEntity(
                title = book.title,
                previewLink = book.previewLink,
                imageLink = book.imageLink,
                authors = book.authors?.joinToString(separator = ","),
                pageCount = book.pageCount,
                description = book.description,
                id = book.id,
                isSavedInDatabase = true
            )
        )
    }

    override suspend fun getFavoritesBookFromDatabase(): List<BookModel> {
        return db.bookDao().getFavoritesBooks().map { items ->
            BookModel(
                title = items.title,
                previewLink = items.previewLink,
                imageLink = items.imageLink,
                authors = items.authors?.split(",")?.let { ArrayList(it) },
                pageCount = items.pageCount,
                description = items.description,
                id = items.id,
                isSavedInDatabase = items.isSavedInDatabase
            )
        }.reversed()
    }

    override suspend fun deleteBookById(id: Int?) {
        if (id != null) {
            db.bookDao().deleteBookById(id)
        }
    }

    override fun getGenresBook(): List<String> {
        return GenresList.genresList
    }
}