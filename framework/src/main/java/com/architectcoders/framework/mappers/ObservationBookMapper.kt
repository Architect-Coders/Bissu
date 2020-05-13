package com.architectcoders.framework.mappers

import com.architectcoders.domain.entities.ObservationBook
import com.architectcoders.framework.database.entities.ObservationBook as RoomObservationBook
import com.architectcoders.framework.server.entities.ObservationBook as RemoteObservationBook


fun RemoteObservationBook.todomainObservationBook(): ObservationBook =
    ObservationBook(
        id = _id,
        title = title,
        author = author,
        pages = pages,
        editorial = editorial,
        category = category,
        description = description,
        photoUrl = photoUrl
    )


fun RemoteObservationBook.toRoomBook(): RoomObservationBook =
    RoomObservationBook(
        id = _id,
        title = title,
        author = author,
        pages = pages,
        editorial = editorial,
        category = category,
        description = description,
        photoUrl = photoUrl
    )

fun ObservationBook.toRoomBook() : RoomObservationBook =
    RoomObservationBook(
        id = id,
        title = title,
        author = author,
        pages = pages,
        editorial = editorial,
        category = category,
        description = description,
        photoUrl = photoUrl
    )

fun RoomObservationBook.toDomainBook() : ObservationBook =
    ObservationBook(
        id = id,
        title = title,
        author = author,
        pages = pages,
        editorial = editorial,
        category = category,
        description = description,
        photoUrl = photoUrl
    )

