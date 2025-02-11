package com.example.boockshelf.di

import com.example.boockshelf.domain.repository.BooksRepository
import com.example.boockshelf.presentation.DetailViewModel
import com.example.boockshelf.presentation.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainViewModel(booksRepository: BooksRepository): MainViewModel {
        return MainViewModel(booksRepository)
    }

    @Provides
    fun provideDetailViewModel(booksRepository: BooksRepository): DetailViewModel {
        return DetailViewModel(booksRepository)
    }
}

