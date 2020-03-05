package com.architectcoders.data.repository

import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.interfaces.ObservationRepository
import java.lang.Exception

class ObservationRepository(
    private val localDataSource: ObservationLocalDataSource,
    private val remoteDataSource: ObservationRemoteDatasource
) : ObservationRepository{


    override suspend fun getObservations(id: String): ArrayList<Observation> {

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

    override suspend fun getOwnerObservations(userId: String): List<Observation> {

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

    override suspend fun createObservation(observation: Observation): Observation? {
        return try {
            remoteDataSource.createObservation(observation)
        }catch (exceptio: Exception){
            null
        }
    }
}