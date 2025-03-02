package com.example.boockshelf.di

import com.example.boockshelf.domain.interactor.BooksInteractor
import com.example.boockshelf.presentation.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainViewModel(booksInteractor: BooksInteractor): MainViewModel {
        return MainViewModel(booksInteractor)
    }
}