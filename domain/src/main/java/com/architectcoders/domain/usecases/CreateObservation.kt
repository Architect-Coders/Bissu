package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.reositories.ObservationRepository

class CreateObservation(private val observationRepository: ObservationRepository) {
    suspend fun invoke(observation: Observation) : DataResponse<Boolean> = observationRepository.createObservation(observation)
}