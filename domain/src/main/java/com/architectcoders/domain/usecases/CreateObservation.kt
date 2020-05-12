package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.repositories.ObservationRepository

class CreateObservation(private val observationRepository: ObservationRepository) {
    suspend fun invoke(userId :String, bookId : String,description : String, page : String)
            : DataResponse<Observation> = observationRepository.createObservation(userId,bookId,description, page)
}