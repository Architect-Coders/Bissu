package com.architectcoders.bissu.data.database.entities

import androidx.room.*

@Dao
interface BookDao {
    @Query("SELECT * FROM Book")
    fun getBooks(): List<Book>

    @Query("SELECT * FROM Book WHERE Book.id LIKE :id")
    fun getBook(id: String): Book

    @Query("SELECT COUNT() FROM Book")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBooks(books: List<Book>)

    @Update
    fun update(book: Book)

}