package com.architectcoders.domain.interfaces

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
interface ObservationRepository {
    suspend fun getObservationsByUser(userId: String, forceRefresh : Boolean): DataResponse<List<Observation>>
    suspend fun createObservation(observation: Observation): DataResponse<Boolean>
}