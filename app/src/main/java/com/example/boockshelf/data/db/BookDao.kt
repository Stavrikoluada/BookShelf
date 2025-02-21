package com.example.boockshelf.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM favorites_book")
    suspend fun getFavoritesBooks(): List<BookEntity>

    @Query("SELECT * FROM favorites_book WHERE id = :id")
    suspend fun getBookForId(id: String): BookEntity

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertToFavoritesBooks(movies: BookEntity)

    @Query("DELETE FROM favorites_book WHERE id = :id")
    suspend fun deleteBookById(id: String)
}
