package com.example.boockshelf.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.boockshelf.data.remote.network.api.BookApi
import com.example.boockshelf.data.repository.BooksRepository
import com.example.boockshelf.data.repository.NetworkBooksRepository
import com.example.boockshelf.presentation.BooksViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    fun provideBookApi(): BookApi {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi::class.java)
    }

    @Provides
    fun provideBooksViewModel(booksRepository: BooksRepository): BooksViewModel {
        return BooksViewModel(booksRepository)
    }

    @Provides
    fun provideBooksRepository(bookApi:BookApi): BooksRepository {
        return NetworkBooksRepository(bookApi)
    }
}

