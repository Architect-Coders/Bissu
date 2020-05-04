package com.architectcoders.framework.database.entitiesDao

import androidx.room.*
import com.architectcoders.framework.database.entities.Book

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveBook(book: Book)

    @Update
    fun update(book: Book)

    @Query("DELETE FROM Book")
    fun deleteAllBooks()
}