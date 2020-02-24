package com.architectcoders.bissu.data.mappers

import com.architectcoders.bissu.data.database.toDomainUser
import com.architectcoders.bissu.data.database.toRoomUser
import com.architectcoders.bissu.data.server.observation.request.CreateObservationRequest
import com.architectcoders.domain.Observation
import com.architectcoders.bissu.data.database.entities.Observation as RoomObservation
import com.architectcoders.bissu.data.server.entities.Observation as RemoteObservation

fun Observation.toRoomObservation(): RoomObservation = RoomObservation(
    id = id,
    user = user.toRoomUser(),
    book = book.toRoomBook(),
    description = description,
    page = page
)

fun RoomObservation.toDomainObservation(): Observation = Observation(
    id = id,
    user = user.toDomainUser(),
    book = book.toDomainBook(),
    description = description,
    page = page
)

fun RemoteObservation.toDomainObservation(): Observation = Observation(
    id = id,
    user = user.toDomainUser(),
    book = book.toDomainBook(),
    description = description,
    page = page
)

fun Observation.toCreateObservationRequest(): CreateObservationRequest = CreateObservationRequest(
    user = user.id,
    book = book.id,
    description = description,
    page = page
)