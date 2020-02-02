package com.architectcoders.bissu.data.server.observation

import com.architectcoders.bissu.data.server.observation.response.ObservationsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.http.Path

interface ObservationServices {

    @POST("/api/book/{id}/observation")
    fun getObservations(@Path("id") bookId: String): Deferred<ObservationsResponse>
}