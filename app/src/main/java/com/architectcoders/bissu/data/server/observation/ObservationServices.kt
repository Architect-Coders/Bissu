package com.architectcoders.bissu.data.server.observation

import com.architectcoders.bissu.data.server.observation.response.ObservationsResponse
import com.architectcoders.domain.Observation
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ObservationServices {

    @GET("/api/book/{id}/observation")
    fun getObservations(@Path("id") bookId: String): Deferred<ObservationsResponse>

    @GET("/api/user/{id}/observation")
    fun getObservationsByUser(@Path("id") userId: String): Deferred<ObservationsResponse>

    @POST("/api/observation")
    fun createObservation(@Body body: Observation): Deferred<Boolean>
}