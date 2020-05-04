package com.example.testshared

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.User


val mockedBook = Book(
   id = "1",
    title = "Harry Pother",
    author = "J. K. Rowling",
    pages = "300",
    editorial = "Bloomsbury Publishing Scholastic,Corporation Salamandra",
    category = Category("2","Action"),
    description = "Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.",
    photoUrl = null
)

val mockedCategory = Category(
    id = "2",
    name = "Action"
)

val mockedUser = User(
    id = "1",
    username = "Anibal",
    email = "aniablc@gmail.com",
    firstName = "anibal",
    lastName = "arenas",
    password = "123",
    photoUrl = null,
    categories = listOf()
)

val mockedServerError = 500



val mockedUsername = "ANIBAL"
val mockedPassword = "123"