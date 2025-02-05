package com.example.boockshelf.presentation.di

import com.example.boockshelf.domain.repository.BooksRepository
import com.example.boockshelf.presentation.BooksViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideBooksViewModel(booksRepository: BooksRepository): BooksViewModel {
        return BooksViewModel(booksRepository)
    }
}

