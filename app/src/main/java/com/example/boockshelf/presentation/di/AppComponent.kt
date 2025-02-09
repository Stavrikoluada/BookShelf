package com.example.boockshelf.presentation.di

import com.example.boockshelf.presentation.MainActivity
import com.example.boockshelf.presentation.DetailActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RemoteModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(detailActivity: DetailActivity)
}


