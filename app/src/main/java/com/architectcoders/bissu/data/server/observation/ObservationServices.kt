package com.architectcoders.bissu.data.server.observation

import com.architectcoders.bissu.data.server.observation.request.CreateObservationRequest
import com.architectcoders.bissu.data.server.observation.response.ObservationResponse
import com.architectcoders.bissu.data.server.observation.response.ObservationsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ObservationServices {

    @FormUrlEncoded
    @POST("/api/observation/getByBook")
    fun getObservations(@Field("id") bookId: String): Deferred<ObservationsResponse>

    @FormUrlEncoded
    @POST("/api/observation/getByUser")
    fun getObservationsByUser(@Field("id") id: String): Deferred<ObservationsResponse>

    @POST("/api/observation/add")
    fun createObservation(@Body body: CreateObservationRequest): Deferred<ObservationResponse>
}