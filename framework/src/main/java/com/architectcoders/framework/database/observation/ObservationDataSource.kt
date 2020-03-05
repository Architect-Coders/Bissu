package com.architectcoders.framework.database.observation

import android.util.Log
import com.architectcoders.framework.database.LocalDatabase
import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.domain.entities.Observation
import com.architectcoders.framework.mappers.toDomainObservation
import com.architectcoders.framework.mappers.toRoomObservation
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2019-12-12.
 */
class ObservationDataSource(db: LocalDatabase) : ObservationLocalDataSource {


    private val observationDao = db.observationDao()

    override suspend fun addObservation(observation: Observation) =
        withContext(Dispatchers.IO){
            Log.e("prueba", Gson().toJson(observation))
            observationDao.insert(observation.toRoomObservation())
    }

    override suspend fun getObservation(id: String): Observation? =
        withContext(Dispatchers.IO){
            observationDao.getObsevartions(id)?.toDomainObservation()
        }

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) {
            observationDao.count() <= 0
        }


    override suspend fun updateObservation(observation: Observation) =
        withContext(Dispatchers.IO) {
            observationDao.update(observation.toRoomObservation())
        }

    override suspend fun getObservations(id: String): List<Observation> =
        withContext(Dispatchers.IO) {
            observationDao.getObsevartionsByBook(id).map { it.toDomainObservation() }
        }

    override suspend fun getObservationsByOwner(userId: String): List<Observation> =
        withContext(Dispatchers.IO) {
            observationDao.getObsevartionsByUser(userId).map { it.toDomainObservation() }
        }

}