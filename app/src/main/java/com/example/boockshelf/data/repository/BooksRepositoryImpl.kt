package com.example.boockshelf.data.repository

import com.example.boockshelf.data.remote.network.api.BookApi
import com.example.boockshelf.data.storage.model.BookModel
import com.example.boockshelf.domain.repository.BooksRepository


class BooksRepositoryImpl(
    private val booksApi: BookApi
): BooksRepository {

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
}