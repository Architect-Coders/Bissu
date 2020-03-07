package com.architectcoders.data.source

import com.architectcoders.domain.entities.Observation


interface ObservationRemoteDatasource {
    suspend fun getObservationsByBook(id: String): List<Observation>
    suspend fun getObservationsByOwner(userId: String): List<Observation>
    suspend fun createObservation(observation: Observation): Boolean
}