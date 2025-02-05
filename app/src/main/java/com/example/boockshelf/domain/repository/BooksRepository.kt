package com.example.boockshelf.domain.repository

import com.example.boockshelf.data.remote.network.api.BookApi
import com.example.boockshelf.data.storage.model.Book

interface BooksRepository {
    suspend fun getRepository(query: String, maxResults: Int): List<Book>
}

class NetworkBooksRepository(
    private val booksApi: BookApi
): BooksRepository {

    override suspend fun getRepository(query: String, maxResults: Int): List<Book> {
        return booksApi.bookSearch(query, maxResults).items.map { items ->
            Book(
                title = items.volumeInfo?.title,
                previewLink = items.volumeInfo?.previewLink,
                imageLink = items.volumeInfo?.imageLinks?.thumbnail
            )
        }
    }

}