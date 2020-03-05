package com.architectcoders.data.source

import com.architectcoders.domain.entities.Observation

interface ObservationLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun updateObservation(observation: Observation)
    suspend fun addObservation(observation: Observation)
    suspend fun getObservations(id: String): List<Observation>
    suspend fun getObservationsByOwner(userId: String): List<Observation>
    suspend fun getObservation(id : String) : Observation?
}