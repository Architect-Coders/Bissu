package com.architectcoders.bissu.data.server.observation

import com.architectcoders.bissu.data.mappers.toDomainObservation
import com.architectcoders.bissu.data.server.RetrofitClient
import com.architectcoders.bissu.data.server.observation.response.ObservationResponse
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.Book
import com.architectcoders.domain.Category
import com.architectcoders.domain.Observation
import com.architectcoders.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ObservationDatasource : ObservationRemoteDatasource {
    override suspend fun getObservationsByBook(id: String): ArrayList<Observation> =
        withContext(Dispatchers.IO) {
            ArrayList(
                RetrofitClient().observationService.getObservations(id)
                .await()
                .observations
                .map { it.toDomainObservation() })
        }

    override suspend fun getObservationsByOwner(userId: String): ArrayList<Observation> =
        withContext(Dispatchers.IO) {
            ArrayList(
                RetrofitClient().observationService.getObservationsByUser(userId)
                .await()
                .observations
                .map { it.toDomainObservation() })
        }

    override suspend fun createObservation(observation: Observation): Observation =
        withContext(Dispatchers.IO) {
            RetrofitClient().observationService.createObservation(observation)
                .await().observation.toDomainObservation()
        }

}