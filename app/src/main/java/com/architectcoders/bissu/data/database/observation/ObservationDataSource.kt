package com.architectcoders.bissu.data.database.observation

import android.util.Log
import com.architectcoders.bissu.data.database.LocalDatabase
import com.architectcoders.bissu.data.mappers.toDomainObservation
import com.architectcoders.bissu.data.mappers.toRoomObservation
import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.domain.Observation
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

}