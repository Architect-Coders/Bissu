package com.architectcoders.usecases

import com.architectcoders.data.repository.ObservationRepository
import com.architectcoders.domain.Observation

class CreateObservation(private val observationRepository: ObservationRepository) {
    suspend fun invoke(observation: Observation) : Observation? = observationRepository.createObservation(observation)
}