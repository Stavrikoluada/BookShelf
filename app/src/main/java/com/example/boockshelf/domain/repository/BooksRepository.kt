package com.example.boockshelf.domain.repository

import com.example.boockshelf.data.remote.network.api.BookApi
import com.example.boockshelf.data.storage.model.Book
import com.example.boockshelf.data.storage.model.DetailBook

interface BooksRepository {
    suspend fun getRepositoryBook(query: String, maxResults: Int): List<Book>
    suspend fun getRepositoryDetailBook(query: String, maxResults: Int): List<DetailBook>
}

class NetworkBooksRepository(
    private val booksApi: BookApi
): BooksRepository {

    override suspend fun getRepositoryBook(query: String, maxResults: Int): List<Book> {
        return booksApi.bookSearch(query, maxResults).items.map { items ->
            Book(
                title = items.volumeInfo?.title,
                previewLink = items.volumeInfo?.previewLink,
                imageLink = items.volumeInfo?.imageLinks?.thumbnail
            )
        }
    }

    override suspend fun getRepositoryDetailBook(query: String, maxResults: Int): List<DetailBook> {
        return booksApi.bookSearch(query, maxResults).items.map { items ->
            DetailBook(
                title = items.volumeInfo?.title,
                previewLink = items.volumeInfo?.previewLink,
                imageLink = items.volumeInfo?.imageLinks?.thumbnail,
                //authors = ArrayList<String>(bookDetailVolumeInfo.authors),
                pageCount = items.volumeInfo?.pageCount,
                description = items.volumeInfo?.description
            )
        }
    }
}
