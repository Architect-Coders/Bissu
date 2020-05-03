package com.architectcoders.framework.server.observation

import android.content.Context
import com.architectcoders.framework.server.RetrofitClient
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.architectcoders.framework.mappers.toCreateObservationRequest
import com.architectcoders.framework.mappers.toDomainObservation
import com.architectcoders.framework.util.ErrorCode
import com.architectcoders.framework.util.isAvailableNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ObservationDatasource(val context: Context) : ObservationRemoteDatasource {

    override suspend fun getObservationsByUser(userId: String): DataResponse<List<Observation>> =
        withContext(Dispatchers.IO) {

            if (!context.isAvailableNetwork()) return@withContext DataResponse.NetworkError

            val result = RetrofitClient().observationService.getObservationsByUser(userId).await()

            if (result.isSuccessful) {
                if (result.body() == null) return@withContext DataResponse.ServerError(ErrorCode.BAD_REQUEST)
                val observationList: List<Observation> = result.body()!!.observations.map { it.toDomainObservation() }
                return@withContext DataResponse.Success(observationList)
            }

            return@withContext DataResponse.ServerError(ErrorCode.SERVER_ERROR)
        }

    override suspend fun createObservation(observation: Observation): DataResponse<Boolean> =
        withContext(Dispatchers.IO) {
            //validate network connection
            if (!context.isAvailableNetwork()) return@withContext DataResponse.NetworkError
            //call to API
            val result = RetrofitClient().observationService.createObservation(observation.toCreateObservationRequest()).await()
            //validate resul
            if (result.isSuccessful){
                if (result.body() == null) return@withContext DataResponse.ServerError(ErrorCode.BAD_REQUEST)
               // val observation : Observation = result.body()!!.observation
                return@withContext DataResponse.Success(true)
            }
            return@withContext DataResponse.ServerError(ErrorCode.SERVER_ERROR)
        }
}