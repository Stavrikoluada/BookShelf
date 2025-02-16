package com.example.boockshelf.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.boockshelf.di.BooksApplication

@Database(entities = [BookEntity::class], version = 1)
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

//        val instance: AppDatabase by lazy {
//            INSTANCE ?: synchronized(this) {
//                val context = (ApplicationContextProvider.context)
//                val db = Room.databaseBuilder(
//                    context,
//                    AppDatabase::class.java,
//                    DATABASE_NAME
//                )
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = db
//                db
//            }
//        }
//    }
//}
//        val instance: AppDatabase by lazy {
//            Room.databaseBuilder(
//                (ApplicationProvider.getApplicationContext() as BooksApplication).context,
//                AppDatabase::class.java,
//                DATABASE_NAME
//            ).allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//    }
//}
//fun getDatabase(context: Context): AppDatabase {
//    return INSTANCE ?: synchronized(this) {
//        val instance = Room.databaseBuilder(
//            context.applicationContext,
//            AppDatabase::class.java,
//            DATABASE_NAME
//        ).build()
//        INSTANCE = instance
//        instance
//    }
//}