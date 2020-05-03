package com.architectcoders.framework.mappers

import com.architectcoders.framework.server.observation.request.CreateObservationRequest
import com.architectcoders.domain.entities.Observation
import com.architectcoders.framework.database.entities.Observation as RoomObservation
import com.architectcoders.framework.server.entities.Observation as RemoteObservation

fun Observation.toRoomObservation(): RoomObservation = RoomObservation(
    id = id,
    userId = userId,
    book = book.toRoomBook(),
    description = description,
    page = page
)

fun RoomObservation.toDomainObservation(): Observation =
    Observation(
        id = id,
        userId = userId,
        book = book.toDomainBook(),
        description = description,
        page = page
    )

fun RemoteObservation.toDomainObservation(): Observation =
    Observation(
        id = id,
        userId = userId,
        book = book.toDomainBook(),
        description = description,
        page = page
    )

fun Observation.toCreateObservationRequest(): CreateObservationRequest =
    CreateObservationRequest(
        user = userId,
        book = book.id,
        description = description,
        page = page
    )