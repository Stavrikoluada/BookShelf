package com.example.boockshelf.presentation.di

import android.app.Application


class BooksApplication: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

