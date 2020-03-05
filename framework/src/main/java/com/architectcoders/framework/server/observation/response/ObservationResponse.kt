package com.architectcoders.framework.server.observation.response

import com.architectcoders.framework.server.entities.Observation
import com.google.gson.annotations.SerializedName

data class ObservationResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("observation")
    var observation: Observation
)