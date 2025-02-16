package com.example.boockshelf.di

import com.example.boockshelf.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RemoteModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}


