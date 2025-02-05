package com.example.boockshelf.presentation.di

import com.example.boockshelf.data.remote.network.api.BookApi
import com.example.boockshelf.data.repository.BooksRepository
import com.example.boockshelf.data.repository.NetworkBooksRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideBookRepository(bookApi: BookApi): BooksRepository {
        return NetworkBooksRepository(bookApi)
    }
}