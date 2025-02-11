package com.example.boockshelf.di

import com.example.boockshelf.data.remote.network.api.BookApi
import com.example.boockshelf.data.repository.BooksRepositoryImpl
import com.example.boockshelf.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideBookRepository(bookApi: BookApi): BooksRepository {
        return BooksRepositoryImpl(bookApi)
    }
}