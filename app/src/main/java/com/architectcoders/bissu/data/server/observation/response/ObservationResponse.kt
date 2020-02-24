package com.architectcoders.bissu.data.server.observation.response

import com.architectcoders.bissu.data.server.entities.Observation
import com.google.gson.annotations.SerializedName

data class ObservationResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("observation")
    var observation: Observation
)