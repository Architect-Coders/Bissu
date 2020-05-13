package com.architectcoders.framework.mappers

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.ObservationBook
import com.architectcoders.framework.server.entities.ObservationBook as RemoteObservationBook
import com.architectcoders.framework.server.entities.Book as RemoteBook
import com.architectcoders.framework.database.entities.Book as RoomBook

fun Book.toRoomBook(): RoomBook = RoomBook(
    id = id,
    title = title,
    author = author,
    pages = pages,
    editorial = editorial,
    category = category.toRoomCategory(),
    description = description,
    photoUrl = photoUrl
)

fun RoomBook.toDomainBook(): Book =
    Book(
        id = id,
        title = title,
        author = author,
        pages = pages,
        editorial = editorial,
        category = category.toDomainCategory(),
        description = description,
        photoUrl = photoUrl
    )

fun RemoteBook.toDomainBook(): Book =
    Book(
        id = _id,
        title = title,
        author = author,
        pages = pages,
        editorial = editorial,
        category = category.toDomainCategory(),
        description = description,
        photoUrl = photoUrl
    )


