package com.architectcoders.data.repository

import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.Observation

class ObservationRepository(
    private val localDataSource: ObservationLocalDataSource,
    private val remoteDataSource: ObservationRemoteDatasource
) {


    suspend fun getObservations(id: String): ArrayList<Observation> {

        if (localDataSource.isEmpty()) {
            remoteDataSource.getObservationsByBook(id).let {
                it.forEach { observation ->
                    val localObservation = localDataSource.getObservation(observation.id)
                    if (localObservation == null) {
                        localDataSource.addObservation(observation)
                    } else {
                        localDataSource.updateObservation(observation)
                    }
                }
            }
        }

        return ArrayList(localDataSource.getObservations(id))
    }

    suspend fun getOwnerObservations(userId: String): List<Observation> {

        if (localDataSource.isEmpty()) {
            remoteDataSource.getObservationsByOwner(userId).let {
                it.forEach { observation ->
                    val localObservation = localDataSource.getObservation(observation.id)
                    if (localObservation == null) {
                        localDataSource.addObservation(observation)
                    } else {
                        localDataSource.updateObservation(observation)
                    }
                }
            }
        }

        return localDataSource.getObservationsByOwner(userId)
    }
}