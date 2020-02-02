package com.architectcoders.bissu.data.database.book

import com.architectcoders.bissu.data.database.LocalDatabase
import com.architectcoders.bissu.data.mappers.toDomainBook
import com.architectcoders.bissu.data.mappers.toRoomBook
import com.architectcoders.data.source.BookLocalDataSource
import com.architectcoders.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2019-12-12.
 */
class BookDataSource(db: LocalDatabase) : BookLocalDataSource {


    private val bookDao = db.bookData()

    override suspend fun updateBook(book: Book) =
        withContext(Dispatchers.IO) {
            bookDao.update(book.toRoomBook())
        }


    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { bookDao.count() <= 0 }


    override suspend fun saveBooks(bookList: List<Book>) =
        withContext(Dispatchers.IO) {
            bookDao.insertBooks(bookList.map { it.toRoomBook() })
        }

    override suspend fun getBooks(): List<Book> =
        withContext(Dispatchers.IO) {
            bookDao.getBooks().map {
                it.toDomainBook()
            }
        }

    override suspend fun getBook(id: String): Book =
        withContext(Dispatchers.IO) {
            bookDao.getBook(id).toDomainBook()
        }
}