package com.example.boockshelf.presentation.di

import android.app.Application
import com.example.boockshelf.data.remote.network.api.AppContainer
import com.example.boockshelf.data.remote.network.api.DefaultAppContainer

class BooksApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}