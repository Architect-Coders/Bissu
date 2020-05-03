package com.architectcoders.framework.database.observation

import com.architectcoders.framework.database.LocalDatabase
import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.domain.entities.Observation
import com.architectcoders.framework.mappers.toDomainObservation
import com.architectcoders.framework.mappers.toRoomObservation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2019-12-12.
 */
class ObservationDataSource(db: LocalDatabase) : ObservationLocalDataSource {

    private val observationDao = db.observationDao()

    override suspend fun addObservation(observation: Observation) =
        withContext(Dispatchers.IO) {
            observationDao.insert(observation.toRoomObservation())
        }

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) {
            observationDao.count() <= 0
        }

    override suspend fun updateObservation(observation: Observation) =
        withContext(Dispatchers.IO) {
            observationDao.update(observation.toRoomObservation())
        }

    override suspend fun getObservationsByUser(userId: String): List<Observation> =
        withContext(Dispatchers.IO) {
            observationDao.getObsevartionsByUser(userId).map { it.toDomainObservation() }
        }

}