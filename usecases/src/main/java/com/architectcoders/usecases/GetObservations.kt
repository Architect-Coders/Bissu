package com.architectcoders.usecases

import com.architectcoders.data.repository.ObservationRepository
import com.architectcoders.domain.Observation

class GetObservations(private val observationRepository: ObservationRepository) {
    suspend fun invoke(bookId: String): ArrayList<Observation> = observationRepository.getObservations(bookId)
}