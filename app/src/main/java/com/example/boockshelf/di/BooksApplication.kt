package com.example.boockshelf.di

import android.app.Application
import android.content.Context

class BooksApplication: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}

