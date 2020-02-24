package com.architectcoders.bissu.data.server.observation

import com.architectcoders.bissu.data.mappers.toCreateObservationRequest
import com.architectcoders.bissu.data.mappers.toDomainObservation
import com.architectcoders.bissu.data.server.RetrofitClient
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.Observation
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
            ArrayList<Observation>()
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
            RetrofitClient().observationService.createObservation(observation.toCreateObservationRequest())
                .await().observation.toDomainObservation()
        }
}