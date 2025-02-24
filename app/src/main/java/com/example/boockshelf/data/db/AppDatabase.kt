package com.example.boockshelf.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.boockshelf.di.BooksApplication

@Database(entities = [BookEntity::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        private const val DATABASE_NAME = "book_database"

        val instance: AppDatabase by lazy {
            Room.databaseBuilder(
                BooksApplication.appContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}