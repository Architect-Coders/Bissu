package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.interfaces.ObservationRepository

class GetObservations(private val observationRepository: ObservationRepository) {
    suspend fun invoke(bookId: String): ArrayList<Observation> = observationRepository.getObservations(bookId)
}