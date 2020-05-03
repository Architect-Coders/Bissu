package com.architectcoders.data.source

import com.architectcoders.domain.entities.Observation

interface ObservationLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun updateObservation(observation: Observation)
    suspend fun addObservation(observation: Observation)
    suspend fun getObservationsByUser(userId: String): List<Observation>

}