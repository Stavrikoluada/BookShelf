package com.example.boockshelf.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boockshelf.domain.model.BookModel

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    suspend fun getAllBooks(): List<BookModel>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getBooksForId(id: String): BookModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(movies: List<BookEntity>)
}
