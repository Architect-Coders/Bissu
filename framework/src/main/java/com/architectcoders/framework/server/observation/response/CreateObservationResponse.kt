package com.architectcoders.framework.server.observation.response

import com.architectcoders.framework.server.entities.CreateObservation
import com.google.gson.annotations.SerializedName

data class CreateObservationResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("observation")
    var observation: CreateObservation
)