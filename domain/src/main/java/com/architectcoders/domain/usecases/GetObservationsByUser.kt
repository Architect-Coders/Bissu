package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.repositories.ObservationRepository

class GetObservationsByUser(private val observationRepository: ObservationRepository) {
    suspend fun invoke(userId: String, forceRefresh : Boolean = false)
            : DataResponse<List<Observation>> = observationRepository.getObservationsByUser(userId,forceRefresh)
}