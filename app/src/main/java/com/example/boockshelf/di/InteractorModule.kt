package com.example.boockshelf.di

import com.example.boockshelf.domain.interactor.BooksInteractor
import com.example.boockshelf.domain.interactor.impl.BooksInteractorImpl
import com.example.boockshelf.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun provideBookInteractor(bookRepository: BooksRepository): BooksInteractor {
        return BooksInteractorImpl(bookRepository)
    }
}