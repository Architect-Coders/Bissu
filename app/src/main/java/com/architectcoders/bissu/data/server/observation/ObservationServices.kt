package com.architectcoders.bissu.data.server.observation

import com.architectcoders.bissu.data.server.observation.response.ObservationResponse
import com.architectcoders.bissu.data.server.observation.response.ObservationsResponse
import com.architectcoders.domain.Observation
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ObservationServices {

    @FormUrlEncoded
    @POST("/api/observation/getByBook")
    fun getObservations(@Field("id") bookId: String): Deferred<ObservationsResponse>

    @FormUrlEncoded
    @POST("/api/observation/getByUser")
    fun getObservationsByUser(@Field("id") id: String): Deferred<ObservationsResponse>

    @POST("/api/observation/add")
    fun createObservation(@Body body: Observation): Deferred<ObservationResponse>
}