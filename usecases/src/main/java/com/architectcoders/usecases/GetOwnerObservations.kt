package com.architectcoders.usecases

import com.architectcoders.data.repository.ObservationRepository
import com.architectcoders.domain.Observation

class GetOwnerObservations(private val observationRepository: ObservationRepository) {
    suspend fun invoke(userId: String): List<Observation> = observationRepository.getOwnerObservations(userId)
}