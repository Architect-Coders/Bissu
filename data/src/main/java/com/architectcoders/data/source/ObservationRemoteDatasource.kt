package com.architectcoders.data.source

import com.architectcoders.domain.Observation


interface ObservationRemoteDatasource {
    suspend fun getObservationsByBook(id: String): List<Observation>
    suspend fun getObservationsByOwner(userId: String): List<Observation>
}