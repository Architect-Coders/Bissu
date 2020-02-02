package com.architectcoders.bissu.data.server.book

import com.architectcoders.bissu.data.mappers.toDomainBook
import com.architectcoders.bissu.data.server.RetrofitClient
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookDatasource : BookRemoteDatasource {
    override suspend fun addBook(book: Book): Book {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateBook(book: Book): Book {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getBooks(): List<Book> =
        withContext(Dispatchers.IO){
            /*RetrofitClient().bookService.
                getBooks()
                .await()
                .books.map {
                it.toDomainBook()
            }*/

            arrayListOf<Book>(
                Book(
                    "1",
                    "Titulo de libro",
                    "Yo",
                    "9",
                    "Salamanca",
                    "Terror",
                    "Libro muy raro para leer",
                    "https://trojantimes.net/wp-content/uploads/2019/03/top-10-confidence-books.jpg"
                ),
                Book(
                    "2",
                    "Un nuevo libro",
                    "Tu",
                    "100",
                    "Desconocido",
                    "Asombrante",
                    "Ni siquiera se si este libro existe de algun modo, pero, lo dudo rotundamente",
                    "https://www.incimages.com/uploaded_files/image/970x450/getty_883231284_200013331818843182490_335833.jpg"
                )
            )
        }

     override suspend fun getBook(id: String): Book? =
         withContext(Dispatchers.IO) {
             Book("1", "Titulo de libro", "Yo", "9", "Salamanca", "Terror", "Libro muy raro para leer", "https://trojantimes.net/wp-content/uploads/2019/03/top-10-confidence-books.jpg");
             /*RetrofitClient().bookService
                 .getBook(id)
                 .await()
                 .value?.toDomainBook()*/
         }

     /*override suspend fun fetchBooks(): ArrayList<Book> =
         withContext(Dispatchers.IO) {
             /*ArrayList(RetrofitClient().bookService
                 .fetchBooks()
                 .await()
                 .value.map { it.toDomainBook() })*/

             arrayListOf<Book>(
                 Book(
                     1,
                     "Titulo de libro",
                     "Yo",
                     9,
                     "Salamanca",
                     "Terror",
                     "Libro muy raro para leer",
                     "https://trojantimes.net/wp-content/uploads/2019/03/top-10-confidence-books.jpg"
                 ),
                 Book(
                     2,
                     "Un nuevo libro",
                     "Tu",
                     100,
                     "Desconocido",
                     "Asombrante",
                     "Ni siquiera se si este libro existe de algun modo, pero, lo dudo rotundamente",
                     "https://www.incimages.com/uploaded_files/image/970x450/getty_883231284_200013331818843182490_335833.jpg"
                 )
             )
         } */
}