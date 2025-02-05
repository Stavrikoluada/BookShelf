package com.example.boockshelf.presentation.di

import com.example.boockshelf.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}


