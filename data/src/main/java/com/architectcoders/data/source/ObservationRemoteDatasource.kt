package com.architectcoders.data.source

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation


interface ObservationRemoteDatasource {
    suspend fun getObservationsByUser(userId: String): DataResponse<List<Observation>>
    suspend fun createObservation(userId :String, bookId : String,description : String, page : String): DataResponse<Observation>
}