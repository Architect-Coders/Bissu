package com.architectcoders.bissu

import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.example.testshared.mockedObservation


class FakeObservationRemoteDatasource : ObservationRemoteDatasource {

    var observationList = mutableListOf(mockedObservation)
    var mockedListResponse : DataResponse<List<Observation>> = DataResponse.Success(observationList)
    var mockedObservationResponse : DataResponse<Observation> = DataResponse.Success(mockedObservation)

    override suspend fun getObservationsByUser(userId: String): DataResponse<List<Observation>> {
       return mockedListResponse
    }

    override suspend fun createObservation(
        userId: String,
        bookId: String,
        description: String,
        page: String
    ): DataResponse<Observation> {
       return mockedObservationResponse
    }
}

class FakeObservationLocalDatasource : ObservationLocalDataSource {

    var isEmpty = false;
    var observation = mockedObservation
    var observationList : List<Observation> = mutableListOf(observation)

    override suspend fun isEmpty(): Boolean = isEmpty

    override suspend fun updateObservation(observation: Observation) {
        this.observation = observation
    }

    override suspend fun addObservation(observation: Observation) {
        val mutableObservationList : MutableList<Observation> = observationList.toMutableList()
        mutableObservationList.add(observation)
        observationList =  mutableObservationList
    }

    override suspend fun getObservationsByUser(userId: String): List<Observation> {
        return observationList
    }

}