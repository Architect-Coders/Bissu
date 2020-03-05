package com.architectcoders.domain.interfaces

import com.architectcoders.domain.entities.Observation

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
interface ObservationRepository {
    suspend fun getObservations(id: String): ArrayList<Observation>
    suspend fun getOwnerObservations(userId: String): List<Observation>
    suspend fun createObservation(observation: Observation): Observation?
}