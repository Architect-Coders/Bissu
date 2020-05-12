package com.example.testshared

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.entities.User


val mockedCategory = Category(
    id = "2",
    name = "Action"
)

val mockedBook = Book(
    id = "1",
    title = "Harry Pother",
    author = "J. K. Rowling",
    pages = "300",
    editorial = "Bloomsbury Publishing Scholastic,Corporation Salamandra",
    category = mockedCategory,
    description = "Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.",
    photoUrl = null
)

val mockedServerError = 500

val mockedUsername = "Anibalc"
val mockedPassword = "123"
val mockedDescription = "This is a description"
val mockedPages = "500"


val mockedUser = User(
    id = "1",
    username = mockedUsername,
    email = "aniablc@gmail.com",
    firstName = "anibal",
    lastName = "arenas",
    password = mockedPassword,
    photoUrl = null,
    categories = listOf(mockedCategory)
)

val mockedObservation = Observation(
    id = "1",
    userId = mockedUser.id,
    book = mockedBook,
    description = "This is one observation",
    page = "500"
)

