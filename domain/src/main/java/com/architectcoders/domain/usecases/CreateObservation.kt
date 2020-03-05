package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.interfaces.ObservationRepository

class CreateObservation(private val observationRepository: ObservationRepository) {
    suspend fun invoke(observation: Observation) : Observation? = observationRepository.createObservation(observation)
}