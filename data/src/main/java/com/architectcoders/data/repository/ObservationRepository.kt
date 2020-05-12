package com.architectcoders.data.repository

import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.repositories.ObservationRepository

class ObservationRepository(private val localDataSource: ObservationLocalDataSource, private val remoteDataSource: ObservationRemoteDatasource)
    : ObservationRepository {



    override suspend fun getObservationsByUser(userId: String, forceRefresh : Boolean): DataResponse<List<Observation>> {
        if(localDataSource.isEmpty() || forceRefresh ){
            val response = remoteDataSource.getObservationsByUser(userId)
            if (response is DataResponse.Success){
                response.data.forEach {
                    localDataSource.addObservation(it)
                }
            }
            return response
        } else return DataResponse.Success(localDataSource.getObservationsByUser(userId))
    }

    override suspend fun createObservation(userId :String, bookId : String,description : String, page : String): DataResponse<Observation> {
        return remoteDataSource.createObservation(userId,bookId,description,page)
    }
}