package com.architectcoders.framework.server.observation

import com.architectcoders.framework.server.RetrofitClient
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.entities.Observation
import com.architectcoders.framework.mappers.toCreateObservationRequest
import com.architectcoders.framework.mappers.toDomainObservation
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

    override suspend fun createObservation(observation: Observation): Boolean =
        withContext(Dispatchers.IO) {
            val response = RetrofitClient()
                .observationService.createObservation(observation.toCreateObservationRequest())
                .await()

            return@withContext response.status == "OK"
        }
}