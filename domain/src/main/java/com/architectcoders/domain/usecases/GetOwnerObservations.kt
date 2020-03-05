package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.interfaces.ObservationRepository

class GetOwnerObservations(private val observationRepository: ObservationRepository) {
    suspend fun invoke(userId: String): List<Observation> = observationRepository.getOwnerObservations(userId)
}