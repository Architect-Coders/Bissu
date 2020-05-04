package com.architectcoders.framework.server.observation

import com.architectcoders.framework.server.observation.request.CreateObservationRequest
import com.architectcoders.framework.server.observation.response.CreateObservationResponse
import com.architectcoders.framework.server.observation.response.ObservationsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ObservationServices {

    @FormUrlEncoded
    @POST("/api/observation/getByBook")
    fun getObservations(@Field("id") bookId: String): Deferred<ObservationsResponse>

    @FormUrlEncoded
    @POST("/api/observation/getByuser")
    fun getObservationsByUser(@Field("id") id: String): Deferred<Response<ObservationsResponse>>

    @POST("/api/observation/add")
    fun createObservation(@Body body: CreateObservationRequest): Deferred<Response<CreateObservationResponse>>
}