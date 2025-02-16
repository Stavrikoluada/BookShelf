package com.example.boockshelf.data.repository

import androidx.room.ColumnInfo
import com.example.boockshelf.data.db.AppDatabase
import com.example.boockshelf.data.db.BookDao
import com.example.boockshelf.data.db.BookEntity
import com.example.boockshelf.data.remote.network.api.BookApi
import com.example.boockshelf.domain.model.BookModel
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
                //authors = ArrayList<String>(bookDetailVolumeInfo.authors),
                pageCount = items.volumeInfo?.pageCount,
                description = items.volumeInfo?.description,
                id = items.id
            )
        }
    }

    override suspend fun saveFavoritesBookToDatabase(book: BookModel) {
        db.bookDao().insertToFavoritesBooks(
            BookEntity(
                title = book.title,
                previewLink = book.previewLink,
                imageLink = book.imageLink,
                pageCount = book.pageCount,
                description = book.description,
                id = book.id
            )
        )
    }

    override suspend fun getFavoritesBookFromDatabase(): List<BookModel> {
        return db.bookDao().getFavoritesBooks().map { items ->
            BookModel(
                title = items.title,
                previewLink = items.previewLink,
                imageLink = items.imageLink,
                pageCount = items.pageCount,
                description = items.description,
                id = items.id
            )
        }
    }
}